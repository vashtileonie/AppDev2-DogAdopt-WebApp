package com.rijai.LocationApi.service;

import com.rijai.LocationApi.model.Account;

import java.util.List;


public interface IAccountService {

    List<Account> findAll();
    Account createAccount(Account account);
    Account updateAccount(Long id, Account account);
    void deleteAccount(Long id);
    Account findById(Long id);
    Account getAccount(Long id);
}