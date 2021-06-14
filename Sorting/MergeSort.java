public class MergeSort {

        static void merge(int A[], int p, int q, int r) {

            int n1 = q - p + 1;
            int n2 = r - q;

            int left[] = new int[n1];
            int right[] = new int[n2];

            for (int i = 0; i < n1; ++i)
                left[i] = A[p + i];
            for (int j = 0; j < n2; ++j)
                right[j] = A[q + 1 + j];


            int i = 0, j = 0;


            int k = p;
            while (i < n1 && j < n2) {
                if (left[i] <= right[j]) {
                    A[k] = left[i];
                    i++;
                }
                else {
                    A[k] = right[j];
                    j++;
                }
                k++;
            }

            while (i < n1) {
                A[k] = left[i];
                i++;
                k++;
            }

            while (j < n2) {
                A[k] = right[j];
                j++;
                k++;
            }
        }

        static void sort(int A[],int p,int r) {
            if(A.length == r)
                r = r-1;
            if (p < r) {

                int q = p + (r - p) / 2;
                sort(A, p, q);
                sort(A, q + 1, r);
                merge(A, p, q, r);
            }
        }



}
