package com.client.domain;


import javax.persistence.*;

@Entity
@Table(name = "profiles", schema = "client_service")
public class Profile {
    @Id
    @Column(name = "profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phoneNumber;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Account account;

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
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public ExtendedProfile getExtendedProfile() {
        return extendedProfile;
    }
    public void setExtendedProfile(ExtendedProfile extendedProfile) {
        this.extendedProfile = extendedProfile;
    }
    public Profile(){}

    public Profile(String email, String phoneNumber, Account account, ExtendedProfile extendedProfile) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.account = account;
        this.extendedProfile = extendedProfile;
    }
}