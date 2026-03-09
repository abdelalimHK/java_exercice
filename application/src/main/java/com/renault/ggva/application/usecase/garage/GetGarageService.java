package com.renault.ggva.application.usecase.garage;

import com.renault.ggva.application.port.in.garage.GetGarageUseCase;
import com.renault.ggva.application.port.out.garage.GarageRepository;
import com.renault.ggva.domain.exception.garage.GarageNotFoundException;
import com.renault.ggva.domain.model.Garage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetGarageService implements GetGarageUseCase {

    private final GarageRepository garageRepository;

    @Override
    public Garage execute(Long id) {
        return garageRepository
                .findById(id)
                .orElseThrow(() -> new GarageNotFoundException(id));
    }
}
