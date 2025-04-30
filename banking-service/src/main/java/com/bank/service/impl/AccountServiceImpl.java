package com.bank.service.impl;

import com.bank.dto.AccountDto;
import com.bank.entity.Account;
import com.bank.exception.InsufficientBalanceException;
import com.bank.mapper.AccountMapper;
import com.bank.model.ResponseStatus;
import com.bank.repository.AccountRepository;
import com.bank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;





import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account saved = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saved);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account doesn't found"));

        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, Double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account doesn't found"));
        double total = account.getBalance() + amount;

        account.setBalance(total);

        Account saved = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(saved);
    }

    @Override
    public AccountDto withdraw(Long id, Double amount) throws AccountNotFoundException {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account doesn't exist"));

        if(account.getBalance() < amount){
            throw new InsufficientBalanceException("user does not have sufficient balance");
        }else {

            double total = account.getBalance() - amount;
            account.setBalance(total);
            Account saved = accountRepository.save(account);
            return AccountMapper.mapToAccountDto(saved);
        }

    }


    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream()
                .map((AccountMapper::mapToAccountDto))
                .toList();
    }

    @Override
    public ResponseStatus deleteAccount(Long id) throws AccountNotFoundException {
        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException("Account doesn't exist");
        }
        accountRepository.deleteById(id);

        return ResponseStatus.builder()
                .isSuccess(Boolean.TRUE)
                .message("Successfully deleted user by id : " + id)
                .build();

    }


}
