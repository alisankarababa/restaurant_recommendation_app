package com.tech.user_service.faker;

import com.tech.common.dto.RestaurantDto;
import com.tech.user_service.dto.ReviewDto;
import com.tech.user_service.dto.UserDto;
import com.tech.user_service.entity.Review;
import com.tech.user_service.entity.User;

public class FakeReviewDto {

    private FakeReviewDto() {
    }

    public static ReviewDto getSingleData() {

        UserDto userDto = FakeUserDto.getSingleData();
        RestaurantDto restaurantDto = FakeRestaurantDto.getSingleData();
        Review review = FakeReview.getSingleData();

        return new ReviewDto(
                review.getId(),
                userDto,
                restaurantDto,
                review.getRate(),
                review.getComment()
        );
    }
}
