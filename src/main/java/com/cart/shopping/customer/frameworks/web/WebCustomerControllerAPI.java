package com.cart.shopping.customer.frameworks.web;

import com.cart.shopping.customer.businessRules.exceptions.CustomerNotFound;
import com.cart.shopping.customer.businessRules.exceptions.EmailInvalid;
import com.cart.shopping.customer.interfaceAdapters.controllers.ICustomerController;
import com.cart.shopping.customer.interfaceAdapters.dtos.request.CustomerReqDto;
import com.cart.shopping.customer.interfaceAdapters.dtos.response.CustomerResDto;
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
public class WebCustomerControllerAPI {
    private static final Logger logger = LoggerFactory.getLogger(WebCustomerControllerAPI.class);
    private final ICustomerController customerController;

    public WebCustomerControllerAPI(ICustomerController customerController) {
        this.customerController = customerController;
    }

    @ApiOperation(value = "Lista de clientes", notes = "Retorna todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de clientes"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/customer")
    public ResponseEntity<List<CustomerResDto>> getAll(){
        logger.trace("getAll method called");
        try {
            List<CustomerResDto> _cd = customerController.getAll();
            logger.info("List of all customers obtained");
            return new ResponseEntity<>(_cd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Cliente especificado", notes = "Retorna o cliente com determinado id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente encontrado", response = CustomerResDto.class),
            @ApiResponse(code = 404, message = "Cliente não encontrado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerResDto> getById(@ApiParam(value="id do cliente") @PathVariable("id") long id) throws CustomerNotFound {
        logger.trace("getById method called with id = {}", id);
        try {
            CustomerResDto _c = customerController.getById(id);
            logger.info("Customer Found with id="+id);
            return new ResponseEntity<>(_c, HttpStatus.OK);
        } catch (CustomerNotFound e){
            logger.warn("Customer Not Found with id="+id);
            throw new CustomerNotFound();
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value="Cliente criado", notes = "Registra um novo Cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente registrado", response = CustomerResDto.class),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping("/customer")
    public ResponseEntity<CustomerResDto> createCustomer(@ApiParam(value="Dados do cliente") @RequestBody CustomerReqDto c) throws EmailInvalid {
        logger.trace("createCustomer method called for product = {}", c);
        try{
            CustomerResDto _c = customerController.createCustomer(c);
            logger.info("Customer created");
            return new ResponseEntity<>(_c, HttpStatus.CREATED);
        } catch (EmailInvalid e){
            logger.error("Invalid Email recived");
            throw new EmailInvalid();
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Atualizar cliente", notes = "Atualiza e retorna o cliente atualizado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente atualizado", response=CustomerResDto.class),
            @ApiResponse(code = 404, message = "Cliente não encontrado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/customer/{id}")
    public ResponseEntity<CustomerResDto> updateCustomer(@ApiParam(value="id do cliente") @PathVariable("id") long id, @ApiParam(value="Novos dados") @RequestBody CustomerReqDto c) throws CustomerNotFound, EmailInvalid {
        logger.trace("updateCustomer method called with id={} and new customer={}", id, c);
        try{
            CustomerResDto _c = customerController.updateCustomer(id, c);
            logger.info("Customer with id="+id+" has been updated");
            return new ResponseEntity<>(_c, HttpStatus.OK);
        } catch (CustomerNotFound e){
            logger.warn("Customer Not Found with id="+id);
            throw new CustomerNotFound();
        } catch (EmailInvalid e){
            logger.warn("Email Invalid recived");
            throw new EmailInvalid();
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value="Deleta cliente", notes = "Deleta o cliente especificado")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cliente deletado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@ApiParam(value="id do cliente") @PathVariable("id") long id) throws CustomerNotFound {
        logger.trace("deleteCustomer method called with id={}", id);
        try {
            customerController.deleteCustomer(id);
            logger.info("Requested Customer deletion");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFound e) {
            throw new CustomerNotFound();
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
