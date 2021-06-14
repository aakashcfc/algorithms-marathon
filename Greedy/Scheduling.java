import java.util.*;

public class Scheduling {

    public static int schedule(int[][] A) {

        for(int i=0;i<A.length;i++){
            if(A[i][0]>=A[i][1]){
                A[i][1]+=86400-A[i][0];
            }

        }
       Arrays.sort(A, java.util.Comparator.<int[]>comparingInt(a -> a[1]).thenComparingInt(a -> a[1]));


        int noOfJobs = 0;
        int previous[]=A[0];
        for(int i=1; i<A.length; i++){
            System.out.println("Previous["+i+"]="+previous[1]+"A[i][0]="+A[i][0]);
            if(A[i][0] >= previous[1]){

                previous = A[i];
                noOfJobs++;
            }
        }
        return noOfJobs;
    }

    public static void main(String args[]){

        int A[][] = {{100, 86000}, {86000, 100}};
        int n = schedule(A);
        System.out.println("no of jobs="+n);
    }

}


