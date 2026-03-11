package com.renault.ggva.application.usecase.vehicule;

import com.renault.ggva.application.command.vehicule.AddVehicleCommand;
import com.renault.ggva.application.port.in.vehicule.AddVehicleUseCase;
import com.renault.ggva.application.port.out.garage.EventPublisher;
import com.renault.ggva.application.port.out.garage.GarageRepository;
import com.renault.ggva.domain.exception.garage.GarageNotFoundException;
import com.renault.ggva.domain.model.Garage;
import com.renault.ggva.domain.model.Vehicle;
import com.renault.ggva.domain.service.GarageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddVehicleService implements AddVehicleUseCase {

    private final GarageRepository garageRepository;
    private final GarageService garageService ;

    private final EventPublisher eventPublisher;

    public Vehicle execute(AddVehicleCommand command) {
        Garage garage = garageRepository
                .findById(command.garageId())
                .orElseThrow(() -> new GarageNotFoundException(command.garageId()));

        Vehicle vehicle = garageService.addVehicle(
                garage,
                command.brand(),
                command.model(),
                command.anneeFabrication(),
                command.fuelType()
        );

        garageRepository.save(garage);

        garageService.pullDomainEvents().forEach(eventPublisher::publish);

        return vehicle;
    }
}
