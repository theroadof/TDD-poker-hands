package com.example.entity;

public class PokerType {
    private String type;
    private String winner;
    private String message;
    private int level;
    private int maxNumber;

    public PokerType(String type, String winner, String message) {
        this.type = type;
        this.winner = winner;
        this.message = message;
    }

    public PokerType(String type) {
        this.type = type;
    }

    public PokerType() {
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
