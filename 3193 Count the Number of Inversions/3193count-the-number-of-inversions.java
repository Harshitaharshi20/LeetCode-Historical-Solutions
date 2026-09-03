class Solution {
    public int numberOfPermutations(int n, int[][] requirements) {
        int MOD = 1_000_000_007;
        int MAX_INV = 400;
        int[] req = new int[n];
        Arrays.fill(req, -1);
        for (int[] r : requirements) {
            req[r[0]] = r[1];
        }
        
        if (req[0] > 0) return 0;
        
        int[][] dp = new int[n + 1][MAX_INV + 1];
        dp[1][0] = 1;
        
        for (int len = 2; len <= n; len++) {
            int endIdx = len - 1;
            long currentSum = 0;
            
            for (int inv = 0; inv <= MAX_INV; inv++) {
                currentSum = (currentSum + dp[len - 1][inv]) % MOD;
                
                if (inv >= len) {
                    currentSum = (currentSum - dp[len - 1][inv - len] + MOD) % MOD;
                }
                
                dp[len][inv] = (int) currentSum;
            }
            if (req[endIdx] != -1) {
                for (int inv = 0; inv <= MAX_INV; inv++) {
                    if (inv != req[endIdx]) {
                        dp[len][inv] = 0;
                    }
                }
            }
        }
        
        return dp[n][req[n - 1]];
    }
}