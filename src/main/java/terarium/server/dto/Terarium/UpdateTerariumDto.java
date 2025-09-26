package terarium.server.dto.Terarium;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateTerariumDto {
    @Schema(example = "My Terarium")
    private String name;
    
    @Schema(example = "666")
    private int animalId;
    
    @Schema(example = "666")
    private int aftorId;
}
