package com.renault.ggva.infrastructur.dao.adapter;

import com.renault.ggva.application.port.out.garage.GarageRepository;
import com.renault.ggva.application.query.GarageSearchCriteria;
import com.renault.ggva.application.query.PageRequest;
import com.renault.ggva.application.query.PagedResult;
import com.renault.ggva.domain.enums.AccessoryType;
import com.renault.ggva.domain.model.Garage;
import com.renault.ggva.domain.valueobject.GarageId;
import com.renault.ggva.infrastructur.dao.entity.GarageJpaEntity;
import com.renault.ggva.infrastructur.dao.mapper.GaragePersistenceMapper;
import com.renault.ggva.infrastructur.dao.mapper.GarageSortResolver;
import com.renault.ggva.infrastructur.dao.repository.GarageJpaRepository;
import com.renault.ggva.infrastructur.dao.specification.GarageSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class GarageRepositoryAdapter implements GarageRepository {

    private final GarageJpaRepository garageJpaRepository;
    private final GaragePersistenceMapper mapper;
    private final GarageSortResolver sortResolver;

//    public GarageRepositoryAdapter(GarageJpaRepository garageJpaRepository,
//                                   GaragePersistenceMapper mapper) {
//        this.garageJpaRepository = garageJpaRepository;
//        this.mapper = mapper;
//    }

    @Override
    public Garage save(Garage garage) {
        GarageJpaEntity entity = mapper.toEntity(garage);
        GarageJpaEntity saved = garageJpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Garage> findById(Long id) {
        return garageJpaRepository
                .findById(id)
                .map(mapper::toDomain);
    }

//    @Override
//    public Page<Garage> findAll(GarageSearchCriteria criteria, Pageable pageable) {
//        Specification<GarageJpaEntity> spec = GarageSpecification.build(criteria);
//        return garageJpaRepository
//                .findAll(spec, pageable)
//                .map(mapper::toDomain);
//    }

    @Override
    public void deleteById(Long id) {
        garageJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return garageJpaRepository.existsById(id);
    }


    @Override
    public PagedResult<Garage> findAll(GarageSearchCriteria criteria,
                                       PageRequest pageRequest) {
        Specification<GarageJpaEntity> spec = GarageSpecification.build(criteria);

        org.springframework.data.domain.PageRequest springPageRequest =
                sortResolver.toSpringPageRequest(pageRequest);

        Page<GarageJpaEntity> page = garageJpaRepository.findAll(
                spec, springPageRequest
        );

        List<Garage> garages = page.getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();

        return PagedResult.of(
                garages,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }

//    @Override
//    public List<Garage> findByFuelType(String fuelType) {
//        Specification<GarageJpaEntity> spec =
//                GarageSpecification.hasFuelType(fuelType);
//        return garageJpaRepository.findAll(spec)
//                .stream()
//                .map(mapper::toDomain)
//                .toList();
//    }

//    @Override
//    public List<Garage> findByAccessoryType(AccessoryType accessoryType) {
//        Specification<GarageJpaEntity> spec =
//                GarageSpecification.hasAccessoryType(accessoryType);
//        return garageJpaRepository.findAll(spec)
//                .stream()
//                .map(mapper::toDomain)
//                .toList();
//    }
}