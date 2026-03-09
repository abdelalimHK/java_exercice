package com.renault.ggva.domain.valueobject;


import java.util.UUID;

public record GarageId(UUID value) {
    public static GarageId generate() { return new GarageId(UUID.randomUUID()); }
    public static GarageId of(UUID value) { return new GarageId(value); }
}
