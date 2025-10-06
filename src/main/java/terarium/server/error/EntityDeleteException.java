package terarium.server.error;

import java.io.IOException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntityDeleteException extends IOException {
    public EntityDeleteException (String messege) {
        super(messege);
    }
}
