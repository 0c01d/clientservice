package com.client.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Accessors(chain = true)
@Data
public class AccountRequest {
    private String nickname;
    private Integer profileId;
    private UUID walletUUID;
}
