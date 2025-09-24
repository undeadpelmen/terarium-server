package terarium.server.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Terarium")
@Tag(name = "Terarium")
@Schema(name = "Terarium", description = "Terarium entity")
public class Terarium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(name = "Id", example = "666")
    private int id;
    
    @Column(name = "name")
    @Schema(name = "name", example = "My Terarium")
    private String name;
    
    @ManyToOne
    @Schema(name = "animal", contentSchema = Animal.class)
    private Animal animal;
}
