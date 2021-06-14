
    public class BinarySearch2D {


        static int[] search(int[][] M, int q) {

            if(M.length == 0)
                return new int[]{-1,-1};
            int row = M.length-1, col = M[0].length-1;

            int row_start = 0 ,row_end = row, row_mid;
            int col_start,col_end;
            while(row_start <= row_end)
            {
                row_mid = (row_start + row_end)/ 2;

                if (M[row_mid][0] <= q && q <= M[row_mid][col]) {
                    col_start = 0;
                    col_end = col;
                    while (col_start <= col_end) {
                        int col_mid = (col_start + col_end) / 2;
                      
                        if (M[row_mid][col_mid] == q)
                            return new int[]{row_mid, col_mid};
                        else if (M[row_mid][col_mid] > q)
                            col_end = col_mid - 1;
                        else
                            col_start = col_mid + 1;


                    }
                    break;
                }
                else if(M[row_mid][0] > q)
                    row_end = row_mid - 1;
                else
                    row_start = row_mid + 1;
            }

            return new int[]{-1,-1};
        }

    }

