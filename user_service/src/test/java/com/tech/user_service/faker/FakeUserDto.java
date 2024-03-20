package com.tech.user_service.faker;

import com.tech.user_service.dto.UserDto;
import com.tech.user_service.entity.User;

public class FakeUserDto {

    private FakeUserDto() {
    }

    public static UserDto getSingleData() {

        User user = FakeUser.getSingleData();

        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getLatitude(),
                user.getLongitude()
        );
    }
}
