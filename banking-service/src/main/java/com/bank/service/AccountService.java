package com.bank.service;

import com.bank.dto.AccountDto;
import com.bank.entity.TransactionLog;
import com.bank.model.BankCreditRequest;
import com.bank.model.BankDebitRequest;
import com.bank.model.ResponseStatus;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, Double amount);

    AccountDto withdraw(Long id, Double amount) throws AccountNotFoundException;

    List<AccountDto> getAllAccounts();

    ResponseStatus deleteAccount(Long id) throws AccountNotFoundException;

    List<TransactionLog> getAllTransactionLogs();

    List<TransactionLog> getTransactionLogsByAccountId(Long accountId) throws AccountNotFoundException;

    ResponseStatus saveDebitPurchase(BankDebitRequest request);

    ResponseStatus saveCreditPurchase(BankCreditRequest request);

}
