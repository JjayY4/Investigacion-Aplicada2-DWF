package sv.edu.udb.Investigacion.event;

import sv.edu.udb.Investigacion.model.Product;
import java.time.LocalDateTime;

public class ProductEvent {
    private String eventType;
    private Product product;
    private LocalDateTime timestamp;public ProductEvent(String eventType, Product product) {
        this.eventType = eventType;
        this.product = product;
        this.timestamp = LocalDateTime.now();
    }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

}
