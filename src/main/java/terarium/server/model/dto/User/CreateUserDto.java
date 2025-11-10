package terarium.server.model.dto.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserDto {
    @Schema(example = "example@example.com")
    private String email;
    
    @Schema(example = "someverystrongpasswordhash")
    private String passwordHash;
}
