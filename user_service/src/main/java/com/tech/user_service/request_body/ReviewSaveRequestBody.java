package com.tech.user_service.request_body;

import com.tech.common.enums.eRate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record ReviewSaveRequestBody(

        @Min( 1 )
        Long userId,
        @Size(min = 20, max = 20, message = "restaurantId must be exactly 20 characters long")
        String restaurantId,
        eRate rate,
        @Size(max = 400)
        String comment
) {
}