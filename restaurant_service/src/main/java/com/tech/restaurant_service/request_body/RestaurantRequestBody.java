package com.tech.restaurant_service.request_body;

public record RestaurantRequestBody(
        String name,
        Double latitude,
        Double longitude
) {
}
