package com.renault.ggva.infrastructur.dao.mapper;

import com.renault.ggva.application.query.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GarageSortResolver {

    // maps application sort field names to JPA entity field names
    private static final Map<String, String> SORT_FIELD_MAPPING = Map.of(
            "name",         "name",
            "city",         "ville",
            "address",      "address",
            "email",        "email",
            "vehicleCount", "vehicles.size"
    );

    public Sort resolve(PageRequest pageRequest) {
        String entityField = SORT_FIELD_MAPPING.getOrDefault(
                pageRequest.sortBy(), "name"
        );

        Sort.Direction direction = pageRequest.isAscending()
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        return Sort.by(direction, entityField);
    }

    public org.springframework.data.domain.PageRequest toSpringPageRequest(
            PageRequest pageRequest) {
        return org.springframework.data.domain.PageRequest.of(
                pageRequest.page(),
                pageRequest.size(),
                resolve(pageRequest)
        );
    }
}
