package com.tech.user_service.controller;

import com.tech.common.dto.RestaurantDto;
import com.tech.user_service.dto.ReviewDto;
import com.tech.user_service.entity.Review;
import com.tech.user_service.entity.User;
import com.tech.user_service.faker.FakeRestaurantDto;
import com.tech.user_service.faker.FakeReview;
import com.tech.user_service.faker.FakeReviewDto;
import com.tech.user_service.faker.FakeUser;
import com.tech.user_service.request_body.ReviewSaveRequestBody;
import com.tech.user_service.request_body.ReviewUpdateRequestBody;
import com.tech.user_service.service.IReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ReviewControllerTest extends ControllerTest {

    @MockBean
    IReviewService reviewService;

    @Autowired
    public ReviewControllerTest(WebApplicationContext context) {

        super( context );
    }

    @Test
    void shouldSave() throws Exception {

        RestaurantDto restaurantDto = FakeRestaurantDto.getSingleData();
        User user = FakeUser.getSingleData();
        Review review = FakeReview.getSingleData();


        ReviewSaveRequestBody reviewSaveRequestBody = new ReviewSaveRequestBody(
                user.getId(),
                restaurantDto.id(),
                review.getRate(),
                review.getComment()
        );

        Mockito.when( reviewService.save( Mockito.any(ReviewSaveRequestBody.class) ) )
                .thenReturn( FakeReviewDto.getSingleData() );

        String stringReviewSaveRequestBody = objectMapper.writeValueAsString( reviewSaveRequestBody );

        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post( "/reviews" )
                                .content( stringReviewSaveRequestBody )
                                .contentType( MediaType.APPLICATION_JSON )
                )
                .andExpect( MockMvcResultMatchers.status().isCreated() )
                .andReturn();

        assertSuccessOnRestResponse( mvcResult );

        ReviewDto reviewDto = getRestResponseData( mvcResult, ReviewDto.class );

        assertEqualityForReviewRequestBodyAndReviewDtoFields( reviewSaveRequestBody, reviewDto );
    }

    private static void assertEqualityForReviewRequestBodyAndReviewDtoFields(ReviewSaveRequestBody reqBody, ReviewDto reviewDto) {
        assertEquals( reqBody.userId(), reviewDto.user().id() );
        assertEquals( reqBody.restaurantId(), reviewDto.restaurant().id() );
        assertEquals( reqBody.rate(), reviewDto.rate() );
        assertEquals( reqBody.comment(), reviewDto.comment() );
    }
}