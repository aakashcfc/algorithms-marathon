import java.util.ArrayList;
import java.util.List;

public class TopologicalSort{

    // course state
    private static int UNTAKEN = 0;
    private static int TAKING  = 1;
    private static int TAKEN   = 2;

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // init courses with UNTAKEN state
        int[] states = new int[numCourses];

        // init graph: from course pointing to all its prerequisites
        List<Integer>[] g = new List[numCourses];
        for (int[] req : prerequisites) {
            // req[0] -> req[1]
            List<Integer> edges = g[req[0]];
            if (edges == null) {
                edges = new ArrayList<Integer>();
                g[req[0]] = edges;
            }
            edges.add(req[1]);
        }

        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (!take(i, g, states, output)) {
                return new int[0];
            }
        }
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            ans[i] = output.get(i);
        }
        return ans;
    }

    // return false if course cannot be taken.
    private boolean take(int course, List<Integer>[] g, int[] states,
                         List<Integer> output) {
        if (states[course] == TAKEN)
            return true;
        if (states[course] == TAKING)
            return false; // circle found
        states[course] = TAKING;
        List<Integer> reqs = g[course];
        if (reqs != null) {
            for (int c : reqs) {
                // take all its reqs first.
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
