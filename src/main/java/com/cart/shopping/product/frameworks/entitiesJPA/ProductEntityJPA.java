package com.cart.shopping.product.frameworks.entitiesJPA;

import com.cart.shopping.product.businessRules.entities.Product;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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


    /** Construtores */
    public ProductEntityJPA(){

    }
    public ProductEntityJPA(Product p){
        this.name = p.getName();
        this.price = p.getPrice();
    }

    /** Converte a Entidade JPA para o model */
    public Product toProduct(){
        return new Product(this.id, this.name, this.price);
    }
    /** Converte uma lista de Entidades JPA em uma lista de model */
    public static List<Product> toProductList(List<ProductEntityJPA> listjpa){
        List<Product> list = new ArrayList<Product>();
        for (ProductEntityJPA el: listjpa) {
            list.add(el.toProduct());
        }
        return list;
    }

    /** Utiliza o bean utils para converter
    public ProductEntityJPA convert(Product product) {
        BeanUtils.copyProperties(product, this, getClass());
        return this;
    }*/
}
