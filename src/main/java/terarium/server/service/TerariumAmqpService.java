package terarium.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerEndpoint;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerariumAmqpService {
    @Autowired
    private AmqpAdmin  amqpAdmin;
    
    @Autowired
    private RabbitListenerEndpointRegistry registry;
    
    @Autowired
    private RabbitListenerContainerFactory<?> factory;
    
    private Logger log = LoggerFactory.getLogger(TerariumAmqpService.class);
    
    public void  newQueue(String queueName){
        amqpAdmin.declareQueue(new Queue(queueName, true, false, true));
    }
    
    public void newListeneableQueue(String queueName) {
        String empointId = queueName + "-listener";
        
        amqpAdmin.declareQueue(new Queue(queueName, true, false, true));
        
        log.info("Declared new queue: " + queueName);
        
        SimpleRabbitListenerEndpoint endpoint = new SimpleRabbitListenerEndpoint();
        
        endpoint.setId(empointId);
        
        endpoint.setQueueNames(queueName);
        
        endpoint.setMessageListener(message -> terariumMessageListenerHandler(message));
        
        registry.registerListenerContainer(endpoint, factory, true);
        
        log.info("Registered listener for queue: " + queueName);
    }
    
    private void terariumMessageListenerHandler(Message message) {
        log.info("Received message from terarium: " + new String(message.getBody()));
    }
}
