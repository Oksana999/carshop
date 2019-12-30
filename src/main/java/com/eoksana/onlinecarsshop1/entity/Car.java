package com.eoksana.onlinecarsshop1.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private long carId;
    private String modell;
    private String mark;
    @Column(name = "build_year")
    private int buildYear;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "price")
    private double price;
    @Column(name = "sold")
    private boolean sold;
    @Column(name = "deleted")
    private boolean deleted = false;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;


    public Car() {
    }

    public Car(String modell, String mark, int buildYear, String imageName, double price) {
        this.modell = modell;
        this.mark = mark;
        this.buildYear = buildYear;
        this.imageName = imageName;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", model='" + modell + '\'' +
                ", mark='" + mark + '\'' +
                ", buildYear=" + buildYear +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
