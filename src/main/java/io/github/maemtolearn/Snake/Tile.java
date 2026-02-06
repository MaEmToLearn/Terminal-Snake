package io.github.maemtolearn.Snake;

public class Tile {
    // Added spaces to stretch the otherwise condensed tile width
    private static final String snakeSymbol = " *";
    private static final String foodSymbol = " o";
    private static final String hWallSymbol = " -";
    private static final String vWallSymbol = " |";
    private static final String emptySymbol = "  ";
    private static final String deathSymbol = " X";

    private static final String headEastSymbol = " >";
    private static final String headWestSymbol = " <";
    private static final String headNorthSymbol = " ^";
    private static final String headSouthSymbol = " v";

    public enum Type {
        Snake,
        Head,
        Food,
        HWall,
        VWall,
        Empty,
        Death
    }

    private final Coordinates coordinates;
    private Type type;
    private String symbol;

    public Tile(Coordinates coordinates) {
        type = Type.Empty;
        symbol = emptySymbol;
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setType(Type type) {
        this.type = type;

        switch (type) {
            case Snake -> symbol = snakeSymbol;
            case Head -> symbol = headEastSymbol;
            case Food -> symbol = foodSymbol;
            case HWall -> symbol = hWallSymbol;
            case VWall -> symbol = vWallSymbol;
            case Empty -> symbol = emptySymbol;
            case Death -> symbol = deathSymbol;
            default -> throw new IllegalArgumentException("Unsupported tile type" + type);
        }
    }

    public void setAsHead(Direction direction) {
        setType(Type.Head);

        switch (direction) {
            case NORTH -> symbol = headNorthSymbol;
            case EAST -> symbol = headEastSymbol;
            case SOUTH -> symbol = headSouthSymbol;
            case WEST -> symbol = headWestSymbol;
        }
    }

    public boolean isKill() {
        return isSnake() || isWall();
    }

    public boolean isSnake() {
        return type == Type.Snake || type == Type.Head;
    }

    public boolean isWall() {
        return type == Type.HWall || type == Type.VWall;
    }

    public boolean isFood() {
        return type == Type.Food;
    }

    public boolean isEmpty() {
        return type == Type.Empty;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
