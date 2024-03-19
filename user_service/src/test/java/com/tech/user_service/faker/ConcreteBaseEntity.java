package com.tech.user_service.faker;

import com.tech.user_service.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ConcreteBaseEntity extends BaseEntity {
    private String name;
}