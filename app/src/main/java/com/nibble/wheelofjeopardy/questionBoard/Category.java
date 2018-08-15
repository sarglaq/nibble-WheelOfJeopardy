package com.nibble.wheelofjeopardy.questionBoard;

public enum Category {
    CATEGORY_ONE(1, "Category One"),
    CATEGORY_TWO(2, "Category Two"),
    CATEGORY_THREE(3, "Category Three"),
    CATEGORY_FOUR(4, "Category Four"),
    CATEGORY_FIVE(5, "Category Five"),
    CATEGORY_SIX(6, "Category Six");

    private int value;
    private String name;

    Category(int value, String name) {
        this.value = value;
        this.name = name;
    }
    public int getValue() { return value; }
    public String getName() { return name; }
}
