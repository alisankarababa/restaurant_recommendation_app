package com.tech.user_service.mapper;

import com.tech.common.dto.RestaurantDto;
import com.tech.user_service.dto.ReviewDto;
import com.tech.user_service.entity.Review;
import com.tech.user_service.entity.User;
import com.tech.user_service.request_body.ReviewSaveRequestBody;
import com.tech.user_service.request_body.ReviewUpdateRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IReviewMapper {

    IReviewMapper INSTANCE = Mappers.getMapper(IReviewMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Review constructReview(ReviewSaveRequestBody requestBody, User user);

    @Mapping(target = "id", source = "review.id")
    @Mapping(target = "user", expression = "java( IUserMapper.INSTANCE.userToUserDto(user) )")
    @Mapping(target = "restaurant", source = "restaurantDto")
    @Mapping(target = "rate", source =  "review.rate")
    ReviewDto constructReviewDto(Review review, User user, RestaurantDto restaurantDto);

    @Mapping( target = "id", ignore = true)
    void updateReviewFields(@MappingTarget Review review, ReviewUpdateRequestBody requestBody);
}