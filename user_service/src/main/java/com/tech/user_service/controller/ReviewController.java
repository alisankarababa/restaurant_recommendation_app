package com.tech.user_service.controller;

import com.tech.common.response.RestResponse;
import com.tech.user_service.dto.ReviewDto;
import com.tech.user_service.request_body.ReviewSaveRequestBody;
import com.tech.user_service.service.IReviewService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final IReviewService reviewService;

    @Autowired
    public ReviewController(IReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<RestResponse<ReviewDto>> save(@RequestBody @Valid ReviewSaveRequestBody requestBody) {

        ReviewDto reviewDto = reviewService.save( requestBody );

        return new ResponseEntity<>( RestResponse.ok( reviewDto ), HttpStatus.CREATED );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<ReviewDto>> findById(@PathVariable @Positive long id) {

        ReviewDto reviewDto = reviewService.getReviewDtoByReviewId( id );
        return new ResponseEntity<>( RestResponse.ok( reviewDto ), HttpStatus.OK );
    }
}