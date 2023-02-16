package com.cart.shopping.product.frameworks.repositoryImp;

import com.cart.shopping.product.businessRules.entities.Product;
import com.cart.shopping.product.frameworks.entitiesJPA.ProductEntityJPA;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    /** De ProductEntityJPA para Product */
    public static Product toProduct(ProductEntityJPA entity) {
        Product data = new Product();
        data.setId(entity.getId());
        data.setName(entity.getName());
        data.setPrice(entity.getPrice());
        return data;
    }

    public static List<Product> toProductList(List<ProductEntityJPA> listjpa){
        List<Product> list = new ArrayList<Product>();
        for (ProductEntityJPA el: listjpa) {
            list.add(toProduct(el));
        }
        return list;
    }

    /** De Product para ProductEntityJPA */
    public static ProductEntityJPA toProductJPA(Product data) {
        ProductEntityJPA entity = new ProductEntityJPA();
        entity.setId(data.getId());
        entity.setName(data.getName());
        entity.setPrice(data.getPrice());
        return entity;
    }
}
