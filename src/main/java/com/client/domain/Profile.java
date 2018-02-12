package com.client.domain;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "profiles", schema = "client_service")
public class Profile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "walletUUID")
    private UUID walletUUID;

    @Column(name = "username")
    private String username;

   /* @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Account account;*/

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ExtendedProfile extendedProfile;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public UUID getWalletUUID() {
        return walletUUID;
    }
    public void setWalletUUID(UUID walletUUID) {
        this.walletUUID = walletUUID;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    /* public Account getAccount() {
                    return account;
                }
                public void setAccount(Account account) {
                    this.account = account;
                }*/
    public ExtendedProfile getExtendedProfile() {
        return extendedProfile;
    }
    public void setExtendedProfile(ExtendedProfile extendedProfile) {
        this.extendedProfile = extendedProfile;
    }
    public Profile(){}

    public Profile(String email, String phoneNumber, UUID walletUUID, String username, /*Account account,*/ ExtendedProfile extendedProfile) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.walletUUID = walletUUID;
       /* this.account = account;*/
        this.extendedProfile = extendedProfile;
        this.username = username;
    }
}