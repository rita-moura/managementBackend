package com.rentalCar.managementBackend.dtos;

import com.rentalCar.managementBackend.entitys.ReservationEntity;
import com.rentalCar.managementBackend.entitys.VehicleEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ReservationWithVehicleDto {
    private ReservationEntity reservation;
    private VehicleEntity vehicle;
}
