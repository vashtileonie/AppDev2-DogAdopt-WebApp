package com.rijai.LocationApi.service;

import com.rijai.LocationApi.model.Account;
import com.rijai.LocationApi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> findAll() {
        return (List<Account>) accountRepository.findAll();
    }

    @Override
    public Account getAccount(Long id) {
        Optional optional=accountRepository.findById(id);
        if(optional.isEmpty())
            return null;
        else
            return (Account) optional.get();
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(Long id, Account account) {
        return accountRepository.save(account);
    }

    public Account findById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElse(null);
    }

    public void deleteAccount (Long id) {
        Optional<Account> account = accountRepository.findById(id);
        account.ifPresent(value -> accountRepository.delete(value));
    }
}
