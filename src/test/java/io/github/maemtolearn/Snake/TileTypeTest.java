package io.github.maemtolearn.Snake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TileTypeTest {

    @ParameterizedTest
    @DisplayName("isEmpty: Is the tile empty")
    @MethodSource("enumIsEmpty")
    void isEmpty1(TileType type, boolean expected) {
        assertEquals(expected, type.isEmpty());
    }

    @ParameterizedTest
    @DisplayName("isKill: Has the tile a kill condition")
    @MethodSource("enumIsKill")
    void isKill1(TileType type, boolean expected) {
        assertEquals(expected, type.isKill());
    }

    @ParameterizedTest
    @DisplayName("isFood: Has the tile a food item")
    @MethodSource("enumIsFood")
    void isFood1(TileType type, boolean expected) {
        assertEquals(expected, type.isFood());
    }

    @ParameterizedTest
    @DisplayName("TileType has a symbol")
    @MethodSource("enumFactory")
    void tileType1(TileType type) {
        assertFalse(type.symbol().isEmpty());
    }

    private static Stream<TileType> enumFactory() {
        return Stream.of(
            TileType.SNAKE,
            TileType.FOOD,
            TileType.H_WALL,
            TileType.V_WALL,
            TileType.EMPTY,
            TileType.DEATH,
            TileType.HEAD_NORTH,
            TileType.HEAD_EAST,
            TileType.HEAD_SOUTH,
            TileType.HEAD_WEST
        );
    }

    private static Stream<Arguments> enumIsEmpty() {
        return Stream.of(
            Arguments.of(TileType.SNAKE, false),
            Arguments.of(TileType.FOOD, false),
            Arguments.of(TileType.H_WALL, false),
            Arguments.of(TileType.V_WALL, false),
            Arguments.of(TileType.EMPTY, true),
            Arguments.of(TileType.DEATH, false),
            Arguments.of(TileType.HEAD_NORTH, false),
            Arguments.of(TileType.HEAD_EAST, false),
            Arguments.of(TileType.HEAD_SOUTH, false),
            Arguments.of(TileType.HEAD_WEST, false)
        );
    }

    private static Stream<Arguments> enumIsKill() {
        return Stream.of(
            Arguments.of(TileType.SNAKE,true),
            Arguments.of(TileType.FOOD, false),
            Arguments.of(TileType.H_WALL, true),
            Arguments.of(TileType.V_WALL, true),
            Arguments.of(TileType.EMPTY, false),
            Arguments.of(TileType.DEATH, true),
            Arguments.of(TileType.HEAD_NORTH, true),
            Arguments.of(TileType.HEAD_EAST, true),
            Arguments.of(TileType.HEAD_SOUTH, true),
            Arguments.of(TileType.HEAD_WEST, true)
        );
    }

    private static Stream<Arguments> enumIsFood() {
        return Stream.of(
            Arguments.of(TileType.SNAKE, false),
            Arguments.of(TileType.FOOD, true),
            Arguments.of(TileType.H_WALL, false),
            Arguments.of(TileType.V_WALL, false),
            Arguments.of(TileType.EMPTY, false),
            Arguments.of(TileType.DEATH, false),
            Arguments.of(TileType.HEAD_NORTH, false),
            Arguments.of(TileType.HEAD_EAST, false),
            Arguments.of(TileType.HEAD_SOUTH, false),
            Arguments.of(TileType.HEAD_WEST, false)
        );
    }
}
