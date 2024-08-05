package com.rijai.LocationApi.controller;

import com.rijai.LocationApi.model.Account;
import com.rijai.LocationApi.service.IAccountService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @RequestMapping(value="/api/create-account", method= RequestMethod.POST)
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping(value = "/api/accounts")
    public List<Account> showAllAccount() {
        return accountService.findAll();
    }

    @GetMapping(value = "/api/show-account/{id}")
    public Account showAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @RequestMapping(value="/api/update-account/{id}", method=RequestMethod.PUT)
    public Account updateAccount(@PathVariable Long id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }
    @RequestMapping(value = "/api/delete-account/{id}", method = {RequestMethod.DELETE, RequestMethod.POST})
    public void deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
    }

}
