package com.renault.ggva.application.port.out.garage;

import com.renault.ggva.domain.model.Garage;

import java.util.Optional;

public interface GarageRepository {
    Garage save(Garage garage);

    Optional<Garage> findById(Long id);

    void deleteById(Long id);

    boolean existsById(Long id);

    //public Page<Garage> findAll(GarageSearchCriteria criteria, Pageable pageable);
}
