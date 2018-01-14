package com.client.web.model;

import com.client.domain.Account;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponse {
    private Long id;
    private String nickname;
    private Long profileId;
    private UUID walletUUID;

    public Long getId(){
        return id;
    }
    public String getNickname(){
        return nickname;
    }
    public Long getProfileId(){
        return profileId;
    }
    public UUID getWalletUUID(){
        return walletUUID;
    }

    public AccountResponse(Account account) {
        this.id = account.getId();
        this.nickname = account.getNickname();
        this.profileId = account.getProfile().getId();
        this.walletUUID = account.getUuid();

    }
}