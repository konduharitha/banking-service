package com.bank.service.impl;

import com.bank.dto.AccountDto;
import com.bank.entity.Account;
import com.bank.mapper.AccountMapper;
import com.bank.repository.AccountRepository;
import com.bank.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

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
}
