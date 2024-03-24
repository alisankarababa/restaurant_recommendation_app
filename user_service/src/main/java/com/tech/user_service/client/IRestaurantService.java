package com.tech.user_service.client;

import com.tech.common.dto.RestaurantDto;
import com.tech.common.enums.eRate;
import com.tech.common.response.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "restaurant-service", url = "http://localhost:8081/restaurant-recommendation-app/api/restaurant-service/restaurants")
public interface IRestaurantService {

    @PatchMapping("/{id}/rate")
    ResponseEntity<RestResponse<RestaurantDto>> rate(@PathVariable String id, @RequestParam eRate rate);

    @PatchMapping("/{id}/rate")
    public ResponseEntity<RestResponse<RestaurantDto>> updateRate(@PathVariable String id, @RequestParam eRate rate, @RequestParam eRate newRate);

    @GetMapping("/{id}")
    ResponseEntity<RestResponse<RestaurantDto>> findById(@PathVariable String id);
}