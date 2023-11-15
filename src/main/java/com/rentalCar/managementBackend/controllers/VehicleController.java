package com.rentalCar.managementBackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentalCar.managementBackend.entitys.VehicleEntity;
import com.rentalCar.managementBackend.services.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("vehicles")
public class VehicleController {
    
    @Autowired
    private VehicleService vehicleService;

    @Operation(summary = "Lista todos os carros.")
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
                vehicleService.insertOrChangeVehicle(vehicle);
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
    public ResponseEntity<String> cahngeVehicle(@RequestBody VehicleEntity vehicle) {
        try {
            if(vehicle.getId() != null) {
                vehicleService.insertOrChangeVehicle(vehicle);
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
    public ResponseEntity<HttpStatus> deleteVehicle(@PathParam("id") Integer id){
        try {
            vehicleService.deleteVehicleById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
