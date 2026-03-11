package com.renault.ggva.mapper;

import com.renault.ggva.application.command.vehicule.AddVehicleCommand;
import com.renault.ggva.domain.model.Accessory;
import com.renault.ggva.domain.model.Garage;
import com.renault.ggva.domain.model.Vehicle;
import com.renault.ggva.domain.valueobject.OpeningTime;
import com.renault.ggva.dto.request.AddVehicleRequest;
import com.renault.ggva.dto.request.OpeningTimeRequest;
import com.renault.ggva.dto.response.AccessoryResponse;
import com.renault.ggva.dto.response.GarageResponse;
import com.renault.ggva.dto.response.OpeningTimeResponse;
import com.renault.ggva.dto.response.VehicleResponse;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class GarageWebMapper {
    public GarageResponse toResponse(Garage garage) {
        return new GarageResponse(
                garage.getId().toString(),
                garage.getName(),
                garage.getCity(),
                garage.getAddress(),
                garage.getTelephone(),
                garage.getEmail(),
                toOpeningTimeResponseMap(garage.getHorairesOuverture()),
                garage.getVehicles() != null ? garage.getVehicles().size() : 0
        );
    }

    public AddVehicleCommand toAddVehicleCommand(Long garageId,
                                                 AddVehicleRequest request) {
        return new AddVehicleCommand(
                garageId,
                request.brand(),
                request.model(),
                request.anneeFabrication(),
                request.fuelType()
        );
    }

    public VehicleResponse toVehicleResponse(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getGarageId(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getAnneeFabrication(),
                vehicle.getFuelType(),
                vehicle.getAccessories()
                        .stream()
                        .map(this::toAccessoryResponse)
                        .toList()
        );
    }

    public AccessoryResponse toAccessoryResponse(Accessory accessory) {
        return new AccessoryResponse(
                accessory.getId(),
                accessory.getNom(),
                accessory.getDescription(),
                accessory.getPrix(),
                accessory.getType()
        );
    }

    private Map<String, List<OpeningTimeResponse>> toOpeningTimeResponseMap(
            Map<DayOfWeek, List<OpeningTime>> horaires) {
        Map<String, List<OpeningTimeResponse>> result = new LinkedHashMap<>();
        horaires.forEach((day, slots) ->
                result.put(
                        day.name(),
                        slots.stream()
                                .map(this::toOpeningTimeResponse)
                                .toList()
                )
        );
        return result;
    }

    private OpeningTimeResponse toOpeningTimeResponse(OpeningTime openingTime) {
        return new OpeningTimeResponse(
                openingTime.startTime().toString(),   // "08:00"
                openingTime.endTime().toString()       // "18:00"
        );
    }

    private Map<DayOfWeek, List<OpeningTime>> toOpeningTimeDomainMap(
            Map<String, List<OpeningTimeRequest>> horaires) {
        Map<DayOfWeek, List<OpeningTime>> result = new HashMap<>();
        horaires.forEach((day, slots) ->
                result.put(
                        DayOfWeek.valueOf(day.toUpperCase()),
                        slots.stream()
                                .map(this::toOpeningTimeDomain)
                                .toList()
                )
        );
        return result;
    }

    private OpeningTime toOpeningTimeDomain(OpeningTimeRequest request) {
        return new OpeningTime(
                LocalTime.parse(request.startTime()),
                LocalTime.parse(request.endTime())
        );
    }
}
