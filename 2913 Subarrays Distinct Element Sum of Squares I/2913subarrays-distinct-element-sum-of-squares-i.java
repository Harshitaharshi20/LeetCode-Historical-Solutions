import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int totalSum = 0;
        int n = nums.size();
        
        // Generate all possible subarrays
        for (int i = 0; i < n; i++) {
            Set<Integer> distinctElements = new HashSet<>();
            for (int j = i; j < n; j++) {
                // Add elements of the current subarray
                distinctElements.add(nums.get(j));
                
                // Square the distinct count and add to the total sum
                int count = distinctElements.size();
                totalSum += count * count;
            }
        }
        
        return totalSum;
    }
}