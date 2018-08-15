package com.nibble.wheelofjeopardy.game;

public enum Round {
    ROUND1(1, "Round 1"),
    ROUND2(2, "Round 2");

    private int mID;
    private String mName;

    Round(int id, String name) {
        mID = id;
        mName = name;
    }

    public int getID() { return mID; }
    public String getName() { return mName; }
}
