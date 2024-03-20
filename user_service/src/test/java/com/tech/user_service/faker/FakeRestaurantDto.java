package com.tech.user_service.faker;

import com.tech.common.dto.RestaurantDto;

public class FakeRestaurantDto {

    private FakeRestaurantDto() {

    }

    public static RestaurantDto getSingleData() {

        return new RestaurantDto(
                "9fc193e5-70d3-47ba-b551-f5cd6baf16e4",
                "fake restaurant name",
                35.3567,
                28.7671,
                4.3
        );
    }
}
