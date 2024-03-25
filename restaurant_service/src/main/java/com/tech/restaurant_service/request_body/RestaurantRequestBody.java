package com.tech.restaurant_service.request_body;

import jakarta.validation.constraints.*;

public record RestaurantRequestBody(
        @NotNull
        @NotBlank
        @Size(min = 3, max = 40)
        String name,

        @NotNull
        @Max(90)
        @Min(-90)
        Double latitude,

        @NotNull
        @Max(180)
        @Min(-180)
        Double longitude
) {
}
