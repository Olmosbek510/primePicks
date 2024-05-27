package uz.primepicks.primepicks.projection;

import java.util.Base64;

public interface ProductProfitProjection {
    byte[] getPhoto();
    String getName();
    Integer getPrice();
    Integer getAmount();
    Integer getTotalSales();
    Integer getProfit();

    default String getBase64Photo(){
        return Base64.getEncoder().encodeToString(getPhoto());
    }
}
