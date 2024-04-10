package com.ravi.inventoryservice.service;

import com.ravi.inventoryservice.dto.InventoryResponse;
import com.ravi.inventoryservice.repository.Inventoryrepsoitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class InventoryService {

    private final Inventoryrepsoitory inventoryrepsoitory;
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
       return  inventoryrepsoitory.findBySkuCodeIn(skuCode).stream()
               .map(inventory ->
                   InventoryResponse.builder().skuCode(inventory.getSkuCode())
                           .isInStock(inventory.getQuantity()>0)
                           .build()
               )
               .toList();

    }
}
