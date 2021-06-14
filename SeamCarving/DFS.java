package SeamCarving;

import java.util.*;

public class DFS {

    static void DFS_visit(List<List<Integer>> G, int u,
                          int[]  parent, boolean[] visited,
                          ArrayList<Integer> finish) {
        visited[u] = true;
        for (int v : G.get(u)) {
            if (! visited[v]) {
                parent[v] = u;
                DFS_visit(G, v, parent, visited, finish);
            }
        }
        finish.add(u);
    }

    static List<Integer> DFS(List<List<Integer>> G,int students) {
        int[] parent = new int[G.size()];
//        boolean[] visited = new boolean[G.size()];
        boolean[] visited = new boolean[students];

        for (int u = 0; u != G.size()-1; ++u) {
            parent[u] = u;
            visited[u] = false;
        }
        ArrayList<Integer> finish = new ArrayList<>();
        for (int u = 0; u != G.size()-1; ++u) {
            if (! visited[u])
                DFS_visit(G, u, parent, visited, finish);
        }
        return finish;
    }

    public static void main(String args[]){


        List<List<Integer>> knows = new ArrayList<>();
        List<Integer> a1= new ArrayList<>();
        List<Integer> a2= new ArrayList<>();
        List<Integer> a3= new ArrayList<>();
        List<Integer> a4= new ArrayList<>();
        List<Integer> a5= new ArrayList<>();
        List<Integer> a6= new ArrayList<>();

        a1.add(1);a1.add(0);
        a2.add(0);a2.add(2);
        a3.add(2);a3.add(1);
        a4.add(3);a4.add(2);
        a5.add(4);a5.add(3);
        a6.add(3);a6.add(4);
        knows.add(a1);knows.add(a2);knows.add(a3);
        knows.add(a4);knows.add(a5);knows.add(a6);

        List<Integer> a = DFS(knows,5);
        System.out.println("Traversal is:"+Arrays.asList(a));
    }

}
