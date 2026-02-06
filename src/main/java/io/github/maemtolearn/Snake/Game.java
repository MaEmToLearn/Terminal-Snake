package io.github.maemtolearn.Snake;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final int snakeStartLength;
    private final int cycleLength;

    private final Map map;
    private final Snake snake;
    private final int maxScore;
    private final StringBuilder drawBuffer;

    public Game(int playableMapWidth, int playableMapHeight, int snakeStartLength, int cycleLength) {
        this.snakeStartLength = snakeStartLength;
        this.cycleLength = cycleLength;
        maxScore = playableMapWidth * playableMapHeight - snakeStartLength;

        // Expand internal map to accommodate additional wall space
        int mapWidth = playableMapWidth + 2;
        int mapHeight = playableMapHeight + 2;

        map = new Map(mapWidth, mapHeight);
        drawBuffer = new StringBuilder(mapWidth * mapHeight * 2);

        snake = spawnSnake();
        map.checkFood();
    }

    public void play() throws IOException {
        Terminal terminal = TerminalBuilder.builder().build();
        terminal.enterRawMode();
        NonBlockingReader reader = terminal.reader();

        while (snake.isAlive() && score() < maxScore) {
            drawFrame();

            // Waits for up to X milliseconds for input
            // Simple implementation, but has one big drawback: Early inputs shorten the length between cycles
            // So like speed-up when you hold forward, but for ALL inputs
            // Doesn't feel bad to play, but it is a distinct difference from the OG game
            // Alternative would be to have a fixed cycle length and only look at the last valid input
            // e.g. by checking reader.available() for valid input until a specific timestamp
            int c = reader.read(cycleLength);

            Direction newDirection;
            switch ((char) c) {
                case 'w' -> newDirection = Direction.NORTH;
                case 'a' -> newDirection = Direction.WEST;
                case 's' -> newDirection = Direction.SOUTH;
                case 'd' -> newDirection = Direction.EAST;
                default -> newDirection = snake.getDirection();
            }

            if (newDirection != snake.getOppositeDirection()) {
                snake.setDirection(newDirection);
            }

            playTurn();
        }

        terminal.close();

        String outcome = "Game Over";
        if (score() == 0) outcome = "Ultimate Defeat!";
        if (score() == maxScore) outcome = "Ultimate Victory!";
        displayEnd(outcome);
    }

    private void playTurn() {
        Tile next = map.getTile(snake.nextTarget());
        snake.move(next);
        if (score() < maxScore) map.checkFood();
    }

    private int score() {
        return snake.getLength() - snakeStartLength;
    }

    private void drawFrame() {
        // Optimized frame draw
        // StringBuilder assembly to only call print once
        // Persistent StringBuilder to avoid unnecessary garbage collection
        drawBuffer.setLength(0);         // reset buffer
        drawBuffer.append("\033[H");     // flush screen
        map.drawTo(drawBuffer);
        drawBuffer.append("\n");
        drawBuffer.append("Score: ").append(score()).append("\n");
        drawBuffer.append("\n");
        drawBuffer.append("W/A/S/D to navigate\n");

        System.out.print(drawBuffer);
    }

    private void displayEnd(String message) {
        drawBuffer.setLength(0);         // reset buffer
        drawBuffer.append("\033[H");     // flush screen

        // One last map draw to display death scene
        map.drawTo(drawBuffer);

        int padding = 5;
        String delimiter = "#".repeat(message.length() + padding * 2);

        drawBuffer.append("\n");
        drawBuffer.append("Final score: ").append(score()).append("\n");
        drawBuffer.append("\n");
        drawBuffer.append(delimiter).append("\n");
        drawBuffer.append(" ".repeat(padding)).append(message).append(" ".repeat(padding)).append("\n");
        drawBuffer.append(delimiter).append("\n");
        drawBuffer.append("\n");

        System.out.print(drawBuffer);
    }

    private Snake spawnSnake() {
        List<Tile> startSnakeParts = new ArrayList<>();
        Tile next = map.getSafeSpawnPoint(snakeStartLength);
        for (int i = 0; i < snakeStartLength; i++) {
            startSnakeParts.add(next);
            next = map.getTile(next.getCoordinates().increment(Direction.WEST));
        }

        return new Snake(startSnakeParts);
    }
}
