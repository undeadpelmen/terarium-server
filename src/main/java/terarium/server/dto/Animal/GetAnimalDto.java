package terarium.server.dto.Animal;

import lombok.Data;

@Data
public class GetAnimalDto {
    private int id;
    private String name;
    private int max_t;
    private int min_t;
    private int day_t;
    private int night_t;
    private int uv_req;
    private int humidity;
    private float day_len;
    private float feed_rate;
}
