package com.tech.user_service.controller;

import com.tech.user_service.dto.UserDto;
import com.tech.user_service.request_body.UserRequestBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest extends ControllerTest{

    @Autowired
    public UserControllerTest(WebApplicationContext context) {
        super(context);
    }

    @Test
    void save() throws Exception{

        UserRequestBody userRequestBody = new UserRequestBody("firstname","lastname",-80.3,60.3);

        String userRequestBodyAsString = objectMapper.writeValueAsString(userRequestBody);

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/users")
                                .content(userRequestBodyAsString)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        assertSuccessOnRestResponse(mvcResult);

        UserDto userDtoOfSavedUser = getRestResponseData(mvcResult, UserDto.class);

        assertEqualityForUserRequestBodyAndUserDtoFields(userRequestBody, userDtoOfSavedUser);
    }

    @Test
    void find() throws Exception{

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

       assertSuccessOnRestResponse(mvcResult);
    }

    @Test
    void findById() throws Exception{

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/users/1000"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertSuccessOnRestResponse(mvcResult);
    }

    @Test
    void update() throws Exception{

        UserRequestBody userRequestBody = new UserRequestBody(
            "updatedUserName",
            "updatedLastName",
            -90.0,
            28.26
        );

        String userRequestBodyAsString = objectMapper.writeValueAsString(userRequestBody);

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.put("/users/1003")
                                .content(userRequestBodyAsString)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertSuccessOnRestResponse(mvcResult);

        UserDto updatedUsersDto = getRestResponseData(mvcResult, UserDto.class);
        assertEqualityForUserRequestBodyAndUserDtoFields(userRequestBody, updatedUsersDto);
    }

    @Test
    void delete() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1002"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
    }


    private static void assertEqualityForUserRequestBodyAndUserDtoFields(UserRequestBody requestBody, UserDto userDto) {

        assertEquals(requestBody.firstName(), userDto.firstName());
        assertEquals(requestBody.lastName(), userDto.lastName());
        assertEquals(requestBody.longitude(), userDto.longitude());
        assertEquals(requestBody.latitude(), userDto.latitude());
    }
}