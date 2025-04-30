package com.bank.controller;


import com.bank.dto.AccountDto;
import com.bank.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bank.model.ResponseStatus; // Your custom response class





import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {


    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){

        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);

    }

    //get account by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //deposit amount rest api
    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
        double amount = request.get("amount");
        AccountDto deposited = accountService.deposit(id, amount);

        return ResponseEntity.ok(deposited);
    }

    //withdraw amount rest api
    @PutMapping("/withdraw/{id}")
    public  ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String,Double> req) throws AccountNotFoundException {
        double amount = req.get("amount");
        AccountDto withdraw = accountService.withdraw(id, amount);
        return ResponseEntity.ok(withdraw);
    }

    //get accounts rest api
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> allAccounts = accountService.getAllAccounts();
        return ResponseEntity.ok(allAccounts);
    }

        @DeleteMapping("/{id}")
        public ResponseEntity<ResponseStatus> deleteAccount(@PathVariable Long id) throws AccountNotFoundException {
          ResponseStatus responseStatus = accountService.deleteAccount(id);
            return ResponseEntity.ok()
                    .body(responseStatus);
        }

}
