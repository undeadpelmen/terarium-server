package terarium.server.dto.Error;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Tag(name = "Error")
@Schema(description = "Error Dto")
public class ErrorDto {
    @Schema(example = "400")
    private int status;
    
    @Schema(example = "BAD REQUEST")
    private String message;
    
    @Schema(example = "2025-09-23T18:03:45.237+00:00")
    private Timestamp timestamp;
}
