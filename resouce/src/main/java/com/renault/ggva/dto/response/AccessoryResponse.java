package com.renault.ggva.dto.response;

import com.renault.ggva.domain.enums.AccessoryType;

import java.math.BigDecimal;

public record AccessoryResponse(
        Long id,
        String nom,
        String description,
        BigDecimal prix,
        AccessoryType type
) {}
