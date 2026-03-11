package com.renault.ggva.domain.service;

import com.renault.ggva.domain.enums.FuelType;
import com.renault.ggva.domain.event.DomainEvent;
import com.renault.ggva.domain.event.VehicleAddedEvent;
import com.renault.ggva.domain.exception.garage.GarageCapacityExceededException;
import com.renault.ggva.domain.exception.vehicule.InvalidVehicleException;
import com.renault.ggva.domain.model.Garage;
import com.renault.ggva.domain.model.Vehicle;
import com.renault.ggva.domain.valueobject.OpeningTime;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GarageService {

    private List<DomainEvent> domainEvents = new ArrayList<>();

    void registerEvent(DomainEvent event) {
        this.domainEvents.add(event);
    }

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return events;
    }
    public Garage createGarage(String name, String address, String telephone,
                               String email,
                               Map<DayOfWeek, List<OpeningTime>> horairesOuverture) {

        return Garage.builder()
                .name(name)
                .address(address)
                .telephone(telephone)
                .email(email)
                .build();
    }

    public Vehicle addVehicle(Garage garage, String brand, String model,
                              int anneeFabrication, FuelType fuelType) {
        // business rule 1 — capacity check
        checkCapacity(garage);

        // business rule 2 — field validation
        validateBrand(brand);
        validateModel(model);
        validateAnneeFabrication(anneeFabrication);
        Objects.requireNonNull(fuelType, "Fuel type is required");

        Vehicle vehicle = Vehicle.builder()
                .garageId(garage.getId())
                .brand(brand)
                .model(model)
                .fuelType(fuelType)
                .anneeFabrication(anneeFabrication)
                .build();

        registerEvent(new VehicleAddedEvent(
                vehicle.getId(),
                vehicle.getGarageId(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getFuelType(),
                vehicle.getAnneeFabrication(),
                LocalDateTime.now()
        ));

        garage.getVehicles().add(vehicle);

        return vehicle;
    }

    private static void checkCapacity(Garage garage) {
        if (garage.getVehicles().size() >= garage.getCapacity())
            throw new GarageCapacityExceededException(
                    garage.getId(), garage.getCapacity()
            );
    }

    private void validateBrand(String brand) {
        if (brand == null || brand.isBlank())
            throw new InvalidVehicleException("Brand is required");
        if (brand.length() > 50)
            throw new InvalidVehicleException(
                    "Brand must not exceed 50 characters");
    }

    private void validateModel(String model) {
        if (model == null || model.isBlank())
            throw new InvalidVehicleException("Model is required");
        if (model.length() > 50)
            throw new InvalidVehicleException(
                    "Model must not exceed 50 characters");
    }

    private void validateAnneeFabrication(int annee) {
        int currentYear = Year.now().getValue();
        if (annee < 1900 || annee > currentYear)
            throw new InvalidVehicleException(
                    "Fabrication year must be between 1900 and " + currentYear
            );
    }
}
