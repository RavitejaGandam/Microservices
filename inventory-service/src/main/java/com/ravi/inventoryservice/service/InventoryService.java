package com.ravi.inventoryservice.service;

import com.ravi.inventoryservice.repository.Inventoryrepsoitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class InventoryService {

    private final Inventoryrepsoitory inventoryrepsoitory;
    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode){
       return  inventoryrepsoitory.findBySkuCode(skuCode).isPresent();

    }
}
