package com.bank.account.controller;

import com.bank.account.dto.CustomerDto;
import com.bank.account.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /*
    PathVariable : Urlden alır.
    RequestBody : Jsondan alır.
     */

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerId(@PathVariable String customerId )
    {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));


    }
}
