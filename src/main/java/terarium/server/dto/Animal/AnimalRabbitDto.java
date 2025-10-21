package terarium.server.dto.Animal;

import com.fasterxml.jackson.annotation.JsonProperty;

import terarium.server.dto.Rabbit.Message;

public class AnimalRabbitDto extends Message{
    public AnimalRabbitDto(@JsonProperty("mac") String mac, @JsonProperty("message") String message,@JsonProperty("time") String time)
    {
        super(mac, message, time);
    }
}
