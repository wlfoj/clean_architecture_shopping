package com.cart.shopping.product.frameworks.web;

import com.cart.shopping.product.interfaceAdapters.controllers.IProductController;
import com.cart.shopping.product.interfaceAdapters.dtos.request.ProductReqDto;
import com.cart.shopping.product.interfaceAdapters.dtos.response.ProductResDto;
import com.cart.shopping.product.businessRules.exceptions.NegativePrice;
import com.cart.shopping.product.businessRules.exceptions.ProductNotFound;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class WebProductControllerAPI {

    private static final Logger logger = LoggerFactory.getLogger(WebProductControllerAPI.class);
    private final IProductController productController;

    public WebProductControllerAPI(IProductController productController) {
        this.productController = productController;
    }

    @ApiOperation(value = "Lista de produtos", notes = "Retorna todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de produtos"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/product")
    public ResponseEntity<List<ProductResDto>> getAll(){
        logger.trace("getAll method called");
        try {
            List<ProductResDto> _rp = productController.getAll();
            logger.info("List of all products obtained");
            return new ResponseEntity<>(_rp, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Produto especificado", notes = "Retorna o produto com determinado id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto encontrado", response = ProductResDto.class),
            @ApiResponse(code = 404, message = "Produto não encontrado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResDto> getById(@ApiParam(value="id do produto") @PathVariable("id") long id) throws ProductNotFound {
        logger.trace("getById method called with id = {}", id);
        try {
            ProductResDto _p = productController.getById(id);
            logger.info("Product Found with id="+id);
            return new ResponseEntity<>(_p, HttpStatus.OK);
        } catch (ProductNotFound e){
            logger.warn("Product Not Found with id="+id);
            throw new ProductNotFound();
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value="Produto criado", notes = "Cria um e retorna o novo Produto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Produto criado", response = ProductResDto.class),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping("/product")
    public ResponseEntity<ProductResDto> createProduct(@ApiParam(value="Dados do produto") @RequestBody ProductReqDto p) throws NegativePrice {
        logger.trace("createProduct method called for product = {}", p);
        try{
            ProductResDto _rp = productController.createProduct(p);
            logger.info("Product created");
            return new ResponseEntity<>(_rp, HttpStatus.CREATED);
        } catch (NegativePrice e){
            logger.error("Price Negative recived");
            throw new NegativePrice();
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Produto atualizado", notes = "Atualiza e retorna o produto atualizado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto atualizado", response=ProductResDto.class),
            @ApiResponse(code = 404, message = "Produto não encontrado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResDto> updateProduct(@ApiParam(value="id do produto") @PathVariable("id") long id, @ApiParam(value="Novos dados") @RequestBody ProductReqDto p) throws ProductNotFound, NegativePrice {
        logger.trace("updateProduct method called with id={} and new product={}", id, p);
        try{
            ProductResDto _p = productController.updateProduct(id, p);
            logger.info("Product with id="+id+" has been updated");
            return new ResponseEntity<>(_p, HttpStatus.OK);
        } catch (ProductNotFound e){
            logger.warn("Product Not Found with id="+id);
            throw new ProductNotFound();
        } catch (NegativePrice e){
            logger.warn("Price Negative recived");
            throw new NegativePrice();
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value="Deleta produto", notes = "Deleta o produto especificado")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Produto deletado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/product/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@ApiParam(value="id do produto") @PathVariable("id") long id) {
        logger.trace("deleteProduct method called with id={}", id);
        try {
            productController.deleteProduct(id);
            logger.info("Requested Product deletion");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
