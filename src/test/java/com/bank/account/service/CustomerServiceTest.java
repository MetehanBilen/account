package com.bank.account.service;

import com.bank.account.dto.CustomerDto;
import com.bank.account.dto.CustomerDtoConverter;
import com.bank.account.exception.CustomerNotFoundException;
import com.bank.account.model.Customer;
import com.bank.account.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


public class CustomerServiceTest {

    private CustomerService service;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter converter;

    @BeforeEach
    public void  setUp(){
        customerRepository = mock(CustomerRepository.class);
        converter = mock(CustomerDtoConverter.class);
        service = new CustomerService(customerRepository,converter);
    }

    @Test
    public  void testFindByCustomerId_whenCustomerIdExtists_shouldReturnCustomer(){
        Customer customer = new Customer("id","name","surname", Set.of());


        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));

        Customer result = service.findByCustomerById("id");

       assertEquals(result,customer);
    }

    @Test
    public  void testFindByCustomerId_whenCustomerIdDoesNotExtists_shouldThrowCustomerNotFoundException(){


        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());

       assertThrows(CustomerNotFoundException.class, () -> service.findByCustomerById("id"));

    }

    @Test
    public  void testGetCustomerById_whenCustomerIdExtists_shouldReturnCustomer(){
        Customer customer = new Customer("id","name","surname", Set.of());
        CustomerDto customerDto = new CustomerDto("id","name","surname", Set.of());

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        Mockito.when(converter.convertToCustomerDto(customer)).thenReturn(customerDto);

        CustomerDto result = service.getCustomerById("id");

        assertEquals(result,customerDto);
    }

    @Test
    public  void testGetCustomerById_whenCustomerIdDoesNotExtists_shouldThrowCustomerNotFoundException(){

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());


        assertThrows(CustomerNotFoundException.class,
                ()-> service.getCustomerById("id"));


         Mockito.verifyNoInteractions(converter); // converter'ın hiçbir methodu çağırılmayacak.

    }

}