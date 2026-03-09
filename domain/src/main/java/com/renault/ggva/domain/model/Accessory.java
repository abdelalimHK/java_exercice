package com.renault.ggva.domain.model;

import com.renault.ggva.domain.enums.AccessoryType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Accessory {
    private Long id;
    private String nom;
    private String description;
    private BigDecimal prix;
    private AccessoryType type;
}
