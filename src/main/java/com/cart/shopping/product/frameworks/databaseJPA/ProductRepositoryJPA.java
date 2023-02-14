package com.cart.shopping.product.frameworks.databaseJPA;

import com.cart.shopping.product.frameworks.entitiesJPA.ProductEntityJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryJPA extends JpaRepository<ProductEntityJPA, Long> { }

