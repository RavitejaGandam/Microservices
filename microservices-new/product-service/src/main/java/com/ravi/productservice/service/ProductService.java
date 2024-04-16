package com.ravi.productservice.service;

import com.ravi.productservice.dto.ProductRequest;
import com.ravi.productservice.dto.ProductResponse;
import com.ravi.productservice.model.Product;
import com.ravi.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

   private final ProductRepository productRepository;

//    public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                //.skuCode(productRequest.getSkuCode())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);


        log.info("product {} is Saved",product.getId());
    }

    public List<ProductResponse> getAllProducts() {
      List <Product> products =  productRepository.findAll();
       return products.stream().map(this::maptoProductResponse).toList();
    }

    private ProductResponse maptoProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
    public List<ProductResponse> getProductsBySkuCodes(List<String> skuCode){
        return productRepository.findBySkuCodeIn(skuCode).stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
