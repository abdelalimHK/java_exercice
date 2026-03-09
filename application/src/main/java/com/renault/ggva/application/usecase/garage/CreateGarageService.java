package com.renault.ggva.application.usecase.garage;

import com.renault.ggva.application.command.garage.CreateGarageCommand;
import com.renault.ggva.application.port.in.garage.CreateGarageUseCase;
import com.renault.ggva.application.port.out.garage.GarageRepository;
import com.renault.ggva.domain.model.Garage;
import com.renault.ggva.domain.service.GarageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateGarageService implements CreateGarageUseCase {
    private final GarageRepository garageRepository;
    private final GarageService garageService = new GarageService();

    @Override
    public Garage execute(CreateGarageCommand command) {
        Garage garage = garageService.createGarage(
                command.name(),
                command.address(),
                command.telephone(),
                command.email(),
                command.horairesOuverture()
        );

        return garageRepository.save(garage);
    }
}
