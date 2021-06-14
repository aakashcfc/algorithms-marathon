package Graphs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CriticalLinkTest {

    @Test
    public void Test1() {
        int[][] links = {{0,1},{1,2},{2,0},{1,3},{2,4}};
        int ans = 2;
        assertEquals(ans, new CriticalLink().criticalLink(5, links));
    }

    @Test
    public void Test2() {
        int[][] links = {{0,1},{1,2},{2,0},{1,3},{2,4}};
        int ans = 2;
        assertEquals(ans, new CriticalLink().criticalLink(5, links));
    }

}
