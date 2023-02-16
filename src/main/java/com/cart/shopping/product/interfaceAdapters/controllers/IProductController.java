package com.cart.shopping.product.interfaceAdapters.controllers;

import com.cart.shopping.product.interfaceAdapters.dtos.request.ProductReqDto;
import com.cart.shopping.product.interfaceAdapters.dtos.response.ProductResDto;
import com.cart.shopping.product.businessRules.exceptions.NegativePrice;
import com.cart.shopping.product.businessRules.exceptions.ProductNotFound;

import java.util.List;

public interface IProductController {
    public List<ProductResDto> getAll();
    public ProductResDto getById(long id) throws ProductNotFound;
    public ProductResDto createProduct(ProductReqDto p) throws NegativePrice;
    public ProductResDto updateProduct(long id, ProductReqDto p) throws NegativePrice, ProductNotFound;
    public void deleteProduct(long id);
}
