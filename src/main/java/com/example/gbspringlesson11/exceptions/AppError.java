package com.example.gbspringlesson11.exceptions;

public class AppError {
    private int statusCode;
    private String ErrorMsg;

    public AppError() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public AppError(int statusCode, String errorMsg) {
        this.statusCode = statusCode;
        ErrorMsg = errorMsg;
    }
}
