package com.tech.restaurant_service.controller;

import com.tech.common.dto.RestaurantDto;
import com.tech.common.enums.eRate;
import com.tech.common.response.RestResponse;
import com.tech.restaurant_service.entity.Restaurant;
import com.tech.restaurant_service.mapper.IRestaurantMapper;
import com.tech.restaurant_service.request_body.RestaurantRequestBody;
import com.tech.restaurant_service.service.IRestaurantService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@Validated
public class RestaurantController {
    private final IRestaurantService restaurantService;

    @Autowired
    public RestaurantController(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<RestResponse<RestaurantDto>> save(@RequestBody @Valid RestaurantRequestBody requestBody) {

        Restaurant restaurant = IRestaurantMapper.INSTANCE.constructRestaurant( requestBody );

        Restaurant savedRestaurant = restaurantService.save( restaurant );

        RestaurantDto restaurantDto = IRestaurantMapper.INSTANCE.restaurantToRestaurantDto( savedRestaurant );

        return new ResponseEntity<>( RestResponse.ok( restaurantDto ), HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantDto>> findById(
            @PathVariable
            @Size(min = 20, max = 20, message = "Id must be exactly 20 characters long")
            String id) {
        Restaurant restaurant = restaurantService.findById( id );
        RestaurantDto restaurantDto = IRestaurantMapper.INSTANCE.restaurantToRestaurantDto( restaurant );
        return new ResponseEntity<>( RestResponse.ok( restaurantDto ), HttpStatus.OK );
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<RestaurantDto>>> findAll() {
        List<Restaurant> restaurantList = restaurantService.findAll();
        List<RestaurantDto> restaurantDtoList = IRestaurantMapper.INSTANCE.restaurantListToRestaurantDtoList( restaurantList );
        return new ResponseEntity<>( RestResponse.ok( restaurantDtoList ), HttpStatus.OK );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> update(
            @PathVariable
            @Size(min = 20, max = 20, message = "Id must be exactly 20 characters long")
            String id,
            @RequestBody @Valid RestaurantRequestBody requestBody) {

        Restaurant restaurant = restaurantService.findById( id );
        IRestaurantMapper.INSTANCE.updateRestaurant( restaurant, requestBody );
        restaurantService.save( restaurant );
        return new ResponseEntity<>( RestResponse.noContent(), HttpStatus.NO_CONTENT );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> delete(@PathVariable @Size(min = 20, max = 20, message = "Id must be exactly 20 characters long") String id) {
        restaurantService.deleteById( id );
        return new ResponseEntity<>( RestResponse.noContent(), HttpStatus.NO_CONTENT );
    }

    @PatchMapping("/{id}/rate")
    public ResponseEntity<RestResponse<RestaurantDto>> rate(@PathVariable @Size(min = 20, max = 20, message = "Id must be exactly 20 characters long") String id, @RequestParam eRate rate, @RequestParam(required = false) eRate newRate) {

        Restaurant restaurant =
                newRate == null ?
                        restaurantService.rate( id, rate ) :
                        restaurantService.updateRate( id, rate, newRate );

        RestaurantDto restaurantDto = IRestaurantMapper.INSTANCE.restaurantToRestaurantDto( restaurant );
        return new ResponseEntity<>( RestResponse.ok( restaurantDto ), HttpStatus.OK );
    }

    @GetMapping("/recommended")
    public ResponseEntity<RestResponse<List<RestaurantDto>>> recommendRestaurants(@RequestParam double lat, @RequestParam double lon) {

        List<Restaurant> restaurantList = restaurantService.recommendRestaurants( lat, lon );
        List<RestaurantDto> restaurantDtoList = IRestaurantMapper.INSTANCE.restaurantListToRestaurantDtoList( restaurantList );
        return new ResponseEntity<>( RestResponse.ok( restaurantDtoList ), HttpStatus.OK );
    }
}