package Graphs;

import java.util.*;

public class Bipartiteness {

    public static int bipartite(List<List<Integer>> edges, int vertices) {

        if(vertices == 0)
            return 1;

        List<List<Integer>> con=new ArrayList<>();
        for(int i=0;i<vertices;i++)
            con.add(new ArrayList<>());
        for(int i=0; i < edges.size(); i++){

            int x = edges.get(i).get(0);
            int y = edges.get(i).get(1);
            con.get(x).add(y);
            con.get(y).add(x);

        }

        int color[] = new int[vertices];
        for (int i=0; i<vertices; ++i)
            color[i] = -1;

        for (int i = 0; i < vertices; i++)
            if (color[i] == -1)
                if (isBipartite(con, i, color) == -1)
                    return -1;

        return 1;



    }

    public static int isBipartite(List<List<Integer>> con , int src , int color[]){


        color[src] = 1;
        LinkedList<Integer>q = new LinkedList<Integer>();
        q.add(src);

        while (q.size() != 0)
        {

            int u = q.poll();

            for (int v : con.get(u))
                if (color[v] == -1) {

                    color[v] = 1-color[u];
                    q.add(v);
//                    visited[v] = true;
                }
                else if(color[v]==color[u])
                    return -1;
        }

        return 1;

    }


    /*public static void main(String args[]){


        List<Integer> e1 = Arrays.asList(0,6);List<Integer> e2 = Arrays.asList(1,6);
        List<Integer> e3 = Arrays.asList(1 ,2);List<Integer> e4 = Arrays.asList(5,6);
        List<Integer> e5 = Arrays.asList(3,2);List<Integer> e6 = Arrays.asList(3,4);
        List<Integer> e7 = Arrays.asList(6,2);
        List<List<Integer>> edges = Arrays.asList(e1,e2,e3,e4,e5,e6,e7);
        int result = bipartite(edges , 7);
        System.out.println("result = "+result);
    }*/

}
