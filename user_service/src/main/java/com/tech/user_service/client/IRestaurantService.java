package com.tech.user_service.client;

import com.tech.common.dto.RestaurantDto;
import com.tech.common.enums.eRate;
import com.tech.common.response.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "restaurant-service", url = "http://localhost:8081/restaurant-recommendation-app/api/restaurant-service")
public interface IRestaurantService {

    @PatchMapping("/{id}")
    ResponseEntity<RestResponse<RestaurantDto>> rate(@PathVariable String id, @RequestParam eRate rate);

    @GetMapping("/{id}")
    ResponseEntity<RestResponse<RestaurantDto>> findById(@PathVariable String id);
}