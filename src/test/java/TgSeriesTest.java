import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

class TgSeriesTest {
    private final double PRECISION = 0.001;
    private final int TERMS = 10;

    private static Stream<Arguments> provideParamsBasic() {
        return Stream.of(
                Arguments.of(-PI / 6, -sqrt(3) / 3, "-PI/6"),
                Arguments.of(-PI / 4, -1, "-PI/4"),
                Arguments.of(-PI / 3, -sqrt(3), "-PI/3"),
                Arguments.of(0.0, 0, "0.0"),
                Arguments.of(-0.0, 0, "0.0"),
                Arguments.of(PI / 6, sqrt(3) / 3, "PI/6"),
                Arguments.of(PI / 4, 1, "PI/4"),
                Arguments.of(PI / 3, sqrt(3), "PI/3")
        );
    }

    private static Stream<Arguments> provideParamsInvalid() {
        return Stream.of(
                Arguments.of(-PI / 2, 3, "-PI/2"),
                Arguments.of(PI / 2, 3, "PI/2"),
                Arguments.of(-PI, 3, "-PI"),
                Arguments.of(PI, 3, "PI"),
                Arguments.of(0, 11, "0"),
                Arguments.of(0, 0, "0"),
                Arguments.of(0, -1, "0"),
                Arguments.of(Double.NaN, 10, "0"),
                Arguments.of(-Double.NaN, 10, "0"),
                Arguments.of(Double.NEGATIVE_INFINITY, 10, "0"),
                Arguments.of(Double.POSITIVE_INFINITY, 10, "0")
        );
    }

    private static Stream<Arguments> provideParamsBoundary() {
        return Stream.of(
                Arguments.of(-PI / 2 + 0.01, 99.99666664444, "-PI/2+delta"),
                Arguments.of(PI / 2 - 0.01, -99.99666664444, "PI/2-delta")
        );
    }

    @DisplayName("Basic tg taylor series test")
    @ParameterizedTest(name = "x = {2}")
    @MethodSource("provideParamsBasic")
    public void basicArgumentsTest(double x, double expected, String xName) {
        assertEquals(TgSeries.calculate(x, TERMS), expected, PRECISION);
    }

    @DisplayName("Invalid arguments tg taylor series test")
    @ParameterizedTest(name = "x = {2}, terms = {1}")
    @MethodSource("provideParamsInvalid")
    public void invalidArgumentsTest(double x, int terms, String xName) {
        assertThrows(IllegalArgumentException.class, () -> TgSeries.calculate(x, terms));
    }

    @DisplayName("Boundary arguments tg taylor series test")
    @ParameterizedTest(name = "x = {2}")
    @MethodSource("provideParamsBoundary")
    public void boundaryArgumentsTest(double x, double expected, String xName) {
        assertEquals(TgSeries.calculate(x, TERMS), expected, PRECISION);
    }
}
