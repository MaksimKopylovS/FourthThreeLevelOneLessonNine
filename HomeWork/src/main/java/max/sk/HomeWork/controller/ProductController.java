package max.sk.HomeWork.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import max.sk.HomeWork.dto.ProductDTO;
import max.sk.HomeWork.model.Product;
import max.sk.HomeWork.repositories.ProductRepository;
import max.sk.HomeWork.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping
    public Page<ProductDTO> findAllProducts(
            @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        if(page < 1){
            page = 1;
        }
        return productService.findAll(page);
    }
    @GetMapping("/{findId}")
    public ProductDTO findProductById(@PathVariable Long findId){
        return productService.findProductById(findId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO saveNewProduct(@RequestBody ProductDTO productDTO){
        productDTO.setId(null);
        return productService.saveOrUpdate(productDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO editProduct(@RequestBody ProductDTO productDTO){
        return productService.editProduct(productDTO);
    }


    @DeleteMapping("/{productId}")
    public void deleteProductById(@PathVariable Long productId){
        productService.deleteProductById(productId);
    }
}
