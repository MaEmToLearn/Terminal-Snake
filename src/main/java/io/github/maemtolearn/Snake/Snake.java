package io.github.maemtolearn.Snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private final List<Tile> parts = new ArrayList<>();
    private Direction direction = Direction.EAST;
    private boolean isAlive = true;

    public Snake(List<Tile> parts) {
        this.parts.addAll(parts);
        for (Tile part : parts) {
            part.setType(TileType.SNAKE);
        }
        this.parts.getFirst().setAsHead(direction);
    }

    public int getLength() {
        return parts.size();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Coordinates nextTarget() {
        return parts.getFirst().getCoordinates().increment(direction);
    }

    public void move(Tile next) {
        if (!next.isFood()) removeEnd();
        if (next.isKill()) isAlive = false;
        addStart(next);
        if (!isAlive) getHead().setType(TileType.DEATH);
    }

    private Tile getHead() {
        return parts.getFirst();
    }

    private void addStart(Tile tile) {
        parts.getFirst().setType(TileType.SNAKE);
        parts.addFirst(tile);
        tile.setAsHead(direction);
    }

    private void removeEnd() {
        parts.removeLast().setType(TileType.EMPTY);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
