package io.github.maemtolearn.Snake;

public class Tile {
    // Added spaces to stretch the otherwise condensed tile width
    private static final String SNAKE_SYMBOL = " *";
    private static final String FOOD_SYMBOL = " o";
    private static final String H_WALL_SYMBOL = " -";
    private static final String V_WALL_SYMBOL = " |";
    private static final String EMPTY_SYMBOL = "  ";
    private static final String DEATH_SYMBOL = " X";

    private static final String HEAD_EAST_SYMBOL = " >";
    private static final String HEAD_WEST_SYMBOL = " <";
    private static final String HEAD_NORTH_SYMBOL = " ^";
    private static final String HEAD_SOUTH_SYMBOL = " v";

    public enum Type {
        SNAKE,
        FOOD,
        H_WALL,
        V_WALL,
        EMPTY
    }

    private final Coordinates coordinates;
    private Type type;
    private String symbol;

    public Tile(Coordinates coordinates) {
        type = Type.EMPTY;
        symbol = EMPTY_SYMBOL;
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setType(Type type) {
        this.type = type;

        switch (type) {
            case SNAKE -> symbol = SNAKE_SYMBOL;
            case FOOD -> symbol = FOOD_SYMBOL;
            case H_WALL -> symbol = H_WALL_SYMBOL;
            case V_WALL -> symbol = V_WALL_SYMBOL;
            case EMPTY -> symbol = EMPTY_SYMBOL;
        }
    }

    public void setDeathLocation() {
        symbol = DEATH_SYMBOL;
    }

    public void setAsHead(Direction direction) {
        switch (direction) {
            case NORTH -> symbol = HEAD_NORTH_SYMBOL;
            case EAST -> symbol = HEAD_EAST_SYMBOL;
            case SOUTH -> symbol = HEAD_SOUTH_SYMBOL;
            case WEST -> symbol = HEAD_WEST_SYMBOL;
        }
    }

    public boolean isKill() {
        return type == Type.SNAKE || type == Type.H_WALL || type == Type.V_WALL;
    }

    public boolean isNotFood() {
        return type != Type.FOOD;
    }

    public boolean isEmpty() {
        return type == Type.EMPTY;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
