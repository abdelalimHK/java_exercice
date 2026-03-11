package com.renault.ggva.application.port.in.vehicule;

import com.renault.ggva.application.command.vehicule.AddVehicleCommand;
import com.renault.ggva.domain.model.Vehicle;

public interface AddVehicleUseCase {
    Vehicle execute(AddVehicleCommand command);
}
