import java.util.Arrays;
public class QuickSelect {
    static int select(int[] A, int k) {
        return subSelect(A , 0 , A.length-1 , k);
    }
    static int subSelect(int[] A, int p, int r ,int k) {
        if(p == r)
            return A[r];
        int pivot = partition(A,p,r);

        if(k == pivot)
            return A[pivot];
        else if(k < pivot)
            return subSelect(A, p , pivot - 1 , k);
        else
            return  subSelect(A, pivot + 1, r , k );
    }
    static int partition(int A[], int p, int r) {

        int x = medianOfMedian(A,p,r);

        int search_index;
        for(search_index = 0; search_index < A.length; search_index++){
            if (A[search_index] == x)
                break;
        }
        swap(A, search_index, r);
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
    static int partitionUtil(int A[], int start, int end)
    {
        int pivot = A[end];
        int first = start - 1;
        int second = start;
        while (second < end)
        {
            if (A[second] <= pivot)
            {
                first += 1;
                swap(A, first, second);
            }
            second += 1;
        }
        swap(A, first+1, end);
        return first+1;
    }

    static void swap(int A[], int from_, int to_ )
    {
        int tmp = A[to_];
        A[to_] = A[from_];
        A[from_] = tmp;
    }
    static int findMedian(int[] arr, int start, int end){
        Arrays.sort(arr, start, end);
        return arr[(start + end - 1)/2];
    }
    static int medianOfMedian(int[] arr, int start, int end){

        int n = end - start + 1;
        int [] median = new int[(n+4)/5];
        int counter;
        for(counter = 0; counter < n/5; counter++){
            median[counter] = findMedian(arr, start + counter * 5, (start + counter * 5)+5);
        }
        if (counter * 5 < n) {
            median[counter] = findMedian(arr, start + counter * 5, (start + counter * 5) + (n % 5));
            counter++;
        }

        int medOfMed;
        if (counter == 1){
            return median[counter-1];
        }
        else
            return medianOfMedian(median, 0, median.length-1);
    }

}