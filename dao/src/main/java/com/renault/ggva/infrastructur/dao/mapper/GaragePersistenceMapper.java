package com.renault.ggva.infrastructur.dao.mapper;

import com.renault.ggva.domain.model.Garage;
import com.renault.ggva.domain.model.Vehicle;
import com.renault.ggva.domain.valueobject.OpeningTime;
import com.renault.ggva.infrastructur.dao.entity.GarageJpaEntity;
import com.renault.ggva.infrastructur.dao.entity.OpeningTimeEmbeddable;
import com.renault.ggva.infrastructur.dao.entity.VehicleJpaEntity;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GaragePersistenceMapper {

    private final VehiclePersistenceMapper vehicleMapper;

    public GaragePersistenceMapper(VehiclePersistenceMapper vehicleMapper) {
        this.vehicleMapper = vehicleMapper;
    }

    public GarageJpaEntity toEntity(Garage garage) {
        GarageJpaEntity entity = new GarageJpaEntity();
        entity.setId(garage.getId());
        entity.setName(garage.getName());
        entity.setAddress(garage.getAddress());
        entity.setTelephone(garage.getTelephone());
        entity.setEmail(garage.getEmail());
        entity.setHorairesOuverture(toEmbeddableList(garage.getHorairesOuverture()));

        List<VehicleJpaEntity> vehicles = garage.getVehicles()
                .stream()
                .map(v -> vehicleMapper.toEntity(v, entity))
                .toList();
        entity.setVehicles(vehicles);

        return entity;
    }

    public Garage toDomain(GarageJpaEntity entity) {
//        List<Vehicle> vehicles = entity.getVehicles()
//                .stream()
//                .map(vehicleMapper::toDomain)
//                .toList();

        return Garage.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .telephone(entity.getTelephone())
                .email(entity.getEmail())
                .horairesOuverture(toDomainMap(entity.getHorairesOuverture()))
                //.vehicles(vehicles)
                .build();
    }

    // Map<DayOfWeek, List<OpeningTime>> → flat List<OpeningTimeEmbeddable>
    private List<OpeningTimeEmbeddable> toEmbeddableList(Map<DayOfWeek, List<OpeningTime>> horaires) {
        return horaires.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .map(slot -> {
                            OpeningTimeEmbeddable e = new OpeningTimeEmbeddable();
                            e.setDayOfWeek(entry.getKey());
                            e.setOpeningTime(slot.startTime());
                            e.setClosingTime(slot.endTime());
                            return e;
                        }))
                .toList();
    }

    // flat List<OpeningTimeEmbeddable> → Map<DayOfWeek, List<OpeningTime>>
    private Map<DayOfWeek, List<OpeningTime>> toDomainMap(List<OpeningTimeEmbeddable> horaires) {
        return horaires.stream()
                .collect(Collectors.groupingBy(
                        OpeningTimeEmbeddable::getDayOfWeek,
                        Collectors.mapping(
                                s -> new OpeningTime(s.getOpeningTime(), s.getClosingTime()),
                                Collectors.toList()
                        )
                ));
    }
}