package terarium.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {
	
	public static final Logger log = LoggerFactory.getLogger(ServerApplication.class);
		
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
