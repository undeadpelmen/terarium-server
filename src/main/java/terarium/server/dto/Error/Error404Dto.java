package terarium.server.dto.Error;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "404 Error Dto")
public class Error404Dto {
    @Schema(example = "404")
    private int status;
    
    @Schema(example = "BAD REQUEST")
    private String message;
    
    @Schema(example = "2025-09-23T18:03:45.237+00:00")
    private Timestamp timestamp;
}
