package com.tech.user_service.faker;

import com.tech.common.enums.eRate;
import com.tech.user_service.entity.Review;

import java.sql.Date;
import java.time.LocalDate;

public class FakeReview {

    private FakeReview() {
    }

    public static Review getSingleData() {

        Review review = new Review();

        review.setId( 3000L );
        review.setRate( eRate.THREE );
        review.setComment( "fake review comment" );
        review.setRestaurantId( "86d961d3-cd27-44d1-b0ca-d61eb8748343" );
        review.setUserId( 3000L );
        review.setCreatedDate( Date.valueOf( LocalDate.MIN) );
        review.setLastModifiedDate( Date.valueOf( LocalDate.MIN) );

        return review;
    }
}
