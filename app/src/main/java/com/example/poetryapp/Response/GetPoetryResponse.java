package com.example.poetryapp.Response;

import com.example.poetryapp.Models.PoetryModel;

import java.util.List;

public class GetPoetryResponse {
    String status;
    String message;
    List<PoetryModel> data;

    // Constructors :-
    public GetPoetryResponse(String status, String message, List<PoetryModel> data) {
        this.status = status;
        this.message = message;
        this.data = data;
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

    public List<PoetryModel> getData() {
        return data;
    }

    public void setData(List<PoetryModel> data) {
        this.data = data;
    }
}
