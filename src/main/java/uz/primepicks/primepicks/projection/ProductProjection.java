package uz.primepicks.primepicks.projection;

import java.util.Base64;
import java.util.UUID;

public interface ProductProjection {
    UUID getId();
    String getName();
    Integer getPrice();
    byte[] getPhoto();

    default String base64Photo() {
        return Base64.getEncoder().encodeToString(this.getPhoto());
    }
}
