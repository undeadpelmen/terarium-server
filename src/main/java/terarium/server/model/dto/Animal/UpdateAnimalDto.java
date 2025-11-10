package terarium.server.model.dto.Animal;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Data
@Tag(name = "Animal")
@Schema(name = "Update Animal Dto", description = "Dto for update(PUT) request")
public class UpdateAnimalDto {
    @Schema(example = "Crested gecko")
    private String name;
    
    @Schema(example = "Correloporhus ciliatus")
    private String lat_name;
    
    @Schema(example = "28")
    private int day_max_t;
    
    @Schema(example = "26")
    private int day_min_t;
    
    @Schema(example = "24")
    private int night_max_t;
    
    @Schema(example = "22")
    private int night_min_t;
    
    @Schema(example = "11.0")
    private float uv_time;
    
    @Schema(example = "5.0")
    private float uv_spec;
    
    @Schema(example = "13")
    private int uv_power;
    
    @Schema(example = "80")
    private int humidity_max;
    
    @Schema(example = "60")
    private int humidity_min;
    
    @Schema(example = "11.0")
    private float day_len;
    
    @Schema(example = "5.5")
    private float feed_rate;
    
    @Schema(example = "2.0")
    private float kide_feed_rate;
    
    @Schema(example = "Cricket, zophobas, mealworm, hawk moth caterpillar, sugar-free tropical fruit puree")
    private String food;
    
    @Schema(example = "Calcium, D3, multivitamins")
    private String vitamins;
}
