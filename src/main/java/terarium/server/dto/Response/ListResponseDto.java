package terarium.server.dto.Response;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListResponseDto {
    private List<?> list;
    private HttpStatus status;
}
