package com.ravi.productservice.repository;

import com.ravi.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {

    List<Product> findBySkuCodeIn(List<String> skuCodes);
}
