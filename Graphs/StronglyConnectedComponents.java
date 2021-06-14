package Graphs;

import java.util.List;
import java.util.LinkedList;
import java.util.*;

public class StronglyConnectedComponents {

    private int V;

    private LinkedList<Integer> adjacencyList[];

    //generate adjacency list
    StronglyConnectedComponents(int ver)
    {
        V = ver;
        adjacencyList = new LinkedList[ver];
        for (int i=0; i<ver; ++i)
            adjacencyList[i] = new LinkedList();
    }

    //insert edge into the graph
    void insertEdge(int ver, int w)  {
        adjacencyList[ver].add(w);
    }

    //Depth First Search
    List<Integer> DFSSearch(int ver, boolean visited[], List<Integer> D)
    {
        visited[ver] = true;
        D.add(ver);
        int n;

        Iterator<Integer> i = adjacencyList[ver].iterator();
        while (i.hasNext())
        {
            n = i.next();

            if (!visited[n])
                DFSSearch(n,visited,D);
        }
        return D;
    }

    StronglyConnectedComponents getTranspose()
    {
        StronglyConnectedComponents g = new StronglyConnectedComponents(V);
        for (int j = 0; j < V; j++)
        {
            Iterator<Integer> i = adjacencyList[j].listIterator();
            while(i.hasNext())
                g.adjacencyList[i.next()].add(j);
        }
        return g;
    }

    void insertOrder(int v, boolean visited[], Stack stack)
    {
        visited[v] = true;

        Iterator<Integer> i = adjacencyList[v].iterator();
        while (i.hasNext())
        {
            int n = i.next();
            if(!visited[n])
                insertOrder(n, visited, stack);
        }

        stack.push(new Integer (v));
    }

    List<List<Integer>> generateSSC()
    {
        Stack stack = new Stack();
        List<List<Integer>> newList = new ArrayList<List<Integer>>();

        boolean visited[] = new boolean[V];
        for(int i = 0; i < V; i++)
            visited[i] = false;

        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                insertOrder(i, visited, stack);

        StronglyConnectedComponents gr = getTranspose();
        for (int i = 0; i < V; i++)
            visited[i] = false;

        while (stack.empty() == false)
        {
            List<Integer> D = new ArrayList<Integer>();
            int v = (int)stack.pop();

            if (visited[v] == false)
            {
                newList.add(gr.DFSSearch(v, visited,D));
            }
        }
        return newList;
    }


    public static List<List<Integer>> scc(int students, List<List<Integer>> knows) {

        StronglyConnectedComponents sg = new StronglyConnectedComponents(students);
        for(int i = 0; i<knows.size();i++){

            sg.insertEdge(knows.get(i).get(0),knows.get(i).get(1));
        }
        return sg.generateSSC();
    }



}
