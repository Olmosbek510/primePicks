package uz.primepicks.primepicks.projection;

import java.util.UUID;

public interface SalesReportProjection {
    UUID getId();
    String getName();
    Integer getPrice();
    Integer getAmount();
    Integer getTotal();
}
