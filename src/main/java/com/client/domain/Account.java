package com.client.domain;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "accounts", schema = "client_service")
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "wallet_id")
    private UUID uuid;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getNickname(){
        return nickname;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public Profile getProfile(){
        return profile;
    }
    public void setProfile(Profile profile){
        this.profile = profile;
    }
    public UUID getUuid(){
        return uuid;
    }
    public void setUuid(UUID uuid){
        this.uuid = uuid;
    }

    public Account(){}

    public Account(String nickname, String password, Profile profile, UUID uuid) {
        this.nickname = nickname;
        this.password = password;
        this.profile = profile;
        this.uuid = uuid;
    }

}