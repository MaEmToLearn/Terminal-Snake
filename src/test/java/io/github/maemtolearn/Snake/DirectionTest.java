package io.github.maemtolearn.Snake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @ParameterizedTest
    @MethodSource("provideDirectionsAndOppositeDirections")
    @DisplayName("opposite: Return opposite direction")
    void opposite1(Direction input, Direction expected) {
        assertEquals(expected, input.opposite());
    }

    private static Stream<Arguments> provideDirectionsAndOppositeDirections() {
        return Stream.of(
            Arguments.of(Direction.NORTH, Direction.SOUTH),
            Arguments.of(Direction.SOUTH, Direction.NORTH),
            Arguments.of(Direction.WEST, Direction.EAST),
            Arguments.of(Direction.EAST, Direction.WEST)
        );
    }
}
