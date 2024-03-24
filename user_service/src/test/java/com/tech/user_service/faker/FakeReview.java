package com.tech.user_service.faker;

import com.tech.common.dto.RestaurantDto;
import com.tech.common.enums.eRate;
import com.tech.user_service.entity.Review;

import java.sql.Date;
import java.time.LocalDate;

public class FakeReview {

    private FakeReview() {
    }

    public static Review getSingleData() {

        RestaurantDto restaurantDto = FakeRestaurantDto.getSingleData();
        Review review = new Review();

        review.setId( 3000L );
        review.setRate( eRate.THREE );
        review.setComment( "fake review comment" );
        review.setRestaurantId( restaurantDto.id());
        review.setUserId( 3000L );
        review.setCreatedDate( Date.valueOf( LocalDate.MIN) );
        review.setLastModifiedDate( Date.valueOf( LocalDate.MIN) );

        return review;
    }
}
