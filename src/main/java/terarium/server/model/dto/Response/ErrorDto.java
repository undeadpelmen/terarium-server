package terarium.server.model.dto.Response;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Error Dto")
public class ErrorDto {
    @Schema(example = "Something went wrong")
    private String message;
    
    @Schema(example = "02-01-2006 15:04")
    private String timestamp;
    
    private Exception err;
    
    public ErrorDto(String messege) {
        this.message = messege;
        this.timestamp = new SimpleDateFormat("dd-MM-yy HH:mm").format( new Timestamp(System.currentTimeMillis()));
        this.err = new Exception();
    }
    
    public ErrorDto(String message, Exception err) {
        this.message = message;
        this.timestamp = new SimpleDateFormat("dd-MM-yy HH:mm").format( new Timestamp(System.currentTimeMillis()));
        this.err = err;
    }
}
