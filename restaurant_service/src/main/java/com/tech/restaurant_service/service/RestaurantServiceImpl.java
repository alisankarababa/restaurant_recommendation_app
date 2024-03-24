package com.tech.restaurant_service.service;

import com.tech.common.enums.eRate;
import com.tech.restaurant_service.entity.Restaurant;
import com.tech.restaurant_service.repository.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        return restaurantRepository.findById( id )
                .orElseThrow(
                        () -> new RuntimeException( "cannot find restaurant" )
                );
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
}