package com.bank.account.service;

import com.bank.account.dto.AccountDto;
import com.bank.account.dto.CreateAccountRequest;
import com.bank.account.dto.CustomerDto;
import com.bank.account.dto.CustomerDtoConverter;
import com.bank.account.exception.CustomerNotFoundException;
import com.bank.account.model.Account;
import com.bank.account.model.Customer;
import com.bank.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
private final CustomerRepository customerRepository;
private final CustomerDtoConverter customerDtoConverter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    //Serviceler arasında iletişim yaparken Entity kullanılabilir.
    //protected sadece paket içinde kullanılır.
    protected Customer findByCustomerById(String id)
    {
        return customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer could not find by id: "+id));
    }


    public CustomerDto getCustomerById(String customerId) {

        return customerDtoConverter.convertToCustomerDto(findByCustomerById(customerId));
    }
}

