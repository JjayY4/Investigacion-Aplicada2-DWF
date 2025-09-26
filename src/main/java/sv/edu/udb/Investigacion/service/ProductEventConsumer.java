package sv.edu.udb.Investigacion.service;

import sv.edu.udb.Investigacion.event.ProductEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ProductEventConsumer {

    @Async
    @KafkaListener(topics = "product-events", groupId = "product-group")
    public void consumeProductEvent(ProductEvent event) {
        System.out.println("Received event: " + event.getEventType());
        System.out.println("Product: " + event.getProduct().getName());
        System.out.println("Timestamp: " + event.getTimestamp());
        processEventAsync(event);
    }

    @Async
    public void processEventAsync(ProductEvent event) {
        try {
            Thread.sleep(1000);
            switch (event.getEventType()) {
                case "Producto creado":
                    System.out.println("Producto Creado: " + event.getProduct().getName());
                    break;
                case "Producto actualizado":
                    System.out.println("Producto Actualizado: " + event.getProduct().getName());
                    break;
                case "Producto borrado":
                    System.out.println("Producto Borrado: " + event.getProduct().getName());
                    break;
                default:
                    System.out.println("Evento desconocido: " + event.getEventType());
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
