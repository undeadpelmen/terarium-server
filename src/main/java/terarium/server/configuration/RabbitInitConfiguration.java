package terarium.server.configuration;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import terarium.server.dto.Rabbit.InitMessage;
import terarium.server.dto.Terarium.TerariumRabbitDto;
import terarium.server.model.Terarium;
import terarium.server.service.TerariumAmqpService;
import terarium.server.service.TerariumService;

@Configuration
public class RabbitInitConfiguration {
	static final String INIT_QUEUE = "terarium.init";
    public static final String TERARIUM_OUT_QUEUE = "terarium.out/";
    
    @Autowired
    private TerariumAmqpService terariumAmqpService;
    
    @Autowired  
    private TerariumService terariumService;
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    private Logger log = LoggerFactory.getLogger(RabbitInitConfiguration.class);
    
    private ObjectMapper mapper = new ObjectMapper();
    
    private Map<String, String> ter = new HashMap<String, String>();
    
	@Bean
	public Queue myQueue() {
		return new Queue(INIT_QUEUE, true, false, true);
	}
    
	@RabbitListener(queues = INIT_QUEUE)
	public void listen(String message) throws JsonProcessingException, IOException {
		log.info("Message read : " + message);
        
        InitMessage mes = mapper.readValue(message, InitMessage.class);
        
        if (!ter.containsKey(mes.getMac())){
            log.info("\n new terarium \n mac: {}", mes.getMac());
            
            ter.put(mes.getMac(), mes.getTime());
            
            String consumeQueueName = "terarium.out/" + mes.getMac();
            String produceQueueName = "terarium.in/" + mes.getMac();
            
            terariumAmqpService.newQueue(consumeQueueName);
            
            terariumAmqpService.newQueueListner(consumeQueueName);
            
            terariumAmqpService.newQueue(produceQueueName);
            
            try {
                Terarium terarium = terariumService.getTerariumsByMac(mes.getMac());
                
                rabbitTemplate.convertAndSend(produceQueueName, mapper.writeValueAsString(terarium));
            } catch (IOException e) {
                log.warn("Unregistred terarium");
                
                TerariumRabbitDto terariumRabbitDto = new TerariumRabbitDto(mes.getMac(), "Terarium unregistred", new SimpleDateFormat("dd-MM-yy HH:mm").format( new Timestamp(System.currentTimeMillis())));
                
                rabbitTemplate.convertAndSend(produceQueueName, mapper.writeValueAsString(terariumRabbitDto));
            }
        }
	}
    
    public Map<String, String> getTerMac() {
        return ter;
    }
}
