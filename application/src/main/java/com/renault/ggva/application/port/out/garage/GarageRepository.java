package com.renault.ggva.application.port.out.garage;

import com.renault.ggva.application.query.GarageSearchCriteria;
import com.renault.ggva.application.query.PageRequest;
import com.renault.ggva.application.query.PagedResult;
import com.renault.ggva.domain.model.Garage;

import java.util.Optional;

public interface GarageRepository {
    Garage save(Garage garage);

    Optional<Garage> findById(Long id);

    void deleteById(Long id);

    boolean existsById(Long id);

    PagedResult<Garage> findAll(GarageSearchCriteria criteria, PageRequest pageRequest);

}
