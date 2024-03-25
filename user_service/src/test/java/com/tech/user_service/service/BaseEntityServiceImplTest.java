package com.tech.user_service.service;

import com.tech.user_service.faker.ConcreteBaseEntity;
import com.tech.user_service.faker.FakeConcreteBaseEntity;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BaseEntityServiceImplTest {

    @Mock
    private JpaRepository<ConcreteBaseEntity, Long> repository;

    private BaseEntityServiceImpl<ConcreteBaseEntity, JpaRepository<ConcreteBaseEntity, Long>> baseEntityService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        baseEntityService = new BaseEntityServiceImpl<>(repository, ConcreteBaseEntity.class);
    }

    @Test
    void shouldSave() {

        //given

        ConcreteBaseEntity concreteBaseEntity = FakeConcreteBaseEntity.getSingleData();

        //when
        Mockito.when(repository.save(concreteBaseEntity)).thenReturn(concreteBaseEntity);

        ConcreteBaseEntity result = baseEntityService.save(concreteBaseEntity);

        //then
        assertEquls(concreteBaseEntity, result);
    }

    @Test
    void shouldFindAll() {

        //given
        List<ConcreteBaseEntity> listOfData = FakeConcreteBaseEntity.getListOfData();

        //when
        Mockito.when(repository.findAll()).thenReturn(listOfData);

        List<ConcreteBaseEntity> result = baseEntityService.findAll();

        //then
        Assertions.assertEquals(listOfData.size(), result.size());

        for(int i = 0; i < listOfData.size(); ++i) {
            ConcreteBaseEntity input = listOfData.get(i);
            ConcreteBaseEntity output = result.get(i);

            assertEquls(input, output);
        }
    }

    @Test
    void shouldFindById() {

        //given
        ConcreteBaseEntity singleData = FakeConcreteBaseEntity.getSingleData();

        //when

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(singleData));

        ConcreteBaseEntity result = baseEntityService.findById(35);

        //then
        assertEquls(singleData, result);
    }

    @Test
    void shouldNotFindById() {

        //when
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        //then
        Assertions.assertThrows( EntityNotFoundException.class, () -> baseEntityService.findById(35));
    }

    @Test
    void shouldDeleteById() {

        //given
        long id = 35L;

        //when

        baseEntityService.deleteById(id);

        //then
        Mockito.verify(repository).deleteById(id);
    }

    @Test
    void shouldGetRepository() {

        JpaRepository<ConcreteBaseEntity, Long> result = baseEntityService.getRepository();

        Assertions.assertEquals(result, repository);
    }

    private static void assertEquls(ConcreteBaseEntity e1, ConcreteBaseEntity e2) {
        Assertions.assertEquals(e1.getId(), e2.getId());
        Assertions.assertEquals(e1.getName(), e2.getName());
        Assertions.assertEquals(e1.getCreatedDate(), e2.getCreatedDate());
        Assertions.assertEquals(e1.getLastModifiedDate(), e2.getLastModifiedDate());
    }
}