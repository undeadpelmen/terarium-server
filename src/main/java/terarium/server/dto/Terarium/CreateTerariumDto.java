package terarium.server.dto.Terarium;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateTerariumDto {
    private String name;
    private int animalId;
    private int aftorId;
}
