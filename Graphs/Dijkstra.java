package Graphs;
import java.util.*;

public class Dijkstra {

    public static int[] shortestDistance(List<List<Integer>> edges, int vertices) {

        //adjacency list
        List<List<int[][]>> con=new ArrayList<>();
        for(int i=0;i<vertices;i++)
            con.add(new ArrayList<>());

        for(int i=0; i < edges.size(); i++){

            int source = edges.get(i).get(0);
            int des = edges.get(i).get(1);
            int weight = edges.get(i).get(2);
//            con.get(x).add(y);
            con.get(source).add(new int[][]{{des , weight}});
//            con.get(y).add(x);

        }

        boolean[] visited = new boolean[vertices];
        int[] distance = new int[vertices];

        Arrays.fill(distance,Integer.MAX_VALUE);
        if(vertices == 0)
            return distance;
        int start = 0;

        Queue<Integer> Q = new LinkedList<Integer>();
        Q.add(start);
//        visited[start] = true;
        distance[start] = 0;

        while (! Q.isEmpty()) {
            int current = Q.remove();
            List<int[][]> neighbours = con.get(current);
            for (int[][] pair : neighbours) {
//                if (!visited[current]) {
                    if ((distance[current] + pair[0][1]) < distance[pair[0][0]]) {
                        distance[pair[0][0]] = distance[current] + pair[0][1];
                        Q.add(pair[0][0]);

                    }
//                }
            }
        }

        for (int i=0; i < vertices; i++)
        {
            if(distance[i] == Integer.MAX_VALUE)
                distance[i] = -1;
        }
//        System.out.println(Arrays.toString(distance));
        return distance;


    }

    /*public static void main(String args[]){


        List<Integer> e1 = Arrays.asList(0,1,4);List<Integer> e2 = Arrays.asList(0,2,2);
        List<Integer> e3 = Arrays.asList(1,3,2);List<Integer> e4 = Arrays.asList(1,4,3);
        List<Integer> e5 = Arrays.asList(2,1,1);List<Integer> e6 = Arrays.asList(2,3,2);
        List<Integer> e7 = Arrays.asList(2,4,5);List<Integer> e8 = Arrays.asList(4,3,1);
        List<Integer> e9 = Arrays.asList(5,2,2);
        List<List<Integer>> edges = Arrays.asList(e1,e2,e3,e4,e5,e6,e7,e8,e9);
        int[] distance = new int[6];
        int vertices = 6;
        distance = shortestDistance(edges, vertices  );
        System.out.println(Arrays.toString(distance));

    }*/
}
