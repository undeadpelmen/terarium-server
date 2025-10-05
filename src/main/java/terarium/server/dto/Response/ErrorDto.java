package terarium.server.dto.Response;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Error Dto")
public class ErrorDto {
    @Schema(example = "BAD REQUEST")
    private HttpStatus status;
    
    @Schema(example = "Can't save user to DB")
    private String massage;
    
    @Schema(example = "2025-09-23T18:03:45.237+00:00")
    private Timestamp timestamp;
}
