package net.akshat.bankingapp.controller;

import net.akshat.bankingapp.dto.AccountDto;
import net.akshat.bankingapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {


    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Add Account Rest API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto  accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //Deposit amount Rest API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> depositAmount(@PathVariable Long id,
                                                    @RequestBody Map<String, Double> request){
        double amount = request.get("amount");
        return new ResponseEntity<>(accountService.deposit(id, amount), HttpStatus.OK);
    }

    //Withdraw amount Rest API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id,
                                                    @RequestBody Map<String, Double> request){
        double amount = request.get("amount");
        return new ResponseEntity<>(accountService.withdraw(id, amount), HttpStatus.OK);
    }

    //Get all Accounts API
    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accountDto = accountService.getAllAccount();
        return ResponseEntity.ok(accountDto);
    }

    //Delete Account by ID API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully!");
    }

    //Create a webhook
    @PostMapping("/webhook-listener")
    public void handleWebhook(@RequestBody String payload, @RequestHeader Map<String, String> headers) {
        // Log the payload and headers for debugging purposes
        System.out.println("Received payload: " + payload);
        System.out.println("Received headers: " + headers);

        // Process the payload as needed

    }




}
