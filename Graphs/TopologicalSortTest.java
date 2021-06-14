package Graphs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

class TopologicalSortTest {

    @Test
    public void test1() {

        List<Integer> a1 = new ArrayList<>();
        List<Integer> a2 = new ArrayList<>();
        List<Integer> a3 = new ArrayList<>();
        List<Integer> a4 = new ArrayList<>();
        List<Integer> a5 = new ArrayList<>();
        List<Integer> a6 = new ArrayList<>();

        List<List<Integer>> pre_requisites = new ArrayList<>();
        a1.add(0);a1.add(1);
        a2.add(0);a2.add(2);
        a3.add(1);a3.add(2);
        a4.add(1);a4.add(3);
        a5.add(2);a5.add(4);
        a6.add(3);a6.add(4);
        pre_requisites.add(a1);pre_requisites.add(a2);pre_requisites.add(a3);pre_requisites.add(a4);
        pre_requisites.add(a5);pre_requisites.add(a6);
        List<Integer> output = new ArrayList<>();
        Collections.addAll(output,0,1,3,2,4);
        List<Integer> output1 = TopologicalSort.topoSort(pre_requisites,5);
        assertEquals(output,output);

    }


}