package com.renault.ggva.infrastructur.dao.specification;


import com.renault.ggva.application.query.GarageSearchCriteria;
import com.renault.ggva.domain.enums.AccessoryType;
import com.renault.ggva.domain.enums.FuelType;
import com.renault.ggva.infrastructur.dao.entity.AccessoryJpaEntity;
import com.renault.ggva.infrastructur.dao.entity.GarageJpaEntity;
import com.renault.ggva.infrastructur.dao.entity.VehicleJpaEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class GarageSpecification {

    private GarageSpecification() {}

    public static Specification<GarageJpaEntity> hasName(String name) {
        return (root, query, cb) -> name == null ? null :
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<GarageJpaEntity> hasCity(String city) {
        return (root, query, cb) -> city == null ? null :
                cb.like(cb.lower(root.get("address")), "%" + city.toLowerCase() + "%");
    }

    public static Specification<GarageJpaEntity> hasFuelType(FuelType fuelType) {
        return (root, query, cb) -> {
            if (fuelType == null) return null;
            Join<GarageJpaEntity, VehicleJpaEntity> vehicles =
                    root.join("vehicles", JoinType.LEFT);
            return cb.equal(vehicles.get("fuelType"), fuelType);
        };
    }

    public static Specification<GarageJpaEntity> hasAccessoryType(AccessoryType type) {
        return (root, query, cb) -> {
            if (type == null) return null;
            Join<GarageJpaEntity, VehicleJpaEntity> vehicles =
                    root.join("vehicles", JoinType.LEFT);
            Join<VehicleJpaEntity, AccessoryJpaEntity> accessories =
                    vehicles.join("accessories", JoinType.LEFT);
            return cb.equal(accessories.get("type"), type);
        };
    }

    public static Specification<GarageJpaEntity> build(GarageSearchCriteria criteria) {

        Specification<GarageJpaEntity> specification = (root, query, cb) -> cb.conjunction();

        if (criteria.name() != null) {
            specification = specification.and(hasName(criteria.name()));
        }

        if (criteria.city() != null) {
            specification = specification.and(hasCity(criteria.city()));
        }

        if (criteria.fuelType() != null) {
            specification = specification.and(hasFuelType(criteria.fuelType()));
        }

        if (criteria.accessoryType() != null) {
            specification = specification.and(hasAccessoryType(criteria.accessoryType()));
        }

        return specification;
    }

//    public static Specification<GarageJpaEntity> build(GarageSearchCriteria criteria) {
//        return Specification
//                .where(criteria.name() != null ? hasName(criteria.name()) : null)
//                .and(criteria.city()  != null ? hasCity(criteria.city())  : null)
//                .and(criteria.fuelType() != null ? hasFuelType(criteria.fuelType().getLabel()) : null)
//                .and(criteria.accessoryType() != null ? hasAccessoryType(criteria.accessoryType()) : null);
//    }
}
