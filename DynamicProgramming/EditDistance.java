import java.util.Arrays;

public class EditDistance {
    public static int editDistance(String word1, String word2) {

        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }



        for (int i = 0; i < len1; i++) {
            char c1 = word1.charAt(i);
            for (int j = 0; j < len2; j++) {
                char c2 = word2.charAt(j);

                if (c1 == c2) {

                    dp[i + 1][j + 1] = dp[i][j];

                }
                else {
                    int change = dp[i][j] + 1;
                    int add = dp[i][j + 1] + 1;
                    int remove = dp[i + 1][j] + 1;

                    int min = change > add ? add : change;
                    min = remove > min ? min : remove;
                    dp[i + 1][j + 1] = min;
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[len1][len2];
    }

    public static void main(String args[]){

        int n = editDistance("tgcatat","atccgat");
        System.out.println("n = "+n);

    }
}