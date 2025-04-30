package com.bank.service;

import com.bank.dto.AccountDto;
import com.bank.model.ResponseStatus;
import org.springframework.http.ResponseEntity;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, Double amount);

    AccountDto withdraw(Long id, Double amount) throws AccountNotFoundException;

    List<AccountDto> getAllAccounts();

   ResponseStatus deleteAccount(Long id) throws AccountNotFoundException;

}
