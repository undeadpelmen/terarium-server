package terarium.server.error;

import java.io.IOException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntityGetException extends IOException {
    public EntityGetException (String messege) {
        super(messege);
    }
    
    public EntityGetException(String message, Throwable tr) {
        super(message, tr);
    }
}
