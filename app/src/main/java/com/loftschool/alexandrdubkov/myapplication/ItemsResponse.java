package com.loftschool.alexandrdubkov.myapplication;

import java.util.List;

public class ItemsResponse {
    private String status;
    private List<Item> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Item> getData() {
        return data;
    }

    public void setData(List<Item> data) {
        this.data = data;
    }
}
