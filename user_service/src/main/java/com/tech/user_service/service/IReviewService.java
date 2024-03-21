package com.tech.user_service.service;

import com.tech.user_service.dto.ReviewDto;
import com.tech.user_service.entity.Review;
import com.tech.user_service.request_body.ReviewSaveRequestBody;

public interface IReviewService extends IBaseEntityService<Review> {
    ReviewDto save(ReviewSaveRequestBody requestBody);
    ReviewDto getReviewDtoByReviewId(long id);
}