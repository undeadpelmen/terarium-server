package terarium.server.model.dto.Terarium;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import terarium.server.model.Animal;
import terarium.server.model.dto.Rabbit.Message;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "TerariumRabbitDto")
public class TerariumRabbitInDto extends Message {
    @Schema(example = "666")
    private Integer terariumId;
    
    @Schema(contentSchema = Animal.class)
    private Animal animal;
    
    public TerariumRabbitInDto(@JsonProperty("Mac") String mac, @JsonProperty("Message") String message,@JsonProperty("Time") String time, @JsonProperty("TerariumId") int terariumId, @JsonProperty("Animal") Animal animal)
    {
        this.terariumId = terariumId;
        this.animal = animal;
        
        super(mac, message, time);
    }
    
    public TerariumRabbitInDto(String mac, String message, String time) {
        super(mac, message, time);
        this.terariumId = -1;
        this.animal = new Animal();
    }
}
