package com.tech.user_service.request_body;

import com.tech.common.enums.eRate;

import jakarta.validation.constraints.Size;

public record ReviewUpdateRequestBody (
    eRate rate,
    @Size(max = 400, message = "Comment cannot be longer than 400 characters")
    String comment) {
}