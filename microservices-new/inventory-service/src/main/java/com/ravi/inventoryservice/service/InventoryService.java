package com.ravi.inventoryservice.service;

import com.ravi.inventoryservice.dto.InventoryResponse;
import com.ravi.inventoryservice.dto.ProductResponse;
import com.ravi.inventoryservice.repository.Inventoryrepsoitory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor

public class InventoryService {

    private final Inventoryrepsoitory inventoryrepsoitory;
    private final RestTemplate restTemplate;

    @Value("${http://product-service/}")
    private String productServiceURl;



    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCodes){
        ProductResponse[] products = restTemplate.getForObject(productServiceURl+"api/products/bySkuCodes?skuCodes={skuCodes}", ProductResponse[].class, String.join(",", skuCodes));
        return  inventoryrepsoitory.findBySkuCodeIn(skuCodes).stream()
                .map(inventory ->{
                    boolean isInStock = inventory.getQuantity() > 0;
                    String productId = String.valueOf(inventory.getId());
                    ProductResponse product = getProductById(products, productId);
                    // Check if product is null before accessing its properties
                    return InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(isInStock)
                            .productName(product != null ? product.getName() : "")
                            .productDescription(product != null ? product.getDescription() : "")
                            .productPrice(product != null ? product.getPrice() : null)
                            .build();
                })
                .toList();
    }

    private ProductResponse getProductById(ProductResponse[] products, String productId) {
        if (products != null && productId != null) {
            for (ProductResponse product : products) {
                if (productId.equals(product.getId())) {
                    return product;
                }
            }
        }
        return null;
    }
}
