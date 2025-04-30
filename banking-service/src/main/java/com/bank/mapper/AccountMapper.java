package com.bank.mapper;

import com.bank.dto.AccountDto;
import com.bank.entity.Account;

import java.util.ArrayList;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto) {
        Account account1 = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance(),
                new ArrayList<>()
        );

        return account1;
    }

    public static AccountDto mapToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );

        return accountDto;
    }

}
