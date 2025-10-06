package terarium.server.dto.Terarium;

import lombok.AllArgsConstructor;
import lombok.Data;
import terarium.server.model.Terarium;

@Data
@AllArgsConstructor
public class ResponseTerariumDto {
    private Terarium terarium;
}
