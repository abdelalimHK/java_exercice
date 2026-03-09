package com.renault.ggva.infrastructur.dao.repository;


import com.renault.ggva.infrastructur.dao.entity.GarageJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarageJpaRepository extends JpaRepository<GarageJpaEntity, Long>,
        JpaSpecificationExecutor<GarageJpaEntity> {

    boolean existsByEmail(String email);

    List<GarageJpaEntity> findByNameContainingIgnoreCase(String name);
}
