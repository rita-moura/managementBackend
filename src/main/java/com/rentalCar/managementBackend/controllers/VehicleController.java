package com.rentalCar.managementBackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rentalCar.managementBackend.entitys.VehicleEntity;
import com.rentalCar.managementBackend.services.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.websocket.server.PathParam;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("vehicles")
public class VehicleController {
    
    @Autowired
    private VehicleService vehicleService;

    @Operation(summary = "Lista todos os veículos.")
    @GetMapping
    public ResponseEntity<List<VehicleEntity>> getAllVehicles() {
        try {
            List<VehicleEntity> listVehicles = vehicleService.getAllVehicles();
            return new ResponseEntity<>(listVehicles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Adiciona um novo veiculo, o id nao deve ser informado.")
    @PostMapping
    public ResponseEntity<String> insertVehicle(@RequestBody VehicleEntity vehicle) {
        try {
            if(vehicle.getId() == null) {
                vehicleService.insertOrUpdateVehicle(vehicle);
                return new ResponseEntity<>("Veículo inserido com sucesso!!!", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Veículo não adicionado.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
    }

    @Operation(summary = "Altera o veiculo que corresponde ao id informado.")
    @PutMapping
    public ResponseEntity<String> updateVehicle(@RequestBody VehicleEntity vehicle) {
        try {
            if(vehicle.getId() != null) {
                vehicleService.insertOrUpdateVehicle(vehicle);
                return new ResponseEntity<>("Veículo alterado com sucesso!!!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Veículo não alterado.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
    }

    @Operation(summary = "Exclui o veiculo que corresponde ao id informado.")
    @DeleteMapping
    public ResponseEntity<String> deleteVehicle(@PathParam("id") Integer id){
        try {
            Optional<VehicleEntity> vehicle = vehicleService.getVehicleById(id);
            if(vehicle.get().getReserved() == false) {
                vehicleService.deleteVehicleById(id);
                return new ResponseEntity<>("Veículo excluído com sucesso!!!", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Veículo não pode ser excluido pois está reservado.", HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
