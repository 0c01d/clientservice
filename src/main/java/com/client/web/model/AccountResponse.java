package com.client.web.model;

import com.client.domain.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AccountResponse {
    private Integer id;
    private String nickname;
    private Integer profileId;
    private UUID walletUUID;

    public AccountResponse(Account account) {
        this.id = account.getId();
        this.nickname = account.getNickname();
        this.profileId = account.getProfile() != null ? account.getProfile().getId() : null;
        this.walletUUID = account.getUuid();

    }
}