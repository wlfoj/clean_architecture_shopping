package com.cart.shopping.product.adapters.controllers;

import com.cart.shopping.product.adapters.dtos.request.ProductReqDto;
import com.cart.shopping.product.adapters.dtos.response.ProductResDto;
import com.cart.shopping.product.adapters.presenters.ProductPresenter;
import com.cart.shopping.product.businessRules.entities.Product;
import com.cart.shopping.product.businessRules.exceptions.NegativePrice;
import com.cart.shopping.product.businessRules.useCases.IProductService;

import java.util.List;

public class ProductController implements IProductController {
    private final IProductService productService;

    public ProductController(IProductService productService){
        this.productService = productService;
    }

    @Override
    public List<ProductResDto> getAll(){
        return ProductPresenter.listProductToDto(productService.getAll());
    }

    @Override
    public ProductResDto getById(long id){
        Product product = productService.getById(id);
        return ProductPresenter.productToDto(product);
    }

    @Override
    public ProductResDto createProduct(ProductReqDto p) throws NegativePrice {
        // Converte o dto de request em model
        Product product = ProductPresenter.dtoToProduct(p);
        // Salva o produto e pega a resposta
        Product resp = productService.addProduct(product);
        // Converte a resposta model para dto response e envia
        return ProductPresenter.productToDto(resp);
    }

    @Override
    public ProductResDto updateProduct(long id, ProductReqDto p) throws NegativePrice {
        // Converte o dto de request em model
        Product product = ProductPresenter.dtoToProduct(p);
        // edita o produto e pega a resposta
        Product resp = productService.edit(id, product);
        // Converte a resposta model para dto response e envia
        return ProductPresenter.productToDto(resp);
    }

    @Override
    public void DeleteProduct(long id){
        productService.deleteById(id);
    }
}
