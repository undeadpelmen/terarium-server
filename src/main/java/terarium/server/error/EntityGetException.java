package terarium.server.error;

import java.io.IOException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntityGetException extends IOException {
    public EntityGetException (String messege) {
        super(messege);
    }
}
