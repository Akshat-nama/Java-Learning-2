package net.akshat.bankingapp.service.impl;

import net.akshat.bankingapp.dto.AccountDto;
import net.akshat.bankingapp.mapper.AccountMapper;
import net.akshat.bankingapp.entity.Account;
import net.akshat.bankingapp.repository.AccountRepository;
import net.akshat.bankingapp.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account Does not Exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {

//        AccountDto currentAccount = getAccountById(id);
//        currentAccount.setBalance(currentAccount.getBalance() + amount);
//        Account account = AccountMapper.mapToAccount(currentAccount);
//        Account savedAccount = accountRepository.save(account);
//        return AccountMapper.mapToAccountDto(savedAccount);

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account Does not Exist"));

        account.setBalance(account.getBalance() + amount);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);

    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account Does not Exist"));

        double total = account.getBalance() - amount;
        if(total>=0){
            account.setBalance(total);
            Account savedAccount = accountRepository.save(account);
            return AccountMapper.mapToAccountDto(savedAccount);
        }else{
            throw new RuntimeException("Withdrawing ammount is greater then the current balance. Please enter valid amount to withdraw");
        }


    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account Does not Exist"));
        accountRepository.deleteById(id);
    }
}
