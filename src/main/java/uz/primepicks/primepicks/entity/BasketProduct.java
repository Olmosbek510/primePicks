package uz.primepicks.primepicks.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketProduct {
    private Product product;
    private Integer amount;
    private String base64Photo;
}
