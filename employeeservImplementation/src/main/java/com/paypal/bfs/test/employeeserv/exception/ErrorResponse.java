package com.paypal.bfs.test.employeeserv.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ErrorResponse {
    @JsonProperty("error_message")
    private String errorMessage;

    public ErrorResponse(String message) {
        this.errorMessage = message;
    }

}
