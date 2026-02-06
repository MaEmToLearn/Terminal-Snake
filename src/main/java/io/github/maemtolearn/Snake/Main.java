package io.github.maemtolearn.Snake;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // ##########################
        // Default settings
        // ##########################
        int mapWidth = 10;
        int mapHeight = 10;
        int snakeStartLength = 3;
        int cycleLength = 500;  // in milliseconds
        // ##########################

        boolean isPlaying = true;
        while (isPlaying) {
            Game game;
            boolean defaultSize = askYesNoQuestion("Do you want to use the default " + mapWidth + "x" + mapHeight + " map? (y/n)");
            if (defaultSize) {
                game = new Game(mapWidth, mapHeight, snakeStartLength, cycleLength);
            } else {
                // Just some very basic sanity checks
                int minMapWidth = snakeStartLength;
                int minMapHeight = 2;

                int customMapWidth = getSafeMinValueInt(minMapWidth, "map width");
                int customMapHeight = getSafeMinValueInt(minMapHeight, "map height");

                game = new Game(customMapWidth, customMapHeight, snakeStartLength, cycleLength);
            }

            System.out.println("\033[2J");  // Clear setup prompts
            try {
                game.play();
            } catch (IOException e) {
                System.out.println("Terminal error: " + e.getMessage());
            }

            isPlaying = askYesNoQuestion("Do you want to play another round? (y/n)");
        }
    }

    public static boolean askYesNoQuestion(String question) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(question);

        String answer = "";
        while (!(answer.equals("y") || answer.equals("n"))) {
            answer = scanner.nextLine().toLowerCase();
        }

        return answer.equals("y");
    }

    public static int getSafeMinValueInt(int min, String label) {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.MIN_VALUE;

        while (number < min) {
            System.out.println("Enter " + label + " (min. " + min + "):");
            try {
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine(); // discard wrong input in scanner buffer
                number = Integer.MIN_VALUE;
            }
        }

        return number;
    }
}
