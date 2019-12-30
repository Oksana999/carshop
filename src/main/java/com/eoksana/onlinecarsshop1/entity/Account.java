package com.eoksana.onlinecarsshop1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn (name = "user_id")

//    @OneToOne
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    private User user;
    @Column
    private double amount;


}
