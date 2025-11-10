package terarium.server.error;

import java.io.IOException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntityCreateException extends IOException {
    public EntityCreateException(String messege) {
        super(messege);
    }
    
    public EntityCreateException(String message, Throwable tr) {
        super(message, tr);
    }
}
