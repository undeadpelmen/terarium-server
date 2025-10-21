package terarium.server.dto.Terarium;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import terarium.server.model.Animal;
import terarium.server.dto.Rabbit.Message;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "TerariumRabbitDto")
public class TerariumRabbitDto extends Message {
    @Schema(example = "666")
    private Integer terariumId;
    
    @Schema(contentSchema = Animal.class)
    private Animal animal;
    
    public TerariumRabbitDto(@JsonProperty("Mac") String mac, @JsonProperty("Message") String message,@JsonProperty("Time") String time, @JsonProperty("TerariumId") int terariumId, @JsonProperty("Animal") Animal animal)
    {
        this.terariumId = terariumId;
        this.animal = animal;
        
        super(mac, message, time);
    }
    
    public TerariumRabbitDto(String mac, String message, String time) {
        super(mac, message, time);
        this.terariumId = -1;
        this.animal = new Animal();
    }
}
