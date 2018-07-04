package com.padcmyanmar.ckassingnment.events;

public class ApiErrorEvent {

    private String errorMessage;

    public String getErrorMsg() {
        return errorMessage;
    }

    public ApiErrorEvent(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
}
