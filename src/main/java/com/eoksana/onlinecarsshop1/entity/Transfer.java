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
@Table(name = "transfers")
public class Transfer extends Transaction {

    @ManyToOne
    @JoinColumn(name="account_from")
    private Account accountFrom;
    @ManyToOne
    @JoinColumn(name="account_to")
    private Account accountTo;
}
