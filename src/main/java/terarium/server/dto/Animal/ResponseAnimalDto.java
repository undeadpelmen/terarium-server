package terarium.server.dto.Animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import terarium.server.model.Animal;

@Data
@AllArgsConstructor
public class ResponseAnimalDto {
    private Animal animal;
}
