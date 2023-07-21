package com.example.cars.util;

public class ResponseObject {
    private String title;

    private String status;

    private String statusCode;

    private String message;

    private String path;

    private Object body;




    public ResponseObject(String title, String status, String statusCode, String message, String path, Object body) {
        this.title = title;
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.path = path;
        this.body = body;
    }

    public ResponseObject(String title, String status, String statusCode, String message, String path) {
        this.title = title;
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "SgResponseObject [title=" + title + ", status=" + status + ", statusCode=" + statusCode + ", message="
                + message + ", path=" + path + ", body=" + body + "]";
    }
}
