package terarium.server.dto.Response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListResponseDto {
    private List<?> list;
}
