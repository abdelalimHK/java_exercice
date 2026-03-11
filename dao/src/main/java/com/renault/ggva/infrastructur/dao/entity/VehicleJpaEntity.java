package com.renault.ggva.infrastructur.dao.entity;

import com.renault.ggva.domain.enums.FuelType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "vehicles")
public class VehicleJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garage_id", nullable = false)
    private GarageJpaEntity garage;

    @Column(name = "brand", nullable = false, length = 50)
    private String brand;

    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @Column(name = "annee_fabrication", nullable = false)
    private int anneeFabrication;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", nullable = false)
    private FuelType fuelType;

    @OneToMany(
            mappedBy = "vehicle",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<AccessoryJpaEntity> accessories = new ArrayList<>();

}