package com.renault.ggva.application.usecase.garage;

import com.renault.ggva.application.port.in.garage.ListGaragesUseCase;
import com.renault.ggva.application.port.out.garage.GarageRepository;
import com.renault.ggva.application.query.GarageSearchCriteria;
import com.renault.ggva.application.query.PageRequest;
import com.renault.ggva.application.query.PagedResult;
import com.renault.ggva.domain.model.Garage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ListGaragesService implements ListGaragesUseCase {

    private final GarageRepository garageRepository;

    @Override
    public PagedResult<Garage> execute(GarageSearchCriteria criteria,
                                       PageRequest pageRequest) {
        Objects.requireNonNull(criteria, "Search criteria cannot be null");
        Objects.requireNonNull(pageRequest, "Page request cannot be null");

        return garageRepository.findAll(criteria, pageRequest);
    }
}
