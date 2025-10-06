package terarium.server.error;

import java.io.IOException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntityUpdateException extends IOException {
    public EntityUpdateException (String messege) {
        super(messege);
    }
}
