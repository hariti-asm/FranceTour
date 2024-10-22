package com.hariti.asmaa.FranceTour.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String message;
    private String error;
    private int status;

    // Constructor for success response
    public ApiResponse(T data) {
        this.success = true;
        this.data = data;
        this.status = 200;
    }

    public ApiResponse(String error, int status) {
        this.success = false;
        this.error = error;
        this.status = status;
    }


}