package com.bank.account.controller;

import com.bank.account.dto.AccountDto;
import com.bank.account.dto.CreateAccountRequest;
import com.bank.account.service.AccountService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/account")
public class AccountController {

    private  final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //post db ekleme yapar put güncelleme yapar
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody CreateAccountRequest request)
    {
        return  ResponseEntity.ok(accountService.createAccount(request));
    }
}
