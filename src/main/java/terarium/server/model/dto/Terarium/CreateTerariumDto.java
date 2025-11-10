package terarium.server.model.dto.Terarium;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateTerariumDto {
    @Schema(example = "My Terarium")
    private String name;
    
    @Schema(example = "38:d5:7a:f4:fe:41")
    private String mac;
    
    @Schema(example = "666")
    private int animalId;
    
    @Schema(example = "666")
    private int aftorId;
}
