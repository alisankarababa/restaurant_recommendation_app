package com.tech.user_service.service;

import com.tech.user_service.dto.ReviewDto;
import com.tech.user_service.entity.Review;
import com.tech.user_service.request_body.ReviewSaveRequestBody;
import com.tech.user_service.request_body.ReviewUpdateRequestBody;

public interface IReviewService extends IBaseEntityService<Review> {
    ReviewDto save(ReviewSaveRequestBody requestBody);
    ReviewDto getReviewDtoByReviewId(long id);
    void update(long id, ReviewUpdateRequestBody requestBody);
}