import java.util.Arrays;

public class HeapSort{

    static void heapify(int A[], int n, int i){

        int largest = i;  //non-leaf node at largest index
        int l = (2*i) + 1;
        int r = (2*i) + 2;
        while(l < n && A[l] > A[largest])
            largest = l;
        while(r < n && A[r] > A[largest])
            largest = r;
        if(largest != i)
        {
            int tmp = A[i];
            A[i] = A[largest];
            A[largest] = tmp;
            heapify(A , n , largest);

        }
        System.out.println(Arrays.toString(A));

    }


    static void heapSort(int A[]){

        int n =  A.length;
        for (int i = n/2 - 1 ; i >= 0; i--) {

            heapify(A , n , i);
        }

        //deleting root
        for(int i = n - 1; i > 0 ; i--){

            int tmp = A[0];
            A[0] = A[i];
            A[i] = tmp;
            heapify(A , i , 0);
        }
        //System.out.println("Array after heap sort is:"+Arrays.toString(A));
    }

    public static void main(String args[]){

        int A[] = new int[]{6,11,10,7,8,9,5,4,3,2,1};
//        heapSort(A);
        heapify(A,11,0);
        System.out.println("Array after heap sort is:"+Arrays.toString(A));
    }
}