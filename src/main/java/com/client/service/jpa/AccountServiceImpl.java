package com.client.service.jpa;

import com.client.domain.Account;
import com.client.domain.Profile;
import com.client.repository.AccountRepository;
import com.client.service.AccountService;
import com.client.web.model.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final ProfileServiceImpl profileService;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, ProfileServiceImpl profileService) {
        this.accountRepository = accountRepository;
        this.profileService = profileService;
    }

   /* @Override
    @Transactional(readOnly = true)
    public Iterable<Account> getAccounts() {
        return accountRepository.findAll();
    }*/

    @Override
    @Transactional
    public Account save(AccountRequest accountRequest) {
        Account account = new Account()
                .setNickname(accountRequest.getNickname())
                .setUuid(accountRequest.getWalletUUID())
                .setPassword(accountRequest.getPassword());

        Profile profile = profileService.getById(accountRequest.getProfileId());
        if( profile != null) {
            account.setProfile(profile);
        }

        return accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Account getById(Integer accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account '{" + accountId + "}' not found"));
    }


    @Override
    @Transactional
    public Account update(Integer accountId, AccountRequest accountRequest) {
        Account account = this.getById(accountId);
        if (account == null) {
            throw new EntityNotFoundException("Account '{" + accountId + "}' not found");
        }
        account.setNickname(accountRequest.getNickname() != null ? accountRequest.getNickname() : account.getNickname());
        account.setPassword(accountRequest.getPassword() != null ? accountRequest.getPassword() : account.getPassword());
        return accountRepository.save(account);
    }

    @Override
    public Account getByNickname(String name) {
        if (accountRepository.findAccountByNickname(name) == null){
            throw new EntityNotFoundException("Account '{" + name + "}' not found");
        }
        return accountRepository.findAccountByNickname(name);

    }

    /* @Override
    @Transactional
    public void delete(Integer accountId) {
        accountRepository.deleteById(accountId);
    }*/

   /* @Override
    public Account addProfile(Integer accountId, Integer profileId) {
        Account account = this.getById(accountId);
        Profile profile = profileService.getById(profileId);
        account.setProfile(profile);
        return accountRepository.save(account);
    }*/

}