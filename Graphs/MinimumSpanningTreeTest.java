package Graphs;

import org.junit.Test;

import java.util.*;
import static org.junit.Assert.*;
public class MinimumSpanningTreeTest {
    @Test
    public void Test1() {
        int vertices = 3;
        // [u, v, w]
        Integer[][] ed = {{0, 1, 3}, {1, 2, 1}, {2, 0, 2}};
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (Integer[] arr : ed) edges.add(new ArrayList(Arrays.asList(arr)));
        // select [0, 2] and [1, 2]
        int ans = 3;
        assertEquals(MinimumSpanningTree.mst(edges, vertices), ans);
    }

}
