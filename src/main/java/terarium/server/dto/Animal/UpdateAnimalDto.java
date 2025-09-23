package terarium.server.dto.Animal;

import lombok.Data;

@Data
public class UpdateAnimalDto {
    private String name;
    private String lat_name;
    private int day_max_t;
    private int day_min_t;
    private int night_max_t;
    private int night_min_t;
    private int uv_req;
    private int humidity_max;
    private int humidity_min;
    private float day_len;
    private float feed_rate;
}
