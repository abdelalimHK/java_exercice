package com.renault.ggva.infrastructur.dao.repository;

import com.renault.ggva.infrastructur.dao.entity.VehicleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// VehicleJpaRepository.java
public interface VehicleJpaRepository extends JpaRepository<VehicleJpaEntity, Long> {

    List<VehicleJpaEntity> findByGarageId(Long garageId);

    List<VehicleJpaEntity> findByModel(String model);

    List<VehicleJpaEntity> findByFuelType(String fuelType);

    @Query("SELECT v FROM VehicleJpaEntity v WHERE v.garage.id = :garageId " +
            "AND v.fuelType = :fuelType")
    List<VehicleJpaEntity> findByGarageIdAndFuelType(
            @Param("garageId") Long garageId,
            @Param("fuelType") String fuelType
    );
}
