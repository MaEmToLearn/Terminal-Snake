package io.github.maemtolearn.Snake;

public class Coordinates {
    private final int row;
    private final int column;

    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Coordinates increment(Direction direction) {
        int row = this.row;
        int column = this.column;

        switch (direction) {
            case NORTH -> row -= 1;
            case SOUTH -> row += 1;
            case EAST -> column += 1;
            case WEST -> column -= 1;
        }

        return new Coordinates(row, column);
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }
}
