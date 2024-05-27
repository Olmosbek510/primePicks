package uz.primepicks.primepicks.projection;

import java.util.Base64;

public interface OrderProductProjection {
    byte[] getPhoto();
    String getName();
    Integer getPrice();
    Integer getAmount();

    default String get64Photo(){
        return Base64.getEncoder().encodeToString(getPhoto());
    }
}
