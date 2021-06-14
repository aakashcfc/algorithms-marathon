public class BinarySearch {


    static int search(int A[], int key) {

        int start=0, end=A.length-1;

        while(start <= end){

            int mid = (start + end) /2;
            if(A[mid] == key)
                return mid;
            if(A[mid] < key)
                start= mid+1;
            else
                end = mid-1;

        }
        return -1;
    }
}