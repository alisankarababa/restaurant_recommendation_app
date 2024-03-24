package com.tech.restaurant_service.service;

import com.tech.common.enums.eRate;
import com.tech.restaurant_service.entity.Restaurant;

import java.util.List;

public interface IRestaurantService {
    Restaurant save(Restaurant restaurant);
    Restaurant findById(String id);
    List<Restaurant> findAll();
    void deleteById(String id);
    Restaurant rate(String id, eRate rate);
    Restaurant updateRate(String id, eRate oldRate, eRate newRate);
    List<Restaurant> recommendRestaurants(double lat, double lon);
}