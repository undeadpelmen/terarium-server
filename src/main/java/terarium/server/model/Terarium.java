package terarium.server.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import terarium.server.dto.Terarium.CreateTerariumDto;
import terarium.server.dto.Terarium.UpdateTerariumDto;


@Entity
@Table(name = "terarium")
@Data
@NoArgsConstructor
public class Terarium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(example = "666")
    private int id;
    
    @Column(name = "name", nullable = false)
    @Schema(example = "My Terarium")
    private String name;
    
    @Column(name = "mac", nullable = false, unique = true)
    @Schema(example = "38:d5:7a:f4:fe:41")
    private String mac;
    
    @ManyToOne
    @Schema(contentSchema = Animal.class)
    private Animal animal;
    
    @Column(name = "aftorId")
    @Schema(example = "666")
    private int aftorId;
    
    public static Terarium fromDto(CreateTerariumDto createTerariumDto, Animal animal) {
        Terarium terarium = new Terarium();
        
        terarium.setAftorId(createTerariumDto.getAftorId());
        terarium.setName(createTerariumDto.getName());
        terarium.setMac(createTerariumDto.getMac());
        terarium.setAnimal(animal);
        
        return terarium;
    }
    
    public static Terarium fromDto(UpdateTerariumDto updateTerariumDto, Animal animal) {
        Terarium terarium = new Terarium();
        
        terarium.setAftorId(updateTerariumDto.getAftorId());
        terarium.setName(updateTerariumDto.getName());
        terarium.setMac(updateTerariumDto.getMac());
        terarium.setAnimal(animal);
        
        return terarium;
    }
}
