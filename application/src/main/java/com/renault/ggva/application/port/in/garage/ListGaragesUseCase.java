package com.renault.ggva.application.port.in.garage;

import com.renault.ggva.application.query.GarageSearchCriteria;
import com.renault.ggva.application.query.PageRequest;
import com.renault.ggva.application.query.PagedResult;
import com.renault.ggva.domain.model.Garage;

public interface ListGaragesUseCase {
    PagedResult<Garage> execute(GarageSearchCriteria criteria, PageRequest pageRequest);
}
