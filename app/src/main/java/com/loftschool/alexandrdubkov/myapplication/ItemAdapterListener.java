package com.loftschool.alexandrdubkov.myapplication;

public interface ItemAdapterListener {
    void onItemClick(Item item, int position);
    void onItemLongClick(Item item, int position);
}
