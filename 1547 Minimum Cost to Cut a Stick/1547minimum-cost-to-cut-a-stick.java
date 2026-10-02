import java.util.Arrays;

class Solution {
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        int[] newCuts = new int[m + 2];
        for (int i = 0; i < m; i++) {
            newCuts[i] = cuts[i];
        }
        newCuts[m] = 0;
        newCuts[m + 1] = n;
        
        Arrays.sort(newCuts);
        
        int len = newCuts.length;
        int[][] dp = new int[len][len];
        
        for (int length = 2; length < len; length++) {
            for (int i = 0; i < len - length; i++) {
                int j = i + length;
                dp[i][j] = Integer.MAX_VALUE;
                
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + newCuts[j] - newCuts[i]);
                }
            }
        }
        
        return dp[0][len - 1];
    }
}