package terarium.server.model.dto.Rabbit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InitMessage extends Message {
    public InitMessage(@JsonProperty("Mac") String mac, @JsonProperty("Message") String message,@JsonProperty("Time") String time)
    {
        super(mac, message, time);
    }
}
