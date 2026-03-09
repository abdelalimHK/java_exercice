package com.renault.ggva.infrastructur.dao.entity;

import com.renault.ggva.domain.enums.AccessoryType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;

@Data
@Entity
@Table(name = "accessories")
public class AccessoryJpaEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleJpaEntity vehicle;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "prix", nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AccessoryType type;

}
