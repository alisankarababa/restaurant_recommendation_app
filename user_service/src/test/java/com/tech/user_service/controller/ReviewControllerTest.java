package com.tech.user_service.controller;

import com.tech.common.dto.RestaurantDto;
import com.tech.common.enums.eRate;
import com.tech.common.response.RestResponse;
import com.tech.user_service.client.IRestaurantService;
import com.tech.user_service.dto.ReviewDto;
import com.tech.user_service.faker.FakeRestaurantDto;
import com.tech.user_service.request_body.ReviewSaveRequestBody;
import com.tech.user_service.request_body.ReviewUpdateRequestBody;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        Mockito.when( restaurantService.rate( Mockito.anyString(), Mockito.any( eRate.class ) ) )
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

    @Test
    void shouldFindById() throws Exception {

        Mockito.when( restaurantService.findById( Mockito.anyString() ) )
                .thenReturn( new ResponseEntity<>(
                        RestResponse.ok( FakeRestaurantDto.getSingleData() ),
                        HttpStatus.OK
                ) );

        MvcResult mvcResult = mockMvc.perform( MockMvcRequestBuilders.get( "/reviews/2001" ) )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andReturn();

        assertSuccessOnRestResponse( mvcResult );

        ReviewDto restResponseData = getRestResponseData( mvcResult, ReviewDto.class );
        assertNotNull( restResponseData );
    }

    @Test
    void shouldUpdate() throws Exception {

        ReviewUpdateRequestBody reviewUpdateRequestBody = new ReviewUpdateRequestBody( eRate.FOUR, "It is a cozy place. I like the food." );
        String stringReviewUpdateRequestBody = objectMapper.writeValueAsString( reviewUpdateRequestBody );

        RestaurantDto restaurantDto = FakeRestaurantDto.getSingleData();

        Mockito.when( restaurantService.updateRate( Mockito.anyString(), Mockito.any( eRate.class ), Mockito.any( eRate.class ) ) )
                .thenReturn(
                        new ResponseEntity<>( RestResponse.ok( restaurantDto ), HttpStatus.OK )
                );

        MvcResult mvcResult = mockMvc.perform( MockMvcRequestBuilders
                        .put( "/reviews/2002" )
                        .content( stringReviewUpdateRequestBody )
                        .contentType( MediaType.APPLICATION_JSON )
                )
                .andExpect( MockMvcResultMatchers.status().isNoContent() )
                .andReturn();

        assertSuccessOnRestResponse( mvcResult );
    }

    @Test
    void shouldDelete() throws Exception {

        MvcResult mvcResult = mockMvc.perform( MockMvcRequestBuilders.delete( "/reviews/2003" ) )
                .andExpect( MockMvcResultMatchers.status().isNoContent() )
                .andReturn();

        assertSuccessOnRestResponse( mvcResult );
    }

    private static void assertEqualityForReviewRequestBodyAndReviewDtoFields(ReviewSaveRequestBody reqBody, ReviewDto reviewDto) {
        assertEquals( reqBody.userId(), reviewDto.user().id() );
        assertEquals( reqBody.restaurantId(), reviewDto.restaurant().id() );
        assertEquals( reqBody.rate(), reviewDto.rate() );
        assertEquals( reqBody.comment(), reviewDto.comment() );
    }
}