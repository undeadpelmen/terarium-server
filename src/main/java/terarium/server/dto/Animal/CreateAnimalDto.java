package terarium.server.dto.Animal;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Data
@Tag(name = "Animal")
@Schema(name = "Create Animal Dto", description = "Dto for create(POST) request")
public class CreateAnimalDto {
    @Schema(example = "pushok")
    private String name;
    
    @Schema(example = "pushokus obeknovenney")
    private String lat_name;
    
    @Schema(example = "777")
    private int day_max_t;
    
    @Schema(example = "666")
    private int day_min_t;
    
    @Schema(example = "13")
    private int night_max_t;
    
    @Schema(example = "12")
    private int night_min_t;
    
    @Schema(example = "0.6")
    private float uv_req;
    
    @Schema(example = "90")
    private int humidity_max;
    
    @Schema(example = "50")
    private int humidity_min;
    
    @Schema(example = "0.6")
    private float day_len;
    
    @Schema(example = "0.5")
    private float feed_rate;
}
