package com.nibble.wheelofjeopardy.questionBoard;

public enum Category {
    CATEGORY_ONE("Category One"),
    CATEGORY_TWO("Category Two"),
    CATEGORY_THREE("Category Three"),
    CATEGORY_FOUR("Category Four"),
    CATEGORY_FIVE("Category Five"),
    CATEGORY_SIX("Category Six");

    private String name;

    Category(String name) { this.name = name; }
    public String getName() { return name; }
}
