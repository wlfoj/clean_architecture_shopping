package com.cart.shopping.product.frameworks.entitiesJPA;

import com.cart.shopping.product.businessRules.entities.Product;

import javax.persistence.*;

@Entity
public class ProductEntityJPA{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Product_ID")
    private long id;

    @Column(name = "Product_Name")
    private String name;

    @Column(name = "Product_Price")
    private float price;


    /** Construtores */
    public ProductEntityJPA(){}
    public ProductEntityJPA(Product p){
        this.id = p.getId();
        this.name = p.getName();
        this.price = p.getPrice();
    }

    //Setters e Getters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
