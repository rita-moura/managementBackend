package com.rentalCar.managementBackend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rentalCar.dtos.ReservationInsertDto;
import com.rentalCar.dtos.ReservationWithVehicleDto;
import com.rentalCar.managementBackend.entitys.ReservationEntity;
import com.rentalCar.managementBackend.entitys.VehicleEntity;
import com.rentalCar.managementBackend.services.ReservationService;
import com.rentalCar.managementBackend.services.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("reservations")
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private VehicleService vehicleService;

    @Operation(summary = "Lista todas as reservas.")
    @GetMapping
    public ResponseEntity<List<ReservationWithVehicleDto>> getAllRervations() {
        try {
            List<ReservationEntity> listReservations = reservationService.getAllReservations();
            List<VehicleEntity> listVehicles = vehicleService.getAllVehicles();
            List<ReservationWithVehicleDto> reservationsWithVehicles = new ArrayList<>();

            for (VehicleEntity vehicle : listVehicles) {
                if (vehicle.getReserved()) {
                    for (ReservationEntity reservation : listReservations) {
                        if (reservation.getVehicle().getId() == vehicle.getId()) {
                            ReservationWithVehicleDto dto = new ReservationWithVehicleDto();
                            dto.setReservation(reservation);
                            dto.setVehicle(vehicle);
                            reservationsWithVehicles.add(dto);
                        }
                    }
                }
            }
            return new ResponseEntity<>(reservationsWithVehicles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Adiciona uma nova reserva.")
    @PostMapping
    public ResponseEntity<String> insertReservation(@RequestBody ReservationInsertDto insertDto) {
        try {
            Integer vehicleId = insertDto.getVehicleId();

            Optional<VehicleEntity> vehicle = vehicleService.getVehicleById(vehicleId);

            if (vehicle.isPresent()) {
                VehicleEntity vehicleEntity = vehicle.get();
                ReservationEntity reservation = new ReservationEntity();
                if (vehicleEntity.getReserved() == false) {

                    vehicleEntity.setReserved(true);
                    vehicleService.insertOrChangeVehicle(vehicleEntity);

                    reservation.setVehicle(vehicleEntity);
                    reservationService.insertOrChangeReservation(reservation);
                    return new ResponseEntity<>("Reserva adicionada com sucesso!!!", HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>("Esse veículo já está reservado!", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Veículo não encontrado.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
    }

    @Operation(summary = "Exclui a reserva que corresponde ao id informado.")
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteReservation(@PathParam("id") Integer id){
        try {
            Optional<ReservationEntity> reservation = reservationService.getReservationById(id);
            VehicleEntity vehicle = vehicleService.getVehicleById(reservation.get().getVehicle().getId()).get();
            System.out.println(vehicle);

            reservationService.deleteReservationById(id);

            vehicle.setReserved(false);
            vehicleService.insertOrChangeVehicle(vehicle);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
