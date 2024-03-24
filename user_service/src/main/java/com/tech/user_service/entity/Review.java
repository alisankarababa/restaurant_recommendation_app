package com.tech.user_service.entity;


import com.tech.common.enums.eRate;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review extends BaseEntity{

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "restaurant_id", length = 20, nullable = false)
    private String restaurantId;

    @Column(name = "rate", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private eRate rate;

    @Column(name = "comment", length = 400, nullable = false)
    private String comment;
}