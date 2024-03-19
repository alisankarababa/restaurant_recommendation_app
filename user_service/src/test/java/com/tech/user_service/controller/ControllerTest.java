package com.tech.user_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tech.common.response.RestResponse;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class ControllerTest {

    protected final MockMvc mockMvc;
    protected static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public ControllerTest(WebApplicationContext context) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    protected <T> T getRestResponseData(MvcResult mvcResult, Class<T> tClass) throws UnsupportedEncodingException, JsonProcessingException {
        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();

        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(RestResponse.class, tClass);

        RestResponse<T> restResponse = objectMapper.readValue(contentAsString, javaType);
        return restResponse.getData();
    }

    protected static void assertSuccessOnRestResponse(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {

        String contentAsString = mvcResult.getResponse().getContentAsString();
        var reviewDtoRestResponse = objectMapper.readValue(contentAsString, RestResponse.class);
        assertTrue(reviewDtoRestResponse.isSuccess());
    }
}