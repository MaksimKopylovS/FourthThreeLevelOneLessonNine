package max.sk.HomeWork.services;

import max.sk.HomeWork.dto.ProductDTO;
import max.sk.HomeWork.model.Product;
import max.sk.HomeWork.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Page<ProductDTO> findAll(int page) {
        Page<Product> pageProduct = productRepository.findAll(PageRequest.of(page -1, 5));
        return new PageImpl<>(pageProduct.getContent().stream().map(ProductDTO::new).collect(Collectors.toList()), pageProduct.getPageable(), pageProduct.getTotalElements());
    }

    public ProductDTO saveOrUpdate(ProductDTO productDTO) {
        productRepository.save(new Product(productDTO));
        return productDTO;
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDTO findProductById(Long findId){
        Product product = productRepository.getOne(findId);
        return new ProductDTO(product);
    }

    public ProductDTO editProduct(ProductDTO productDTO){
        Product product = productRepository.getOne(productDTO.getId());
        productRepository.save(new Product(
                productDTO.getId(),
                productDTO.getTitle(),
                productDTO.getCost(),
                product.getCreateAt(),
                new Date()
        ));
        return productDTO;
    }


}
