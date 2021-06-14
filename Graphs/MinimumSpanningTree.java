package Graphs;

import java.util.*;
import java.util.stream.Collectors;

public class MinimumSpanningTree {

    static class Edge implements Comparable<MinimumSpanningTree.Edge>
    {
        int src, dest, weight;


        public int compareTo(MinimumSpanningTree.Edge compareEdge)
        {
            return this.weight - compareEdge.weight;
        }
    }

    static class subset
    {
        int parent, rank;
    };

    int V, E;
    static MinimumSpanningTree.Edge edge[];


    MinimumSpanningTree(int v, int e)
    {
        V = v;
        E = e;
        edge = new MinimumSpanningTree.Edge[E];
        for (int i = 0; i < e; ++i)
            edge[i] = new MinimumSpanningTree.Edge();
    }

    public static int mst(ArrayList<ArrayList<Integer>> edges, int vertices) {

        int V = vertices;
        int E = edges.size();
        MinimumSpanningTree graph = new MinimumSpanningTree(V, E);
        for(int i=0; i < edges.size(); i++){

            graph.edge[i].src = edges.get(i).get(0);;
            graph.edge[i].dest = edges.get(i).get(1);
            graph.edge[i].weight = edges.get(i).get(2);

        }
        MinimumSpanningTree.Edge result[] = new MinimumSpanningTree.Edge[V];


        int e = 0;


        int i = 0;
        for (i = 0; i < V; ++i)
            result[i] = new MinimumSpanningTree.Edge();


        Arrays.sort(edge);


        MinimumSpanningTree.subset subsets[] = new MinimumSpanningTree.subset[V];
        for (i = 0; i < V; ++i)
            subsets[i] = new MinimumSpanningTree.subset();


        for (int v = 0; v < V; ++v)
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0;


        while (e < V - 1)
        {

            MinimumSpanningTree.Edge next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);


            if (x != y) {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
            // Else discard the next_edge
        }

        int minimumCost = 0;
        for (i = 0; i < e; ++i)
        {

            minimumCost += result[i].weight;
        }

        return minimumCost;

    }

    static void Union(MinimumSpanningTree.subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);


        if (subsets[xroot].rank
                < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank
                > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    static int find(MinimumSpanningTree.subset subsets[], int i)
    {

        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }


   /* public static void main(String args[]){

        int vertices = 3;
        // [u, v, w]
        Integer[][] ed = {{0, 1, 3}, {1, 2, 1}, {2, 0, 2}};
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (Integer[] arr : ed) edges.add(new ArrayList(Arrays.asList(arr)));
        int cost = mst(edges,vertices);
        System.out.println("cost = "+cost);
    }*/

}