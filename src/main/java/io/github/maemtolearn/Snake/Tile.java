package io.github.maemtolearn.Snake;

public class Tile {
    private final Coordinates coordinates;
    private TileType type;

    public Tile(Coordinates coordinates) {
        type = TileType.EMPTY;
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public boolean isKill() {
        return type.isKill();
    }

    public boolean isFood() {
        return type.isFood();
    }

    public boolean isEmpty() {
        return type.isEmpty();
    }

    public void setAsHead(Direction direction) {
        switch (direction) {
            case NORTH -> type = TileType.HEAD_NORTH;
            case EAST -> type = TileType.HEAD_EAST;
            case SOUTH -> type = TileType.HEAD_SOUTH;
            case WEST -> type = TileType.HEAD_WEST;
        }
    }

    @Override
    public String toString() {
        return type.getSymbol();
    }
}
