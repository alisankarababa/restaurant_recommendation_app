package com.tech.user_service.service;

import com.tech.common.dto.RestaurantDto;
import com.tech.common.enums.eRate;
import com.tech.common.response.RestResponse;
import com.tech.user_service.client.IRestaurantService;
import com.tech.user_service.dto.ReviewDto;
import com.tech.user_service.entity.Review;
import com.tech.user_service.entity.User;
import com.tech.user_service.faker.FakeRestaurantDto;
import com.tech.user_service.faker.FakeReview;
import com.tech.user_service.faker.FakeUser;
import com.tech.user_service.repository.IReviewRepository;
import com.tech.user_service.request_body.ReviewSaveRequestBody;
import com.tech.user_service.request_body.ReviewUpdateRequestBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private IUserService userService;
    @Mock
    private IRestaurantService restaurantService;
    @Mock
    private IReviewRepository reviewRepository;
    @InjectMocks
    private ReviewServiceImpl reviewServiceImpl;

    @Test
    void shouldSave() {

        User user = FakeUser.getSingleData();
        RestaurantDto restaurantDto = FakeRestaurantDto.getSingleData();
        Review review = FakeReview.getSingleData();

        ReviewSaveRequestBody reviewSaveRequestBody = new ReviewSaveRequestBody(
                user.getId(),
                restaurantDto.id(),
                review.getRate(),
                review.getComment()
        );

        Mockito.when( userService.findById( Mockito.anyLong() ) )
                .thenReturn( user );

        Mockito.when( restaurantService.rate( Mockito.anyString(), Mockito.any(eRate.class) ) )
                        .thenReturn( new ResponseEntity<>(
                                RestResponse.ok( restaurantDto ),
                                HttpStatus.OK
                        ) );

        Mockito.when( reviewRepository.save( Mockito.any(Review.class) ) )
                        .thenReturn( review );

        ReviewDto reviewDto = reviewServiceImpl.save( reviewSaveRequestBody );

        assertEqualityOnUserAndUserDtoInReviewDto(user, reviewDto);
        assertEqualityOnRestaurantDtoAndRestaurantDtoInReviewDto(restaurantDto, reviewDto);
        assertEquals( reviewSaveRequestBody.rate(), reviewDto.rate() );
        assertEquals( reviewSaveRequestBody.comment(), reviewDto.comment() );
    }

    @Test
    void shouldGetReviewDtoByReviewId() {

        Review review = FakeReview.getSingleData();
        User user = FakeUser.getSingleData();
        RestaurantDto restaurantDto = FakeRestaurantDto.getSingleData();


        Mockito.when( reviewRepository.findById( Mockito.anyLong() ) )
                .thenReturn( Optional.of( review ) );

        Mockito.when( userService.findById( Mockito.anyLong() ) )
                .thenReturn( user );

        Mockito.when( restaurantService.findById( Mockito.anyString() ) )
                .thenReturn( new ResponseEntity<>(
                        RestResponse.ok( restaurantDto ),
                        HttpStatus.OK
                ) );

        ReviewDto reviewDto = reviewServiceImpl.getReviewDtoByReviewId( review.getId() );

        assertEqualityOnRestaurantDtoAndRestaurantDtoInReviewDto( restaurantDto, reviewDto );
        assertEqualityOnUserAndUserDtoInReviewDto( user, reviewDto );
        assertEquals( review.getRate(), reviewDto.rate() );
        assertEquals( review.getComment(), reviewDto.comment() );
    }

    @Test
    void shouldUpdate() {

        Review review = FakeReview.getSingleData();
        RestaurantDto restaurantDto = FakeRestaurantDto.getSingleData();

        ReviewUpdateRequestBody reviewUpdateRequestBody = new ReviewUpdateRequestBody( eRate.FIVE, "Definitely recommend this place!!" );

        Mockito.when( reviewRepository.findById( Mockito.anyLong() ) )
                        .thenReturn( Optional.of( review ) );

        Mockito.when( restaurantService.updateRate( Mockito.anyString(), Mockito.any(eRate.class), Mockito.any(eRate.class) ) )
                .thenReturn( new ResponseEntity<>(
                        RestResponse.ok( restaurantDto ),
                        HttpStatus.OK
                ) );

        reviewServiceImpl.update( review.getId(), reviewUpdateRequestBody );

        assertEquals( reviewUpdateRequestBody.comment(), review.getComment() );
        assertEquals( reviewUpdateRequestBody.rate(), review.getRate() );

        Mockito.verify( reviewRepository).save( review );
    }

    private void assertEqualityOnRestaurantDtoAndRestaurantDtoInReviewDto(RestaurantDto restaurantDto, ReviewDto reviewDto) {

        assertEquals( restaurantDto.id(), reviewDto.restaurant().id() );
        assertEquals( restaurantDto.name(), reviewDto.restaurant().name() );
        assertEquals( restaurantDto.latitude(), reviewDto.restaurant().latitude() );
        assertEquals( restaurantDto.longitude(), reviewDto.restaurant().longitude() );
        // rate is not included because
        // when you rate a restaurant the logic for computing new rate for restaurant belongs in the restaurant service
    }

    private void assertEqualityOnUserAndUserDtoInReviewDto(User user, ReviewDto reviewDto) {
        assertEquals( user.getId(), reviewDto.user().id() );
        assertEquals( user.getFirstName(), reviewDto.user().firstName() );
        assertEquals( user.getLastName(), reviewDto.user().lastName() );
        assertEquals( user.getLatitude(), reviewDto.user().latitude() );
        assertEquals( user.getLongitude(), reviewDto.user().longitude() );
    }


}