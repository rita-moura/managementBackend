package com.rentalCar.managementBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalCar.managementBackend.entitys.VehicleEntity;
import com.rentalCar.managementBackend.repositorys.VehicleRepository;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<VehicleEntity> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public void insertOrChangeVehicle(VehicleEntity vehicle) {
        vehicleRepository.save(vehicle);
    }

    public void deleteVehicleById(Integer id) {
        vehicleRepository.deleteById(id);
    }
    
}
