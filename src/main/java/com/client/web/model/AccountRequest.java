package com.client.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;
import java.util.UUID;

public class AccountRequest {

    @Size(min = 3, max = 15)
    private final String nickname;

    @Size(min = 3, max = 30)
    private final String password;

    @NotNull
    private Long profileId;

    @NotNull
    private UUID walletUUID;

    public String getNickname(){
        return nickname;
    }
    public String getPassword(){
        return password;
    }
    public Long getProfileId(){
        return profileId;
    }
    public UUID getWalletUUID(){
        return walletUUID;
    }


    public AccountRequest(@JsonProperty("nickname") String nickname,
                          @JsonProperty("password") String password,
                          @JsonProperty("profileId") Long profileId,
                          @JsonProperty("walletUUID") UUID walletUUID) {
        this.nickname = nickname;
        this.password = password;
        this.profileId = profileId;
        this.walletUUID = walletUUID;
    }

}
