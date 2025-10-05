package terarium.server.dto.Response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OkResponseDto {
    private String messege;
    private HttpStatus status;
}
