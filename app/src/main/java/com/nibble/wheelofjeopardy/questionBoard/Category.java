package com.nibble.wheelofjeopardy.questionBoard;

public enum Category {
    CATEGORY_ONE(1, "History"),
    CATEGORY_TWO(2, "Astronomy"),
    CATEGORY_THREE(3, "Chemistry"),
    CATEGORY_FOUR(4, "Geography"),
    CATEGORY_FIVE(5, "Literature"),
    CATEGORY_SIX(6, "General");

    private int value;
    private String name;

    Category(int value, String name) {
        this.value = value;
        this.name = name;
    }
    public int getValue() { return value; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
