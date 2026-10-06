class Solution {
    public int lastStoneWeightII(int[] stones) {
        int totalSum = 0;
        for (int stone : stones) {
            totalSum += stone;
        }
        
        int target = totalSum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        
        int maxSubsetSum = 0;
        
        for (int stone : stones) {
            for (int j = target; j >= stone; j--) {
                dp[j] = dp[j] || dp[j - stone];
                if (dp[j]) {
                    maxSubsetSum = Math.max(maxSubsetSum, j);
                }
            }
        }
        
        return totalSum - 2 * maxSubsetSum;
    }
}