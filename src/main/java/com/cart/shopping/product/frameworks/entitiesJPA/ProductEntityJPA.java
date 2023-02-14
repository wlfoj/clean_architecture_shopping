package com.cart.shopping.product.frameworks.entitiesJPA;

import com.cart.shopping.product.businessRules.entities.Product;

import javax.persistence.*;

/**Só realizo a extensão para inverter a depêndencia do presenter ao converter O productJPA para product
 *
 */
@Entity
public class ProductEntityJPA extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Product_ID")
    private long id;

    @Column(name = "Product_Name")
    private String name;

    @Column(name = "Product_Price")
    private float price;

}
