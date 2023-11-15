package com.rentalCar.managementBackend.entitys;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleEntity vehicle;

}
