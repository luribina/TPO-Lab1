import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class IntPair {
    int left, right;

    public IntPair(int left, int right) {
        this.left = left;
        this.right = right;
    }
}

class DijkstraOutput {
    int[] costs;
    int[] paths;
    int[] visited;

    public DijkstraOutput(int[] costs, int[] paths, int[] visited) {
        this.costs = costs;
        this.paths = paths;
        this.visited = visited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DijkstraOutput that = (DijkstraOutput) o;
        return Arrays.equals(costs, that.costs) && Arrays.equals(paths, that.paths) && Arrays.equals(visited, that.visited);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(costs);
        result = 31 * result + Arrays.hashCode(paths);
        result = 31 * result + Arrays.hashCode(visited);
        return result;
    }
}

public class DijkstraAlgo {
    public static List<List<IntPair>> adjacencyMatrixToList(int[][] matrix) {
        int size = matrix.length;
        ArrayList<List<IntPair>> graph = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            graph.add(new ArrayList<>());
            if (matrix[i].length != size) {
                throw new IllegalArgumentException("Invalid matrix size");
            }
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] != 0) {
                    graph.get(i).add(new IntPair(j, matrix[i][j]));
                }
            }
        }
        return graph;
    }

    public static DijkstraOutput findPaths(List<List<IntPair>> graph, int start) {
        ArrayList<Integer> paths = new ArrayList<>();
        int size = graph.size();
        int[] d = new int[size],
                p = new int[size];
        Arrays.fill(d, Integer.MAX_VALUE);
        Arrays.fill(p, -1);
        d[start] = 0;

        boolean[] u = new boolean[size];
        Arrays.fill(u, false);

        for (int i = 0; i < size; i++) {
            int v = -1;
            for (int j = 0; j < size; j++) {
                if (!u[j] && (v == -1 || d[j] < d[v])) {
                    v = j;
                    paths.add(v);
                }
            }
            if (d[v] == Integer.MAX_VALUE) {
                paths.add(-1);
                break;
            }
            u[v] = true;

            for (int j = 0; j < graph.get(v).size(); j++) {
                int to = graph.get(v).get(j).left,
                        len = graph.get(v).get(j).right;
                if (d[v] + len < d[to]) {
                    d[to] = d[v] + len;
                    p[to] = v;
                    paths.add(to);
                }
            }
        }
        return new DijkstraOutput(d, p, paths.stream().mapToInt(i -> i).toArray());
    }
}

