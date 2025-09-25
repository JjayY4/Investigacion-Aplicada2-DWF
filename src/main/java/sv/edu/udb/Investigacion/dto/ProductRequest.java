package sv.edu.udb.Investigacion.dto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductRequest {

    @NotBlank(message = "Nombre obligatorio")
    private String name;

    @NotBlank(message = "Desccripción obligatoria")
    private String description;
    @NotNull(message="El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio tiene que ser >0")
    private BigDecimal price;

    @NotNull(message = "Stock es obligatorio")
    private Integer stock;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}

