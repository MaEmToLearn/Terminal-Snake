package io.github.maemtolearn.Snake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {
    @Test
    @DisplayName("increment: Direction North: Row - 1")
    void increment1() {
        Coordinates coordinates = new Coordinates(1, 3).increment(Direction.NORTH);
        assertEquals(0, coordinates.getRow());
        assertEquals(3, coordinates.getColumn());
    }

    @Test
    @DisplayName("increment: Direction South: Row + 1")
    void increment2() {
        Coordinates coordinates = new Coordinates(2, 2).increment(Direction.SOUTH);
        assertEquals(3, coordinates.getRow());
        assertEquals(2, coordinates.getColumn());
    }

    @Test
    @DisplayName("increment: Direction East: Column + 1")
    void increment3() {
        Coordinates coordinates = new Coordinates(4, 2).increment(Direction.EAST);
        assertEquals(4, coordinates.getRow());
        assertEquals(3, coordinates.getColumn());
    }

    @Test
    @DisplayName("increment: Direction West: Column - 1")
    void increment4() {
        Coordinates coordinates = new Coordinates(1, 1).increment(Direction.WEST);
        assertEquals(1, coordinates.getRow());
        assertEquals(0, coordinates.getColumn());
    }
}
