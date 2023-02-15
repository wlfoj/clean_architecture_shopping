package com.cart.shopping.product.businessRules.entities;

public class Product {
    private long id;
    private String name;
    private float price;

    public Product(){}

    public Product(long id, String name, float price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

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


    public boolean isValidPrice(){
        if(this.price>=0){
            return true;
        }
        else{
            return false;
        }
    }
}
