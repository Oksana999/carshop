package com.eoksana.onlinecarsshop1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class CarsStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_storage_id")
    private long carStorageId;
    private String description;
    @Column(name = "is_used")
    private boolean isUsed;
//    @OneToMany
//    @JoinColumn(name = "car_id")
//    private Car car;

    @Column(name = "car_id")
    private Long carId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
