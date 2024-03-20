package com.tech.user_service.request_body;

import com.tech.common.enums.eRate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record ReviewSaveRequestBody(

        @Min(value = 1, message = "User Id should be 1 at minimum.")
        Long userId,
        String restaurantId,
        eRate rate,
        @Size(max = 400, message = "Comment should not be longer than 400 characters")
        String comment
) {
}