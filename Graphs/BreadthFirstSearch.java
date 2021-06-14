package Graphs;

import java.util.*;
public class BreadthFirstSearch {

    public static int[] breadthFirstSearch(List<List<Integer>> edges,int vertices) {

        List<List<Integer>> con=new ArrayList<>();
        for(int i=0;i<vertices;i++)
            con.add(new ArrayList<>());

        for(int i=0; i < edges.size(); i++){

            int x = edges.get(i).get(0);
            int y = edges.get(i).get(1);

            con.get(x).add(y);
//            con.get(y).add(x);

        }



        boolean[] visited = new boolean[vertices];
        int[] parent = new int[vertices];
        int[] distance = new int[vertices];
        Arrays.fill(distance,-1);
        if(vertices == 0)
            return distance;
        int start = 0;
        Queue<Integer> Q = new LinkedList<Integer>();
        Q.add(start);
        visited[start] = true;
        distance[start] = 0;

        while (! Q.isEmpty()) {
            int u = Q.remove();
            for (int v : con.get(u))
                if (! visited[v]) {
                    distance[v] = distance[u] + 1;
                    parent[v] = u;
                    Q.add(v);
                    visited[v] = true;
                }
        }
        return distance;

    }


   /* public static void main(String args[]){


        List<Integer> e1 = new ArrayList<>();List<Integer> e2 = new ArrayList<>();List<Integer> e3 = new ArrayList<>();
        List<Integer> e4 = new ArrayList<>();List<Integer> e5 = new ArrayList<>();List<Integer> e6 = new ArrayList<>();
        e1.add(0);e1.add(1);e2.add(0);e2.add(2);e3.add(1);e3.add(2);e4.add(2);e4.add(0);e5.add(2);e5.add(3);e6.add(3);e6.add(3);
        List<List<Integer>> edges = Arrays.asList(e1,e2,e3,e4,e5);
        int[] distance = new int[6];
        int vertices = 5;
        distance = breadthFirstSearch(edges, vertices  );
        System.out.println(Arrays.toString(distance));

    }*/
}