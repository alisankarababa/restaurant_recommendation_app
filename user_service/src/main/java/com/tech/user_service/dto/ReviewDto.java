package com.tech.user_service.dto;

import com.tech.common.dto.RestaurantDto;
import com.tech.common.enums.eRate;

public record ReviewDto(

        Long id,
        UserDto user,
        RestaurantDto restaurant,
        eRate rate,
        String comment
) {
}