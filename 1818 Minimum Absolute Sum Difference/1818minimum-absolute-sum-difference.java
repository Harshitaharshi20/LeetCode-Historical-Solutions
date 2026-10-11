import java.util.Arrays;

class Solution {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int mod = 1_000_000_007;
        int n = nums1.length;
        
        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum = (totalSum + Math.abs(nums1[i] - nums2[i])) % mod;
        }
        
        int[] sortedNums1 = nums1.clone();
        Arrays.sort(sortedNums1);
        
        long maxReduction = 0;
        
        for (int i = 0; i < n; i++) {
            int currentDiff = Math.abs(nums1[i] - nums2[i]);
            int idx = Arrays.binarySearch(sortedNums1, nums2[i]);
            
            if (idx >= 0) {
                maxReduction = Math.max(maxReduction, currentDiff);
            } else {
                idx = -idx - 1; 
                if (idx < n) {
                    int diff1 = Math.abs(sortedNums1[idx] - nums2[i]);
                    maxReduction = Math.max(maxReduction, currentDiff - diff1);
                }
                if (idx > 0) {
                    int diff2 = Math.abs(sortedNums1[idx - 1] - nums2[i]);
                    maxReduction = Math.max(maxReduction, currentDiff - diff2);
                }
            }
        }
        
        long result = (totalSum - maxReduction + mod) % mod;
        return (int) result;
    }
}