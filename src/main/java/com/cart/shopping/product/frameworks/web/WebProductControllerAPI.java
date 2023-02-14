package com.cart.shopping.product.frameworks.web;

import com.cart.shopping.product.adapters.controllers.IProductController;
import com.cart.shopping.product.adapters.dtos.request.ProductReqDto;
import com.cart.shopping.product.adapters.dtos.response.ProductResDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class WebProductControllerAPI {
    private final IProductController productController;

    public WebProductControllerAPI(IProductController productController) {
        this.productController = productController;
    }

    @ApiOperation(value = "Lista todos produtos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de produtos"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/product")
    public ResponseEntity<List<ProductResDto>> getAll(){
        try {
            List<ProductResDto> _rp = productController.getAll();
            return new ResponseEntity<>(_rp, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Retorna o produto com determinado id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto encontrado"),
            @ApiResponse(code = 404, message = "Produto não encontrado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResDto> getById(@PathVariable("id") long id){
        try {
            ProductResDto _p = productController.getById(id);
            //Se tiver achado
            if (_p != null) {
                return new ResponseEntity<>(_p, HttpStatus.OK);
            }
            //se não achar
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Cria um Produto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Produto criado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping("/product")
    public ResponseEntity<ProductResDto> createProduct(@RequestBody ProductReqDto p) {
        try {
            ProductResDto _rp = productController.createProduct(p);
            return new ResponseEntity<>(_rp, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Atualiza o dados do produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto atualizado"),
            @ApiResponse(code = 404, message = "Produto não encontrado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResDto> updateProduct(@PathVariable("id") long id, @RequestBody ProductReqDto p){
        try {
            ProductResDto _p = productController.updateProduct(id, p);
            if (_p != null) {
                return new ResponseEntity<>(_p, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Deleta produto com o id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Produto deletado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/product/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
        try {
            productController.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
