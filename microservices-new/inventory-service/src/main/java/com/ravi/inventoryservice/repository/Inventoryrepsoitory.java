package com.ravi.inventoryservice.repository;

import com.ravi.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Inventoryrepsoitory  extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findBySkuCode(String skuCode);
}
