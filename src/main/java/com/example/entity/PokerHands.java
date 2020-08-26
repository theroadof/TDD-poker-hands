package com.example.entity;

public class PokerHands {
    private int number;
    private String pokerColor;

    public PokerHands(int number, String pokerColor) {
        this.number = number;
        this.pokerColor = pokerColor;
    }

    public int getNumber() {
        return number;
    }


    public String getPokerColor() {
        return pokerColor;
    }

}
