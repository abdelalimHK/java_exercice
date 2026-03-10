package com.renault.ggva.infrastructur.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.*;

@Data
@Entity
@Table(name = "garages")
public class GarageJpaEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "telephone", nullable = false, length = 20)
    private String telephone;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @ElementCollection
    @CollectionTable(
            name = "garage_opening_times",
            joinColumns = @JoinColumn(name = "garage_id")
    )
    private List<OpeningTimeEmbeddable> horairesOuverture = new ArrayList<>();

    @OneToMany(
            mappedBy = "garage",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<VehicleJpaEntity> vehicles = new ArrayList<>();

}