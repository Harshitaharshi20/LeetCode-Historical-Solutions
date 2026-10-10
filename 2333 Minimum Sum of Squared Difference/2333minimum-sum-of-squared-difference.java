import java.util.HashMap;
import java.util.Map;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long totalK = (long) k1 + k2;
        int n = nums1.length;
        int maxDiff = 0;
        int[] count = new int[100001];
        for (int i = 0; i < n; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            count[d]++;
            maxDiff = Math.max(maxDiff, d);
        }
        for (int d = maxDiff; d > 0; d--) {
            if (count[d] == 0) continue;
            long take = Math.min(totalK, count[d]);
            count[d] -= take;
            count[d - 1] += take;
            totalK -= take;
            
            if (totalK == 0) break;
        }
        long result = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (count[d] > 0) {
                result += (long) count[d] * d * d;
            }
        }
        
        return result;
    }
}