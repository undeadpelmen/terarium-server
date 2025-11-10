package terarium.server.model.dto.Rabbit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Message")
public class Message extends Object{
    @Schema(example = "38:d5:7a:f4:fe:41")
    private String mac;
    
    @Schema(example = "Hello")
    private String message;
    
    @Schema(example = "02-01-2006 15:04")
    private String time;
    
    @JsonCreator
    public Message(@JsonProperty("Mac") String mac, @JsonProperty("Message") String message,@JsonProperty("Time") String time)
    {
        this.mac = mac;
        this.message = message;
        this.time = time;
    }
}
