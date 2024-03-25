package com.tech.user_service.request_body;

import jakarta.validation.constraints.*;

public record UserRequestBody(

        @NotNull
        @NotBlank
        @Size(min = 3, max = 40)
        String firstName,

        @NotNull
        @NotBlank
        @Size(min = 3, max = 40)
        String lastName,

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
