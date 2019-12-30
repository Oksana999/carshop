package com.eoksana.onlinecarsshop1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="replenishments")
public class Replenishment extends Transaction {
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
