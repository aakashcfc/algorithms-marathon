import static org.junit.Assert.*;
public class InsertionSort {
    static void sort(int[] A)
    {
        for (int i = 1; i < A.length; i++) {
            int key = A[i];
            int j = i - 1;
            assertEquals(true,sorted(A,i-1,j));
            while (j >= 0 && A[j] > key) {
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = key;
        }

    }

    static Boolean sorted(int[] A, int begin, int end) {
        for (int i = begin; i != end; ++i) {
            if (i + 1 != end) {
                if (A[i] > A[i+1]) return false;
            }
        }
        return true;
    }

}