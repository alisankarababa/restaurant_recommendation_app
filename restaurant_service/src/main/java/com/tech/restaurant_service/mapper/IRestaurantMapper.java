package com.tech.restaurant_service.mapper;

import com.tech.common.dto.RestaurantDto;
import com.tech.restaurant_service.entity.Restaurant;
import com.tech.restaurant_service.request_body.RestaurantRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantMapper {

    IRestaurantMapper INSTANCE = Mappers.getMapper( IRestaurantMapper.class );

    @Mapping( target = "reviewCount", constant = "0")
    @Mapping( target = "accumulatedRating", constant = "0")
    @Mapping( target = "geoPoint", expression = "java( new GeoPoint( requestBody.latitude(), requestBody.longitude() ) )")
    Restaurant constructRestaurant(RestaurantRequestBody requestBody);

    @Mapping( target = "latitude", source = "restaurant.geoPoint.lat")
    @Mapping( target = "longitude", source = "restaurant.geoPoint.lon")
    @Mapping( target = "rate", expression = "java( (double)restaurant.getAccumulatedRating() / restaurant.getReviewCount() )")
    RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);

    List<RestaurantDto> restaurantListToRestaurantDtoList(List<Restaurant> restaurantList);

    @Mapping( target = "restaurant.geoPoint", expression = "java( new GeoPoint( requestBody.latitude(), requestBody.longitude() ) )")
    void updateRestaurant(@MappingTarget Restaurant restaurant, RestaurantRequestBody requestBody);
}
