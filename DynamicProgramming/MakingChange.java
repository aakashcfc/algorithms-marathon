import java.util.Arrays;

public class MakingChange {
    public static int minimumCoins(int money, int[] coins) {

        int dp[]=new int[money+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=1;i<=money;i++){
            for(int c: coins){
                if(i-c<0 || dp[i-c]==Integer.MAX_VALUE){
                    continue;
                }
                dp[i]=Math.min(dp[i],dp[i-c]+1);
            }
        }
        if(dp[money] != Integer.MAX_VALUE)
            return dp[money];
        else
            return -1;


    }

    public static void main(String args[]){

        int A[] = new int[]{4,3,6};
        int n =minimumCoins(10 , A);
        System.out.println("N = "+n);
    }
}