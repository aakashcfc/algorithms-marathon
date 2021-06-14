package SeamCarving.src;

import java.util.Arrays;

class SeamCarving {

    static int[] carve_seam(int disruption[][]) {

        int[] seam = new int[disruption.length];
        int rows = disruption.length;
        int col = disruption[0].length;

        int[][] prev = new int[rows][col];


        for (int i = 0; i < rows; i++)
        {

            for (int j = 0; j < col; j++) {

                int min_value;

                if (i == 0) {
                    prev[i][j] = disruption[i][j];
                    //prev[i][j] = -1;
                    continue;
                }
                //edge
                if (j == 0) {
                    min_value = Math.min(prev[i-1][j], prev[i - 1][j + 1]);

                    prev[i][j] = disruption[i][j] + min_value;

                }

                else if (j == col - 1) {
                    min_value = Math.min(prev[i - 1][j], prev[i - 1][j - 1]);
                    prev[i][j] = disruption[i][j] + min_value;
                }
                //min of 3  neighbors
                else {
                    min_value = Math.min(prev[i - 1][j - 1], Math.min(prev[i - 1][j], prev[i - 1][j + 1]));

                    prev[i][j] = disruption[i][j] + min_value;

                }

            }

        }
        //System.out.println("Optimal matrix = "+Arrays.deepToString(prev));
        int minIndex = getMin(prev[prev.length - 1]);
       // System.out.println("min index = "+minIndex);
        seam[seam.length - 1] = minIndex;
        int row = prev.length - 1;
        int column  = minIndex;
        for(int i = prev.length - 2 ; i >= 0 ; i--){

            if(column == 0){

                if(prev[row - 1][column] < prev[row - 1][column + 1]) {
                    row = row - 1;
                    seam[i] = column;
                }
                else{
                    seam[i] = column + 1;
                    row = row - 1;
                    column = column + 1;
                }

            }
            else if(column == prev[0].length - 1){

                if(prev[row - 1][column] < prev[row - 1][column - 1]){

                    row = row - 1;
                    seam[i] = column;
                }
                else{

                    seam[i] = column - 1;
                    row = row - 1;
                    column = column - 1;
                }


            }
            else{

                if(prev[row - 1][column] < prev[row - 1][column + 1] && prev[row - 1][column] < prev[row - 1][column - 1]){

                    row = row - 1;
                    seam[i] = column;
                }
                else if( prev[row - 1][column + 1]  < prev[row - 1][column - 1]){

                    seam[i] = column + 1;
                    row = row - 1;
                    column = column + 1;
                }
                else{
                    seam[i] = column - 1;
                    row = row - 1;
                    column = column - 1;

                }

            }

        }
        return seam;
    }

    public static int getMin(int[] array){

        if (array.length == 0)
            return -1;

        int index = 0;
        int min = array[index];

        for (int i = 1; i < array.length; i++){
            if (array[i] <= min){
                min = array[i];
                index = i;
            }
        }
        return index;
    }


    public static void main(String args[]){

        int[][] disruption = {

              /*  {1, 2, 0, 3},
                {1, 2, 3, 1},
                {1, 2, 3, 0}*/

                {3,2,2,3,1,2},
                {2,1,3,2,3,1},
                {3,4,3,1,3,1},
                {3,2,1,2,4,3},
                {1,3,3,2,4,3}
        };
        int[] n = carve_seam(disruption);
        System.out.println("N = "+Arrays.toString(n));

    }

}
