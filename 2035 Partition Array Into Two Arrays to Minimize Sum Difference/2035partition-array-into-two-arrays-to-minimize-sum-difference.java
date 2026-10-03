import java.util.*;

class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length / 2;
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        List<List<Integer>> leftSums = new ArrayList<>();
        List<List<Integer>> rightSums = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) {
            leftSums.add(new ArrayList<>());
            rightSums.add(new ArrayList<>());
        }

        generateSums(nums, 0, n, 0, 0, leftSums);
        generateSums(nums, n, 2 * n, 0, 0, rightSums);

        for (int i = 0; i <= n; i++) {
            Collections.sort(rightSums.get(i));
        }

        int minDiff = Integer.MAX_VALUE;
        int target = totalSum / 2;

        for (int k = 0; k <= n; k++) {
            List<Integer> left = leftSums.get(k);
            List<Integer> right = rightSums.get(n - k);

            for (int leftSum : left) {
                int leftIdx = 0;
                int rightIdx = right.size() - 1;

                while (leftIdx <= rightIdx) {
                    int mid = leftIdx + (rightIdx - leftIdx) / 2;
                    int rightSum = right.get(mid);
                    
                    int currentSum = leftSum + rightSum;
                    int diff = Math.abs(totalSum - 2 * currentSum);
                    minDiff = Math.min(minDiff, diff);
                    
                    if (currentSum < target) {
                        leftIdx = mid + 1;
                    } else {
                        rightIdx = mid - 1;
                    }
                }
            }
        }

        return minDiff;
    }

    private void generateSums(int[] nums, int start, int end, int count, int currentSum, List<List<Integer>> sums) {
        if (start == end) {
            sums.get(count).add(currentSum);
            return;
        }
        
        generateSums(nums, start + 1, end, count + 1, currentSum + nums[start], sums);
        
        generateSums(nums, start + 1, end, count, currentSum, sums);
    }
}