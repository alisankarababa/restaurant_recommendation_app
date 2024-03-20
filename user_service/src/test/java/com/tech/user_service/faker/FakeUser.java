package com.tech.user_service.faker;

import com.tech.user_service.entity.User;

import java.sql.Date;
import java.time.LocalDate;

public class FakeUser {

    private FakeUser() {

    }

    public static User getSingleData() {

        User user = new User();

        user.setId( 3000L );
        user.setFirstName( "fakeFirstName" );
        user.setLastName( "fakeLastName" );
        user.setLatitude( 12.22 );
        user.setLongitude( 22.25 );
        user.setCreatedDate( Date.valueOf( LocalDate.MIN) );
        user.setLastModifiedDate( Date.valueOf( LocalDate.MIN) );

        return user;
    }

}
