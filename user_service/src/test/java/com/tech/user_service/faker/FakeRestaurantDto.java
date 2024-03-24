package com.tech.user_service.faker;

import com.tech.common.dto.RestaurantDto;

public class FakeRestaurantDto {

    private FakeRestaurantDto() {

    }

    public static RestaurantDto getSingleData() {

        return new RestaurantDto(
                "8FU0MwbQ6UCUHfxg1hjO",
                "fake restaurant name",
                35.3567,
                28.7671,
                4.3
        );
    }
}
