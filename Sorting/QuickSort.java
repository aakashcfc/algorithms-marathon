import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    static int partition(int A[], int p, int r) {

        getRandomNumber(A,p , r);
        int x = A[r];

        int i = p - 1;
        for(int j = p ; j < r; j++) {

            if (A[j] < x)
            {
                i = i + 1;
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        int temp = A[i + 1];
        A[i + 1] = A[r];
        A[r] = temp;
        return i+1;
    }

    public static void getRandomNumber(int A[],int min, int max) {

        Random rand = new Random();
        int x = rand.nextInt(max-min)+min;
        int temp = A[x];
        A[x]= A[max];
        A[max]= temp;

    }

    static void sort(int A[], int p, int r) {
        if(A.length == r)
            r = r-1;
        if(p < r)
        {
            int q = partition(A , p , r);
            sort(A , p , q-1);
            sort(A , q+1 , r);


        }
    }


}