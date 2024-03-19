package com.tech.user_service.faker;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeConcreteBaseEntity {

    public static ConcreteBaseEntity getSingleData() {
        ConcreteBaseEntity concreteBaseEntity = new ConcreteBaseEntity();
        concreteBaseEntity.setId(1L);
        concreteBaseEntity.setCreatedDate(Date.valueOf(LocalDate.now()));
        concreteBaseEntity.setLastModifiedDate(Date.valueOf(LocalDate.now()));
        concreteBaseEntity.setName("test-name");
        return concreteBaseEntity;
    }

    public static List<ConcreteBaseEntity> getListOfData() {

        List<ConcreteBaseEntity> list = new ArrayList<>();
        for(long i = 1; i < 5; ++i) {
            ConcreteBaseEntity concreteBaseEntity = new ConcreteBaseEntity();
            concreteBaseEntity.setId(1L);
            concreteBaseEntity.setCreatedDate(Date.valueOf(LocalDate.now()));
            concreteBaseEntity.setLastModifiedDate(Date.valueOf(LocalDate.now()));
            concreteBaseEntity.setName("test-name-" + i);
            list.add(concreteBaseEntity);
        }

        return list;
    }
}