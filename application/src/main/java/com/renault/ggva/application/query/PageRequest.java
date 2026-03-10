package com.renault.ggva.application.query;

import com.renault.ggva.application.exception.InvalidPageRequestException;

import java.util.Set;

public record PageRequest(
        int page,
        int size,
        String sortBy,
        String sortDirection
) {
    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 20;
    private static final int MAX_SIZE = 100;
    private static final String DEFAULT_SORT = "name";
    private static final String DEFAULT_DIRECTION = "ASC";

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "name", "city", "address", "email", "vehicleCount"
    );

    public PageRequest {
        if (page < 0)
            throw new InvalidPageRequestException("Page must not be negative");
        if (size <= 0 || size > MAX_SIZE)
            throw new InvalidPageRequestException(
                    "Size must be between 1 and " + MAX_SIZE);
        if (!ALLOWED_SORT_FIELDS.contains(sortBy))
            throw new InvalidPageRequestException(
                    "Sort field '" + sortBy + "' is not allowed. " +
                            "Allowed fields: " + ALLOWED_SORT_FIELDS);
        if (!sortDirection.equalsIgnoreCase("ASC")
                && !sortDirection.equalsIgnoreCase("DESC"))
            throw new InvalidPageRequestException(
                    "Sort direction must be ASC or DESC");
    }

    public static PageRequest of(int page, int size,
                                 String sortBy, String sortDirection) {
        return new PageRequest(page, size, sortBy, sortDirection);
    }

    public static PageRequest ofDefaults() {
        return new PageRequest(
                DEFAULT_PAGE, DEFAULT_SIZE,
                DEFAULT_SORT, DEFAULT_DIRECTION
        );
    }

    public boolean isAscending() {
        return sortDirection.equalsIgnoreCase("ASC");
    }
}