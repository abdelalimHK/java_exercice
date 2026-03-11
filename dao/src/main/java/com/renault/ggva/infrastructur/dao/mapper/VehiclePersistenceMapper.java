package com.renault.ggva.infrastructur.dao.mapper;

import com.renault.ggva.domain.model.Accessory;
import com.renault.ggva.domain.model.Vehicle;
import com.renault.ggva.infrastructur.dao.entity.AccessoryJpaEntity;
import com.renault.ggva.infrastructur.dao.entity.GarageJpaEntity;
import com.renault.ggva.infrastructur.dao.entity.VehicleJpaEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VehiclePersistenceMapper {

    private final AccessoryPersistenceMapper accessoryMapper;

    public VehiclePersistenceMapper(AccessoryPersistenceMapper accessoryMapper) {
        this.accessoryMapper = accessoryMapper;
    }

    public VehicleJpaEntity toEntity(Vehicle vehicle,
                                     GarageJpaEntity garageEntity) {
        VehicleJpaEntity entity = new VehicleJpaEntity();
        entity.setId(vehicle.getId());
        entity.setGarage(garageEntity);
        entity.setBrand(vehicle.getBrand());
        entity.setModel(vehicle.getModel());
        entity.setAnneeFabrication(vehicle.getAnneeFabrication());
        entity.setFuelType(vehicle.getFuelType());

        List<AccessoryJpaEntity> accessories = vehicle.getAccessories()
                .stream()
                .map(a -> accessoryMapper.toEntity(a, entity))
                .collect(Collectors.toCollection(ArrayList::new));

        entity.setAccessories(accessories);

        return entity;
    }

    public Vehicle toDomain(VehicleJpaEntity entity) {
        List<Accessory> accessories = entity.getAccessories()
                .stream()
                .map(accessoryMapper::toDomain)
                .toList();

        return Vehicle.builder()
                .id(entity.getId())
                .garageId(entity.getGarage().getId())
                .brand(entity.getBrand())
                .model(entity.getModel())
                .anneeFabrication(entity.getAnneeFabrication())
                .fuelType(entity.getFuelType())
                .accessories(accessories)
                .build();

    }
}
