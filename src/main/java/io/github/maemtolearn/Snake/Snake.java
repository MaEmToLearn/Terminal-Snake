package io.github.maemtolearn.Snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private final List<Tile> body = new ArrayList<>();
    private Direction direction = Direction.EAST;
    private boolean isAlive = true;

    public Snake(List<Tile> tiles) {
        this.body.addAll(tiles);
        tiles.forEach(t -> t.setType(TileType.SNAKE));
        head().setAsHead(direction);
    }

    public int length() {
        return body.size();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Coordinates nextTarget() {
        return head().coordinates().increment(direction);
    }

    public void move(Tile next) {
        if (!next.isFood()) removeEnd();
        if (next.isKill()) isAlive = false;
        addFirst(next);
        if (!isAlive) head().setType(TileType.DEATH);
    }

    private Tile getHead() {
        return body.getFirst();
    }

    private void addFirst(Tile tile) {
        if (!body.isEmpty()) head().setType(TileType.SNAKE);
        tile.setAsHead(direction);
        body.addFirst(tile);
    }

    private void remove() {
        if (!body.isEmpty()) body.removeLast().setType(TileType.EMPTY);
    }

    public Direction direction() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getOppositeDirection() {
        Direction opposite;
        switch (direction) {
            case NORTH -> opposite = Direction.SOUTH;
            case EAST -> opposite = Direction.WEST;
            case SOUTH -> opposite = Direction.NORTH;
            case WEST -> opposite = Direction.EAST;
            default -> throw new IllegalArgumentException("Unsupported direction: " + direction);
        }
        return opposite;
    }
}
