import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

public class DijkstraAlgoTest {

    private static Stream<Arguments> undirectedGraph() {
        int[][] matrix = {
                {0, 9, 9, 6, 7, 0, 0, 0},
                {9, 0, 2, 6, 0, 7, 0, 0},
                {9, 2, 0, 0, 0, 0, 5, 0},
                {6, 6, 0, 0, 0, 0, 0, 0},
                {7, 0, 0, 0, 0, 0, 4, 1},
                {0, 7, 0, 0, 0, 0, 2, 0},
                {0, 0, 5, 0, 4, 2, 0, 4},
                {0, 0, 0, 0, 1, 0, 4, 0}
        };
        return Stream.of(
                Arguments.of(matrix, 2, new DijkstraOutput(
                        new int[]{9, 2, 0, 8, 9, 7, 5, 9},
                        new int[]{2, 2, -1, 1, 6, 6, 2, 6},
                        new int[]{0, 2, 0, 1, 6, 0, 1, 3, 5, 0, 3, 6, 4, 5, 7, 0, 3, 5, 0, 3, 0, 4, 7}
                )),
                Arguments.of(matrix, 5, new DijkstraOutput(
                        new int[]{13, 7, 7, 13, 6, 0, 2, 6},
                        new int[]{4, 5, 6, 1, 6, -1, 5, 6},
                        new int[]{0, 5, 1, 6, 0, 1, 6, 2, 4, 7, 0, 1, 4, 0, 0, 1, 7, 0, 1, 3, 0, 2, 0, 3}
                ))
        );
    }

    private static Stream<Arguments> directedGraph() {
        int[][] matrix = {
                {0, 2, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 1, 0},
                {0, 9, 0, 0, 2, 0, 3, 0},
                {0, 5, 0, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 0, 0, 9, 1},
                {0, 5, 0, 1, 0, 0, 6, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 2, 0}
        };
        return Stream.of(
                Arguments.of(matrix, 1, new DijkstraOutput(
                        new int[]{Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 5, Integer.MAX_VALUE, 4, 1, Integer.MAX_VALUE},
                        new int[]{-1, -1, -1, 5, -1, 1, 1, -1},
                        new int[]{0, 1, 5, 6, 0, 5, 6, 0, 5, 3, 0, 3, 0, -1}
                )),
                Arguments.of(matrix, 6, new DijkstraOutput(
                        new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
                        new int[]{-1, -1, -1, -1, -1, -1, -1, -1},
                        new int[]{0, 6, 0, -1}
                ))
        );
    }

    private static Stream<Arguments> emptyGraph() {
        int[][] matrix = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        return Stream.of(
                Arguments.of(matrix, 0, new DijkstraOutput(
                        new int[]{0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                        new int[]{-1, -1, -1, -1, -1, -1, -1, -1},
                        new int[]{0, 1, -1}
                )),
                Arguments.of(matrix, 5, new DijkstraOutput(
                        new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE},
                        new int[]{-1, -1, -1, -1, -1, -1, -1, -1},
                        new int[]{0, 5, 0, -1}
                ))
        );
    }

    private static Stream<Arguments> invalidIndex() {
        return Stream.of(
                Arguments.of(new int[][]{
                        new int[]{0, 0},
                        new int[]{0, 0}
                }, 3)
        );
    }

    private static Stream<Arguments> invalidMatrix() {
        return Stream.of(
                Arguments.of(new int[][]{
                        new int[]{0, 0},
                        new int[]{0, 0},
                        new int[]{0, 0},
                        new int[]{0, 0},
                }, 0),
                Arguments.of(new int[][]{
                        new int[]{0, 0, 0},
                        new int[]{0},
                        new int[]{0, 0}
                }, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("undirectedGraph")
    void undirectedGraphTest(int[][] matrix, int start, DijkstraOutput dijkstraOutput) {
        assertEquals(dijkstraOutput, DijkstraAlgo.findPaths(DijkstraAlgo.adjacencyMatrixToList(matrix), start));
    }

    @ParameterizedTest
    @MethodSource("directedGraph")
    void directedGraphTest(int[][] matrix, int start, DijkstraOutput dijkstraOutput) {
        assertEquals(dijkstraOutput, DijkstraAlgo.findPaths(DijkstraAlgo.adjacencyMatrixToList(matrix), start));
    }

    @ParameterizedTest
    @MethodSource("emptyGraph")
    void emptyGraphTest(int[][] matrix, int start, DijkstraOutput dijkstraOutput) {
        assertEquals(dijkstraOutput, DijkstraAlgo.findPaths(DijkstraAlgo.adjacencyMatrixToList(matrix), start));
    }

    @ParameterizedTest
    @MethodSource("invalidIndex")
    void invalidIndexTest(int[][] matrix, int start) {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> DijkstraAlgo.findPaths(DijkstraAlgo.adjacencyMatrixToList(matrix), start));
    }

    @ParameterizedTest
    @MethodSource("invalidMatrix")
    void invalidMatrixTest(int[][] matrix, int start) {
        assertThrows(IllegalArgumentException.class, () -> DijkstraAlgo.findPaths(DijkstraAlgo.adjacencyMatrixToList(matrix), start));
    }
}
