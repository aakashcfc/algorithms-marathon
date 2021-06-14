package Graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopologicalSort {

    private static int UNTAKEN = 0;
    private static int TAKING  = 1;
    private static int TAKEN   = 2;

    public static List<Integer> topoSort(List<List<Integer>> pre_requisites, int total_courses) {

        int[] states = new int[total_courses];

        List<Integer>[] g = new List[total_courses];
        for (List<Integer> req : pre_requisites) {

            List<Integer> edges = g[req.get(0)];
            if (edges == null) {
                edges = new ArrayList<Integer>();
                g[req.get(0)] = edges;
            }
            edges.add(req.get(1));
        }
        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < total_courses; ++i) {
            if (!take(i, g, states, output)) {
                output.add(0);
                return output;
            }
        }
        Collections.reverse(output);

        return output ;
    }

    private static boolean take(int course, List<Integer>[] g, int[] states,
                                List<Integer> output) {
        if (states[course] == TAKEN)
            return true;
        if (states[course] == TAKING)
            return false;
        states[course] = TAKING;
        List<Integer> reqs = g[course];
        if (reqs != null) {
            for (int c : reqs) {

                if (!take(c, g, states, output)) {
                    return false;
                }
            }
        }
        output.add(course);
        states[course] = TAKEN;
        return true;
    }


}
