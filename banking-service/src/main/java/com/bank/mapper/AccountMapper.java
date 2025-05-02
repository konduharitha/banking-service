package com.bank.mapper;

import com.bank.dto.AccountDto;
import com.bank.entity.Account;

import java.util.ArrayList;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto) {
        return Account.builder()
                .id(accountDto.getId())
                .accountHolderName(accountDto.getAccountHolderName())
                .balance(accountDto.getBalance())
                .build();
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
