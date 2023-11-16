package com.rentalCar.managementBackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalCar.managementBackend.entitys.ReservationEntity;
import com.rentalCar.managementBackend.entitys.VehicleEntity;
import com.rentalCar.managementBackend.repositorys.ReservationRepository;


@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private VehicleService vehicleService;

    public List<ReservationEntity> getAllReservations() {
        return reservationRepository.findAll();
    }

     public void insertReservation(Integer vehicleId) {
        Optional<VehicleEntity> vehicle = vehicleService.getVehicleById(vehicleId);
        if(vehicle.isPresent()) {
            ReservationEntity reservation = new ReservationEntity();
            reservation.setVehicle(vehicle.get());
            reservationRepository.save(reservation);
        }
    }

    public void insertOrUpdateReservation(ReservationEntity reservation) {
        reservationRepository.save(reservation);
    }

    public void deleteReservationById(Integer id) {
        reservationRepository.deleteById(id);
    }

    public Optional<ReservationEntity> getReservationById(Integer id) {
        return reservationRepository.findById(id);
    }
}
