package terarium.server.dto.Terarium;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import terarium.server.model.Terarium;

@Data
@AllArgsConstructor
public class ResponseTerariumDto {
    private Terarium terarium;
    private HttpStatus status;
}
