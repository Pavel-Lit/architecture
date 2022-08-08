package ru.geekbrains;

public enum StatusCode {
    OK("HTTP/1.1 200 OK"),
    NOT_FOUND("HTTP/1.1 404 NOT_FOUND")
    ;

    private final String statusCode;

    StatusCode(String s) {
        this.statusCode = s;
    }

    @Override
    public String toString() {
        return statusCode;
    }
}
