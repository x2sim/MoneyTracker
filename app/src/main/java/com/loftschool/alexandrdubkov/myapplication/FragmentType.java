package com.loftschool.alexandrdubkov.myapplication;

public enum FragmentType {
    expense(R.color.income_price_color), income(R.color.dark_sky_blue);
    private int priceColor;

    FragmentType(int priceColor) {
        this.priceColor = priceColor;
    }

    public int getPriceColor() {
        return priceColor;
    }
}
