package com.renault.ggva.application.port.in.garage;

import com.renault.ggva.application.command.garage.CreateGarageCommand;
import com.renault.ggva.domain.model.Garage;

public interface CreateGarageUseCase {
    Garage execute(CreateGarageCommand command);
}
