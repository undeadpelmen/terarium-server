package terarium.server.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import terarium.server.dto.User.CreateUserDto;
import terarium.server.dto.User.UpdateUserDto;

@Entity
@Table(name = "terarium_users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(example = "666")
    private int id;
    
    @Column(name = "email",nullable = false,unique = true)
    @Schema(example = "example@example.com")
    private String email;
    
    @Column(name = "passwordHash", nullable = false)
    @Schema(example = "someverystrongpasswordhash")
    private String passwordHash;
    
    public static User fromDto(CreateUserDto createUserDto) {
        User user = new User();
        
        user.setEmail(createUserDto.getEmail());
        user.setPasswordHash(createUserDto.getPasswordHash());
        
        return user;
    }
    
    public static User fromDto(UpdateUserDto updateUserDto) {
        User user = new User();
        
        user.setEmail(updateUserDto.getEmail());
        user.setPasswordHash(updateUserDto.getPassword());
        
        return user;
    }
}
