package com.renault.ggva.domain.service;

import com.renault.ggva.domain.enums.FuelType;
import com.renault.ggva.domain.event.DomainEvent;
import com.renault.ggva.domain.exception.garage.GarageCapacityExceededException;
import com.renault.ggva.domain.exception.vehicule.InvalidVehicleException;
import com.renault.ggva.domain.model.Garage;
import com.renault.ggva.domain.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class GarageServiceTest {

    private GarageService garageService;
    private Garage garage;

    @BeforeEach
    void setUp() {
        garageService = new GarageService();
        garage = Garage.builder()
                .id(1L)
                .name("Garage Test")
                .city("Paris")
                .address("12 Rue de la Paix")
                .telephone("0123456789")
                .email("test@garage.fr")
                .capacity(5)
                .vehicles(new ArrayList<>())
                .build();
    }

    @Test
    void addVehicle_shouldReturnVehicleWithCorrectFields() {
        Vehicle vehicle = garageService.addVehicle(garage, "Renault", "Clio", 2020, FuelType.ESSENCE);

        assertThat(vehicle.getBrand()).isEqualTo("Renault");
        assertThat(vehicle.getModel()).isEqualTo("Clio");
        assertThat(vehicle.getAnneeFabrication()).isEqualTo(2020);
        assertThat(vehicle.getFuelType()).isEqualTo(FuelType.ESSENCE);
        assertThat(vehicle.getGarageId()).isEqualTo(garage.getId());
    }

    @Test
    void addVehicle_shouldAddVehicleToGarage() {
        garageService.addVehicle(garage, "Renault", "Clio", 2020, FuelType.ESSENCE);

        assertThat(garage.getVehicles()).hasSize(1);
    }

    @Test
    void addVehicle_shouldRegisterVehicleAddedEvent() {
        garageService.addVehicle(garage, "Renault", "Clio", 2020, FuelType.ESSENCE);

        List<DomainEvent> events = garageService.pullDomainEvents();
        assertThat(events).hasSize(1);
        assertThat(events.getFirst().getClass().getSimpleName()).isEqualTo("VehicleAddedEvent");
    }

    @Test
    void pullDomainEvents_shouldClearEventsAfterPull() {
        garageService.addVehicle(garage, "Renault", "Clio", 2020, FuelType.ESSENCE);
        garageService.pullDomainEvents();

        assertThat(garageService.pullDomainEvents()).isEmpty();
    }

    @Test
    void addVehicle_shouldThrowWhenCapacityExceeded() {
        Garage fullGarage = Garage.builder()
                .id(2L)
                .name("Full Garage")
                .capacity(2)
                .vehicles(new ArrayList<>(List.of(
                        Vehicle.builder().brand("A").model("A").anneeFabrication(2020).fuelType(FuelType.DIESEL).build(),
                        Vehicle.builder().brand("B").model("B").anneeFabrication(2020).fuelType(FuelType.DIESEL).build()
                )))
                .build();

        assertThatThrownBy(() -> garageService.addVehicle(fullGarage, "Renault", "Clio", 2020, FuelType.ESSENCE))
                .isInstanceOf(GarageCapacityExceededException.class);
    }

    @Test
    void addVehicle_shouldThrowWhenBrandIsNull() {
        assertThatThrownBy(() -> garageService.addVehicle(garage, null, "Clio", 2020, FuelType.ESSENCE))
                .isInstanceOf(InvalidVehicleException.class)
                .hasMessageContaining("Brand is required");
    }

    @Test
    void addVehicle_shouldThrowWhenBrandIsBlank() {
        assertThatThrownBy(() -> garageService.addVehicle(garage, "  ", "Clio", 2020, FuelType.ESSENCE))
                .isInstanceOf(InvalidVehicleException.class)
                .hasMessageContaining("Brand is required");
    }

    @Test
    void addVehicle_shouldThrowWhenBrandExceeds50Characters() {
        String longBrand = "A".repeat(51);

        assertThatThrownBy(() -> garageService.addVehicle(garage, longBrand, "Clio", 2020, FuelType.ESSENCE))
                .isInstanceOf(InvalidVehicleException.class)
                .hasMessageContaining("50 characters");
    }

    @Test
    void addVehicle_shouldThrowWhenModelIsNull() {
        assertThatThrownBy(() -> garageService.addVehicle(garage, "Renault", null, 2020, FuelType.ESSENCE))
                .isInstanceOf(InvalidVehicleException.class)
                .hasMessageContaining("Model is required");
    }

    @Test
    void addVehicle_shouldThrowWhenModelIsBlank() {
        assertThatThrownBy(() -> garageService.addVehicle(garage, "Renault", "  ", 2020, FuelType.ESSENCE))
                .isInstanceOf(InvalidVehicleException.class)
                .hasMessageContaining("Model is required");
    }

    @Test
    void addVehicle_shouldThrowWhenModelExceeds50Characters() {
        String longModel = "M".repeat(51);

        assertThatThrownBy(() -> garageService.addVehicle(garage, "Renault", longModel, 2020, FuelType.ESSENCE))
                .isInstanceOf(InvalidVehicleException.class)
                .hasMessageContaining("50 characters");
    }

    @Test
    void addVehicle_shouldThrowWhenYearIsBefore1900() {
        assertThatThrownBy(() -> garageService.addVehicle(garage, "Renault", "Clio", 1899, FuelType.ESSENCE))
                .isInstanceOf(InvalidVehicleException.class)
                .hasMessageContaining("1900");
    }

    @Test
    void addVehicle_shouldThrowWhenYearIsInFuture() {
        int futureYear = Year.now().getValue() + 1;

        assertThatThrownBy(() -> garageService.addVehicle(garage, "Renault", "Clio", futureYear, FuelType.ESSENCE))
                .isInstanceOf(InvalidVehicleException.class)
                .hasMessageContaining(String.valueOf(Year.now().getValue()));
    }


    @Test
    void addVehicle_shouldThrowWhenFuelTypeIsNull() {
        assertThatThrownBy(() -> garageService.addVehicle(garage, "Renault", "Clio", 2020, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Fuel type is required");
    }
}