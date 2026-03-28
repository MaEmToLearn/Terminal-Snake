package io.github.maemtolearn.Snake;

public enum TileType {
    // Added spaces to stretch the otherwise condensed tile width
    SNAKE(" *", false,true, false),
    FOOD(" o", false, false, true),
    H_WALL(" -", false, true, false),
    V_WALL(" |", false, true, false),
    EMPTY("  ", true, false, false),
    DEATH(" X", false, true, false),

    HEAD_NORTH(" ^", false, true, false),
    HEAD_EAST(" >", false, true, false),
    HEAD_SOUTH(" v", false, true, false),
    HEAD_WEST(" <", false, true, false);

    private final String symbol;
    private final boolean isEmpty;
    private final boolean isKill;
    private final boolean isFood;

    TileType(String symbol, boolean isEmpty, boolean isKill, boolean isFood) {
        this.symbol = symbol;
        this.isEmpty = isEmpty;
        this.isKill = isKill;
        this.isFood = isFood;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isKill() {
        return isKill;
    }

    public boolean isFood() {
        return isFood;
    }
}
