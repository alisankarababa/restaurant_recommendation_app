package com.tech.user_service.request_body;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record UserRequestBody(

        @Size(min = 3, message = "Firstname cannot be shorter than 3 characters long.")
        @Size(max = 40, message = "Lastname cannot be longer than 40 characters long.")
        String firstName,
        @Size(min = 3, message = "Lastname cannot be shorter than 3 characters long.")
        @Size(max = 40, message = "Lastname cannot be longer than 40 characters long.")
        String lastName,
        @Max(value = 90, message = "Latitude cannot be greater than 90")
        @Min(value = -90, message = "Latitude cannot be less than -90")
        Double latitude,
        @Max(value = 180, message = "Longitude cannot be greater than 180")
        @Min(value = -180, message = "Longitude cannot be less than -180")
        Double longitude
) {
}
