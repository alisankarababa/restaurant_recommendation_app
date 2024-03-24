package com.tech.restaurant_service.service;

import com.tech.common.enums.eRate;
import com.tech.common.exception.ResourceNotFoundException;
import com.tech.restaurant_service.entity.Restaurant;
import com.tech.restaurant_service.enums.eRestaurantExceptionMessage;
import com.tech.restaurant_service.repository.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements IRestaurantService {
    private final IRestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(IRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save( restaurant );
    }

    @Override
    public Restaurant findById(String id) {

        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById( id );
        if( optionalRestaurant.isEmpty() ) {
            throw new ResourceNotFoundException( eRestaurantExceptionMessage.NOT_FOUND );
        }
        return optionalRestaurant.get();
    }

    @Override
    public List<Restaurant> findAll() {

        List<Restaurant> restaurantList = new ArrayList<>();

        Iterable<Restaurant> restaurantIterable = restaurantRepository.findAll();
        restaurantIterable.forEach( restaurantList::add );

        return restaurantList;
    }

    @Override
    public void deleteById(String id) {
        restaurantRepository.deleteById( id );
    }

    @Override
    public Restaurant rate(String id, eRate rate) {

        Restaurant restaurant = findById( id );

        Integer reviewCount = restaurant.getReviewCount();
        Integer accumulatedRating = restaurant.getAccumulatedRating();

        restaurant.setReviewCount( reviewCount + 1 );
        restaurant.setAccumulatedRating( accumulatedRating + rate.getRate() );

        return save( restaurant );
    }

    @Override
    public Restaurant updateRate(String id, eRate oldRate, eRate newRate) {

        Restaurant restaurant = findById( id );

        Integer reviewCount = restaurant.getReviewCount();
        Integer accumulatedRating = restaurant.getAccumulatedRating();

        restaurant.setReviewCount( reviewCount + 1 );
        restaurant.setAccumulatedRating( accumulatedRating - oldRate.getRate() + newRate.getRate() );

        return save( restaurant );
    }

    @Override
    public List<Restaurant> recommendRestaurants(double lat, double lon) {

        Page<Restaurant> result = restaurantRepository.recommendRestaurants(
                lat,
                lon,
                PageRequest.of(0, 3, Sort.by("_score").descending() )
        );

        return result.stream().toList();
    }
}