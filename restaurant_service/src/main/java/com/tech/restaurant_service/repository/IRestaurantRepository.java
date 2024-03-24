package com.tech.restaurant_service.repository;

import com.tech.restaurant_service.entity.Restaurant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IRestaurantRepository extends ElasticsearchRepository<Restaurant, String> {
}