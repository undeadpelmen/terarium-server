package terarium.server.model.dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import terarium.server.model.User;

@Data
@AllArgsConstructor
public class ResponseUserDto {
    private User user;
}
