package io.github.maemtolearn.Snake;

public class Coordinates {
    private int row;
    private int column;

    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Coordinates increment(Direction direction) {
        Coordinates next = new Coordinates(row, column);

        switch (direction) {
            case NORTH -> next.row -= 1;
            case SOUTH -> next.row += 1;
            case EAST -> next.column += 1;
            case WEST -> next.column -= 1;
        }

        return next;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
