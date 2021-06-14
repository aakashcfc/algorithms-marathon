package Graphs;

import java.util.*;

class CriticalLink {

    public int criticalLink(int n, int[][] links) {

        List<List<Integer>> cc=new ArrayList<>(),con=new ArrayList<>();

        for(int i=0;i<n;i++)
            con.add(new ArrayList<>());

        for(int i=0; i < links.length; i++){

            int x = links[i][0];
            int y = links[i][1];
            con.get(x).add(y);
            con.get(y).add(x);

        }
        int d[] = new int[n];
        get_criticalConnection(0,con,d,new int[n],new boolean[n],1,0,cc);

        return cc.size();


    }

    public void get_criticalConnection(int start,List<List<Integer>> con,int discovery[],int lowDiscovery[],boolean visited[],int d,int parent,List<List<Integer>> cc){

        discovery[start]=lowDiscovery[start]=d;     //initialize to 1 for start node
        visited[start]=true;

        for(int i:con.get(start)){

            if(!visited[i]){
                d++;
                get_criticalConnection(i,con,discovery,lowDiscovery,visited,d,start,cc);
            }
            if(i != parent)
                lowDiscovery[start] = Math.min(lowDiscovery[start],lowDiscovery[i]);
        }

        //get list of articulation points
        if(discovery[parent] < lowDiscovery[start]){

            List<Integer> time= new ArrayList<>();
            time.add(parent);
            time.add(start);
            cc.add(time);
        }
//        return;
    }
}
