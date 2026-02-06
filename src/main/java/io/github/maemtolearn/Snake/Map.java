package io.github.maemtolearn.Snake;

import java.util.Random;

public class Map {
    private final int width;
    private final int height;
    private final Tile[][] tiles;
    private final Random generator = new Random();
    private Tile food;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[this.height][this.width];

        // Arrays.fill doesn't create new objects for each cell, but just references the same across the array
        for (int row = 0; row < this.height; row++) {
            for (int column = 0; column < this.width; column++) {
                Coordinates coordinates = new Coordinates(row, column);
                Tile tile = new Tile(coordinates);

                if (row == 0 || row == this.height - 1) {
                    tile.setType(Tile.Type.HWall);
                } else if (column == 0 || column == this.width - 1) {
                    tile.setType(Tile.Type.VWall);
                }

                tiles[row][column] = tile;
            }
        }

        // Pseudo assignment
        // Always wall until checkFood() gets called for the 1st time
        food = tiles[0][0];
    }

    public void checkFood() {
        if (!food.isFood()) spawnFood();
    }

    public void drawTo(StringBuilder drawBuffer) {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                drawBuffer.append(tile.toString());
            }
            drawBuffer.append("\n");
        }
    }

    public Tile getTile(Coordinates coordinates) {
        return tiles[coordinates.getRow()][coordinates.getColumn()];
    }

    private void spawnFood() {
        Coordinates spawn = new Coordinates(0, 0);

        // Walls already excluded
        // Loop to avoid food spawning on snake
        while (!getTile(spawn).isEmpty()) {
            spawn.setRow(generator.nextInt(height - 1) + 1);
            spawn.setColumn(generator.nextInt(width - 1) + 1);
        }

        food = getTile(spawn);
        food.setType(Tile.Type.Food);
    }

    public Tile getSafeSpawnPoint(int snakeStartLength) {
        int row = height / 2;
        int column = width / 2;

        if (column < snakeStartLength) column = snakeStartLength;

        Coordinates spawn = new Coordinates(row, column);

        // Random spawn isn't fun once you have to react in real-time
        /*
        while (!getTile(spawn).isEmpty()) {
            spawn.setRow(generator.nextInt(height - 1) + 1);
            // Snake can currently only spawn vertically
            spawn.setColumn(generator.nextInt(width - snakeStartLength) + snakeStartLength);
        }
        */

        return getTile(spawn);
    }
}
