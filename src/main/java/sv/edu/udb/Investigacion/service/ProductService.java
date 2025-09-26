package sv.edu.udb.Investigacion.service;
import sv.edu.udb.Investigacion.model.Product;
import sv.edu.udb.Investigacion.dto.ProductRequest;
import sv.edu.udb.Investigacion.dto.ProductResponse;
import sv.edu.udb.Investigacion.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    @Autowired
private ProductRepository productRepository;

    @Autowired
    private ProductEventPublisher eventPublisher;

    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        Product savedProduct = productRepository.save(product);
        eventPublisher.publishProductEvent("Producto creado", savedProduct);
        return mapToResponse(savedProduct);
}
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
@Transactional(readOnly = true)
public ProductResponse getProductById(Long id) {
    Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado por su id:"+id));
    return mapToResponse(product);
}
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado por id:" + id));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());


        Product updatedProduct = productRepository.save(product);
        eventPublisher.publishProductEvent("Producto actualizadoo", updatedProduct);
        return mapToResponse(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado por su id" + id));
        productRepository.deleteById(id);
        eventPublisher.publishProductEvent("Producto borrado", product);
    }

    private ProductResponse mapToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());
        return response;
    }

}



