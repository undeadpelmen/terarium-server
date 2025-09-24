package terarium.server.dto.Terarium;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Tag(name = "Terarium")
@Schema(name = "Create Terarium Dto", description = "Dto for create(POST) requests")
public class CreateTerariumDto {
    @Schema(name = "name", example = "My Terarium")
    private String name;
    
    @Schema(name = "animalId", example = "103")
    private int animalId;
}
