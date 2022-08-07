package com.example.poetryapp.Response;

public class DeleteResponse {
    String status;
    String message;

    // Constructors :-
    public DeleteResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }


    // Getter & Setter :-

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
