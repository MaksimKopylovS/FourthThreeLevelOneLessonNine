package max.sk.HomeWork.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import max.sk.HomeWork.model.Product;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long id;

    private String title;

    private int cost;

    public ProductDTO(Product product){
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost= product.getCost();
    }
}
