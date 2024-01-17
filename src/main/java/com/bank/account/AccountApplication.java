package com.bank.account;

import com.bank.account.model.Account;
import com.bank.account.model.Customer;
import com.bank.account.model.Transaction;
import com.bank.account.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.util.HashSet;
/*
application.properties-test yaptıktan sonra
@ActiveProfiles("application.properties-test")
olarak kullanabilirsin.
*/
@SpringBootApplication
@ComponentScan(basePackages = {"com.bank.account"})
public class AccountApplication implements CommandLineRunner {

	private final CustomerRepository customerRepository;

    public AccountApplication(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public static void main(String[] args) {SpringApplication.run(AccountApplication.class, args);}



	@Override
	public void run(String... args) throws Exception {
		Customer customer =customerRepository.save(new Customer("","mt","bln",new HashSet<>()));
		System.out.println(customer);

	}
}
