import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCounts = new int[30];
        for (int num : nums) {
            for (int i = 0; i < 30; i++) {
                if ((num & (1 << i)) != 0) {
                    bitCounts[i]++;
                }
            }
        }
        
        long mod = 1_000_000_007;
        long totalSumOfSquares = 0;
        for (int step = 0; step < k; step++) {
            long currentVal = 0;
            for (int i = 0; i < 30; i++) {
                if (bitCounts[i] > 0) {
                    currentVal |= (1L << i);
                    bitCounts[i]--;
                }
            }
            totalSumOfSquares = (totalSumOfSquares + (currentVal * currentVal) % mod) % mod;
        }
        
        return (int) totalSumOfSquares;
    }
}