package com.tech.user_service.service;

import com.tech.common.dto.RestaurantDto;
import com.tech.common.enums.eRate;
import com.tech.common.response.RestResponse;
import com.tech.user_service.client.IRestaurantService;
import com.tech.user_service.dto.ReviewDto;
import com.tech.user_service.entity.Review;
import com.tech.user_service.entity.User;
import com.tech.user_service.mapper.IReviewMapper;
import com.tech.user_service.repository.IReviewRepository;
import com.tech.user_service.request_body.ReviewSaveRequestBody;
import com.tech.user_service.request_body.ReviewUpdateRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ReviewServiceImpl extends BaseEntityServiceImpl<Review, IReviewRepository> implements IReviewService {

    private final IUserService userService;
    private final IRestaurantService restaurantService;
    @Autowired
    public ReviewServiceImpl(IReviewRepository repository, IUserService userService, IRestaurantService restaurantService) {
        super(repository, Review.class);
        this.userService = userService;
        this.restaurantService = restaurantService;
    }

    @Override
    public ReviewDto save(ReviewSaveRequestBody requestBody) {

        User user = userService.findById( requestBody.userId() );
        RestaurantDto restaurantDto = conveyRateToRestaurantService( requestBody.restaurantId(), requestBody.rate() );

        Review review = IReviewMapper.INSTANCE.constructReview( requestBody, user );

        Review savedReview = this.getRepository().save( review );

        return IReviewMapper.INSTANCE.constructReviewDto( savedReview, user, restaurantDto );
    }

    @Override
    public ReviewDto getReviewDtoByReviewId(long id) {

        Review review = findById( id );
        User user = userService.findById( review.getUserId() );
        RestaurantDto restaurantDto = getRestaurant( review.getRestaurantId() );

        return IReviewMapper.INSTANCE.constructReviewDto( review, user, restaurantDto );
    }

    @Override
    public void update(long id, ReviewUpdateRequestBody requestBody) {

        Review review = findById( id );
        conveyNewRateToRestaurantService( review.getRestaurantId(), review.getRate(), requestBody.rate() );
        IReviewMapper.INSTANCE.updateReviewFields(review, requestBody);
        save( review );
    }

    private RestaurantDto getRestaurant(String restaurantId) {
        ResponseEntity<RestResponse<RestaurantDto>> restResponseResponseEntity = restaurantService.findById( restaurantId );
        return getRestaurantDto( restResponseResponseEntity );
    }

    private RestaurantDto conveyNewRateToRestaurantService(String restaurantId, eRate oldRate, eRate newRate) {
        ResponseEntity<RestResponse<RestaurantDto>> responseEntity = restaurantService.updateRate( restaurantId, oldRate, newRate);
        return getRestaurantDto( responseEntity );
    }

    private RestaurantDto conveyRateToRestaurantService(String restaurantId, eRate rate) {
        ResponseEntity<RestResponse<RestaurantDto>> responseEntity = restaurantService.rate( restaurantId, rate);
        return getRestaurantDto( responseEntity );
    }

    private static RestaurantDto getRestaurantDto(ResponseEntity<RestResponse<RestaurantDto>> responseEntity) {
        RestResponse<RestaurantDto> body = responseEntity.getBody();

        if(body == null) {
            throw new RuntimeException("restaurant service error");
        }

        if( !body.isSuccess() ) {
            throw new RuntimeException("failed processing review");
        }

        RestaurantDto restaurantDto = body.getData();

        if( restaurantDto == null) {
            throw new RuntimeException("failed processing review");
        }

        return restaurantDto;
    }
}