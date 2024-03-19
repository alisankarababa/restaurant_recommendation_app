package com.tech.common.response;

import java.util.Date;

public class RestResponse<T> {

    private T data;
    private Date time;
    private boolean isSuccess;

    public RestResponse() {

    }

    private RestResponse(T data, boolean isSuccess) {
        this.data = data;
        this.isSuccess = isSuccess;
        this.time = new Date();
    }

    public static <T> RestResponse<T> ok(T data) {

        return new RestResponse<>(data, true);
    }

    public static <T> RestResponse<T> error(T error) {

        return new RestResponse<>(error, false);
    }

    public static RestResponse<Void> noContent() {

        return new RestResponse<>(null, true);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}