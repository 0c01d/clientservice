package com.client.web.controller;

import com.client.domain.Account;
import com.client.service.AccountService;
import com.client.web.model.AccountRequest;
import com.client.web.model.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

   /* @RequestMapping(method = RequestMethod.GET)
    public ArrayList<AccountResponse> getAccounts() {
        ArrayList<AccountResponse> accountResponses = new ArrayList<>();
        accountService.getAccounts().forEach(account -> accountResponses.add(new AccountResponse(account)));
        return accountResponses;
    }*/

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public AccountResponse createAccount(@Valid @RequestBody AccountRequest accountRequest, HttpServletResponse response) {
        Account account = accountService.save(accountRequest);
        response.addHeader(HttpHeaders.LOCATION, "/account/" + account.getId());
        return new AccountResponse(account);
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    public AccountResponse getAccountById(@PathVariable("accountId") Integer accountId) {
        return new AccountResponse(accountService.getById(accountId));
    }

    @RequestMapping(value = "/name/{accountName}", method = RequestMethod.GET)
    public AccountResponse getAccountByName(@PathVariable("accountName") String accountName) {
        return new AccountResponse(accountService.getByNickname(accountName));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{accountId}", method = RequestMethod.PATCH)
    public AccountResponse updateAccount(@PathVariable("accountId") Integer accountId,@Valid @RequestBody AccountRequest accountRequest) {
        return new AccountResponse(accountService.update(accountId, accountRequest));
    }

  /*  @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{accountId}", method = RequestMethod.DELETE)
    public void deleteAccount(@PathVariable Integer accountId) {
        accountService.delete(accountId);
    }*/


}