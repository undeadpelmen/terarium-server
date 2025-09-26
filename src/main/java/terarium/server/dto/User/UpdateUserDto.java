package terarium.server.dto.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserDto {
    @Schema(example = "example@example.com")
    private String email;
    
    @Schema(example = "someverystrongpasswordhash")
    private String password;
}
