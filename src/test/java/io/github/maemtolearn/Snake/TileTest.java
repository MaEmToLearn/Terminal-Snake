package io.github.maemtolearn.Snake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    private final int row = 0;
    private final int column = 0;
    private final Coordinates coordinates = new Coordinates(row, column);
    private final Tile tile = new Tile(coordinates);

    @Test
    void coordinates1() {
        Tile test = new Tile(coordinates);
        assertEquals(column, test.coordinates().row());
        assertEquals(row, test.coordinates().column());
    }

    @Test
    @DisplayName("isKill: Returns underlying enum isKill property")
    void isKill1() {
        tile.setType(TileType.H_WALL);
        assertTrue(tile.isKill());
        tile.setType(TileType.FOOD);
        assertFalse(tile.isKill());
    }

    @Test
    @DisplayName("isFood: Returns underlying enum isFood property")
    void isFood1() {
        tile.setType(TileType.FOOD);
        assertTrue(tile.isFood());
        tile.setType(TileType.EMPTY);
        assertFalse(tile.isFood());
    }

    @Test
    @DisplayName("isEmpty: Returns underlying enum isEmpty property")
    void isEmpty1() {
        tile.setType(TileType.EMPTY);
        assertTrue(tile.isEmpty());
        tile.setType(TileType.SNAKE);
        assertFalse(tile.isEmpty());
    }

    @ParameterizedTest
    @DisplayName("setAsHead: Direction enum: Set correct TileType")
    @MethodSource("provideDirectionHeadTypeCombination")
    void setAsHead1(Direction direction, TileType expectedTileType) {
        tile.setAsHead(direction);
        assertEquals(expectedTileType.symbol(), tile.toString());
    }

    @Test
    @DisplayName("toString: Return TileType symbol as String")
    void toString1() {
        TileType testType = TileType.SNAKE;
        tile.setType(testType);
        assertEquals(testType.symbol(), tile.toString());
    }

    private static Stream<Arguments> provideDirectionHeadTypeCombination() {
        return Stream.of(
            Arguments.of(Direction.NORTH, TileType.HEAD_NORTH),
            Arguments.of(Direction.EAST, TileType.HEAD_EAST),
            Arguments.of(Direction.SOUTH, TileType.HEAD_SOUTH),
            Arguments.of(Direction.WEST, TileType.HEAD_WEST)
        );
    }
}
