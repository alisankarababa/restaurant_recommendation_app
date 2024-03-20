package com.tech.user_service.controller;

import com.tech.common.dto.RestaurantDto;
import com.tech.common.enums.eRate;
import com.tech.common.response.RestResponse;
import com.tech.user_service.client.IRestaurantService;
import com.tech.user_service.dto.ReviewDto;
import com.tech.user_service.faker.FakeRestaurantDto;
import com.tech.user_service.request_body.ReviewSaveRequestBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ReviewControllerTest extends ControllerTest {

    @MockBean
    IRestaurantService restaurantService;

    @Autowired
    public ReviewControllerTest(WebApplicationContext context) {

        super( context );
    }

    @Test
    void shouldSave() throws Exception {

        RestaurantDto restaurantDto = FakeRestaurantDto.getSingleData();

        ReviewSaveRequestBody reviewSaveRequestBody = new ReviewSaveRequestBody(
                1000L,
                restaurantDto.id(),
                eRate.TWO,
                "shouldSave Test Comment"
        );

        Mockito.when( restaurantService.rate( Mockito.anyString(), Mockito.any( eRate.class) ) )
                .thenReturn(
                        new ResponseEntity<>( RestResponse.ok( restaurantDto ), HttpStatus.OK )
                );

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