package sv.edu.udb.Investigacion.controller;
import sv.edu.udb.Investigacion.dto.ProductRequest;
import sv.edu.udb.Investigacion.dto.ProductResponse;
import sv.edu.udb.Investigacion.dto.ApiResponse;
import sv.edu.udb.Investigacion.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(
            @Valid @RequestBody ProductRequest request) {
        ProductResponse product = productService.createProduct(request);
        ApiResponse<ProductResponse> response = new ApiResponse<>(
                true, "Product created successfully", product, "/api/products");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        ApiResponse<List<ProductResponse>> response = new ApiResponse<>(
                true, "Products retrieved successfully", products, "/api/products");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long id) {
        ProductResponse product = productService.getProductById(id);
        ApiResponse<ProductResponse> response = new ApiResponse<>(
                true, "Product retrieved successfully", product, "/api/products/" + id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {
        ProductResponse product = productService.updateProduct(id, request);
        ApiResponse<ProductResponse> response = new ApiResponse<>(
                true, "Product updated successfully", product, "/api/products/" + id);
        return ResponseEntity.ok(response);
    }@DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        ApiResponse<Void> response = new ApiResponse<>(
                true, "Product deleted successfully", null, "/api/products/" + id);
        return ResponseEntity.ok(response);
    }




}
