package com.renault.ggva.infrastructur.dao.mapper;

import com.renault.ggva.domain.model.Accessory;
import com.renault.ggva.infrastructur.dao.entity.AccessoryJpaEntity;
import com.renault.ggva.infrastructur.dao.entity.VehicleJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class AccessoryPersistenceMapper {

    public AccessoryJpaEntity toEntity(Accessory accessory,
                                       VehicleJpaEntity vehicleEntity) {
        AccessoryJpaEntity entity = new AccessoryJpaEntity();
        entity.setId(accessory.getId());
        entity.setVehicle(vehicleEntity);
        entity.setNom(accessory.getNom());
        entity.setDescription(accessory.getDescription());
        entity.setPrix(accessory.getPrix());
        entity.setType(accessory.getType());
        return entity;
    }

    public Accessory toDomain(AccessoryJpaEntity entity) {
        return Accessory.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .description(entity.getDescription())
                .prix(entity.getPrix())
                .type(entity.getType())
                .build();

    }
}
