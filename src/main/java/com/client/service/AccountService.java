package com.client.service;

import com.client.domain.Account;
import com.client.web.model.AccountRequest;

public interface AccountService {
  /*  Iterable<Account> getAccounts();*/
    Account getById(Integer accountId);
    Account getByNickname(String name);
    Account save(AccountRequest accountRequest);
    Account update(Integer accountId, AccountRequest accountRequest);
  /*  void delete(Integer accountId);*/
   /* Account addProfile(Integer accountId, Integer profileId);*/

}