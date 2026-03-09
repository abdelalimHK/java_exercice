package com.renault.ggva.application.port.in.garage;

import com.renault.ggva.domain.model.Garage;

public interface GetGarageUseCase {
    Garage execute(Long id);
}
