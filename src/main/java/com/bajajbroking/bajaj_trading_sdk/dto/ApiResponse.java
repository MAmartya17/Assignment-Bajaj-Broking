package com.bajajbroking.bajaj_trading_sdk.dto;

public class ApiResponse {
    private int statusCode;
    private String message;
    private Object data;

    public ApiResponse() {}
    public ApiResponse(int statusCode, String message, Object data) { this.statusCode = statusCode; this.message = message; this.data = data; }
    public int getStatusCode() { return statusCode; }
    public String getMessage() { return message; }
    public Object getData() { return data; }
}
