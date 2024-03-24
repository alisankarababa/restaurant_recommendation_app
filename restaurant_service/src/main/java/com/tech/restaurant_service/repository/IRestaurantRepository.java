package com.tech.restaurant_service.repository;

import com.tech.restaurant_service.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IRestaurantRepository extends ElasticsearchRepository<Restaurant, String> {

    @Query("{\"script_score\":{\"query\":{\"bool\":{\"must\":{\"match_all\":{}},\"filter\":{\"geo_distance\":{\"distance\":\"10km\",\"geo_point\":{\"lat\":?0,\"lon\":?1}}}}},\"script\":{\"source\":\"double rate = doc['review_count'].value == 0 ? 1 : doc['accumulated_rating'].value / doc['review_count'].value; double distance = doc['geo_point'].arcDistance(?0, ?1); return 0.7 * ((rate - 1) / 4) + 0.3 * (-distance/10000 + 1);\"}}}}")
    Page<Restaurant> recommendRestaurants(double lat, double lon, Pageable pageable);
}