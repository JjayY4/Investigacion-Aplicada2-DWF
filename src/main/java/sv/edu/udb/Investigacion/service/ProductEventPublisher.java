package sv.edu.udb.Investigacion.service;

import sv.edu.udb.Investigacion.event.ProductEvent;
import sv.edu.udb.Investigacion.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component

public class ProductEventPublisher {
    private static final String TOPIC = "product-events";

    @Autowired
    private KafkaTemplate<String, ProductEvent> kafkaTemplate;

    @Async
    public void publishProductEvent(String eventType, Product product) {
        try {
            ProductEvent event = new ProductEvent(eventType, product);
            kafkaTemplate.send(TOPIC, eventType, event);
            System.out.println("Evento publicado" + eventType + ": " + product.getName());
        } catch (Exception e) {
            System.err.println("Error publicando el evento" + e.getMessage());
        }
    }
}
