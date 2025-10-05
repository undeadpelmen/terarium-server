package terarium.server.dto.User;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import terarium.server.model.User;

@Data
@AllArgsConstructor
public class ResponseUserDto {
    private User user;
    private HttpStatus status;
}
