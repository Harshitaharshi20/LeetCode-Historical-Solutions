class Solution {
    public int superEggDrop(int k, int n) {
        int[] dp = new int[k + 1];
        int m = 0;
        while (dp[k] < n) {
            m++;
            for (int eggs = k; eggs > 0; eggs--) {
                dp[eggs] = dp[eggs] + dp[eggs - 1] + 1;
            }
        }
        
        return m;
    }
}