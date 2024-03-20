package com.tech.common.dto;

public record RestaurantDto(
        String id,
        String name,
        Double latitude,
        Double longitude,
        Double rate
) {
}
