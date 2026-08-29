import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }
        
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] result = new int[n];
        int i = 0;
        
        while (i < n) {
            int j = i + 1;
            while (j < n && pairs[j][0] - pairs[j - 1][0] <= limit) {
                j++;
            }
            
            List<Integer> indices = new ArrayList<>();
            for (int k = i; k < j; k++) {
                indices.add(pairs[k][1]);
            }
            Collections.sort(indices);
            
            for (int k = 0; k < indices.size(); k++) {
                result[indices.get(k)] = pairs[i + k][0];
            }
            
            i = j;
        }
        
        return result;
    }
}