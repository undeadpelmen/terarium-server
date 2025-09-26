package terarium.server.dto.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserDto {
    private String email;
    private String passwordHash;
}
