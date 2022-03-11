package uz.pdp.appjparelationships.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Apiresponse {
private String message;
private boolean success;
private Object object;

    public Apiresponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
