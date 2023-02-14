package com.cart.shopping.product.adapters.presenters;

import com.cart.shopping.product.adapters.dtos.request.ProductReqDto;
import com.cart.shopping.product.adapters.dtos.response.ProductResDto;
import com.cart.shopping.product.businessRules.entities.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Talvez usar factorys para remover o new
 * */
public class ProductPresenter {

    /**Converte o produto do model para o produto de dto de response
     * */
    public static ProductResDto productToDto(Product product) {
        ProductResDto dto = new ProductResDto();
        dto.id = product.getId();
        dto.name = product.getName();
        dto.price = product.getPrice();
        return dto;
    }


    /**Converte o produto dto request para o produto model
     * */
    public static Product dtoToProduct(ProductReqDto prodDto){
        Product product = new Product();
        product.setName(prodDto.name);
        product.setPrice(prodDto.price);
        return product;
    }

    /**Converte uma lista de produto model em uma lista de produto dto response
     */
    public static List<ProductResDto> listProductToDto(List<Product> listProduct){
        List<ProductResDto> list = new ArrayList<ProductResDto>();
        for (Product el:listProduct) {
            list.add(productToDto(el));
        }
        return list;
    }

    /**Converte a Entity JPA em Entity
     *
     */


    /**Converte a Entity em Entity JPA
     *
     */
}
