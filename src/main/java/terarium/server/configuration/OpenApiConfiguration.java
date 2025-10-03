package terarium.server.configuration;

import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.List;

@Configuration
@AllArgsConstructor
public class OpenApiConfiguration {

    private Environment env;

    @Bean
    public OpenAPI defineOpenAPI () {
        Server server = new Server();
        String serverPort = env.getProperty("server.port");
        server.setUrl(serverPort);
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Undead Pelmen");

        Info info = new Info()
                .title("Terarium Api for terarium")
                .version("0.0.1")
                .description("CRUD Api for work with terariums, users, animals.")
                .contact(myContact);
        return new OpenAPI().info(info).servers(List.of(server));
    }
}
