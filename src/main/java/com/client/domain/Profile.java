package com.client.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "profiles", schema = "client_service")
public class Profile {
    @Id
    @Column(name = "profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "firstname")
    public String firstname;

    @Column(name = "middlename")
    public String middlename;

    @Column(name = "lastname")
    public String lastname;

    @Column(name = "email")
    public String email;

    @Column(name = "phone")
    public String phone;

    @Column(name = "gender")
    public String gender;

    @Column(name = "date_of_birth")
    public String dateOfBirth;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Account account;

}