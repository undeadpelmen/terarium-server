package terarium.server.dto.Response;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Error Dto")
public class ErrorDto {
    @Schema(example = "Something went wrong")
    private String message;
    
    @Schema(example = "2025-09-23T18:03:45.237+00:00")
    private Timestamp timestamp;
    
    public ErrorDto(String messege) {
        this.message = messege;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}
