package com.rentalCar.managementBackend.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "vehicles")
public class VehicleEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "color")
    private String color;
    
    @Column(name = "model")
    private String model;

    @Column(name = "plate")
    private String plate;

    @Column(name = "year")
    private Integer year;

    @Column(name = "is_reserved")
    private boolean reserved;

    public boolean getReserved() {
        return this.reserved;
    }
}