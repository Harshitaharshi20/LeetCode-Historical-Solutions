class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] freq = new int[51]; 
        
        for (int num : nums) {
            freq[num]++;
        }
        
        if (k == n) {
            int max = -1;
            for (int num : nums) {
                max = Math.max(max, num);
            }
            return max;
        }
        
        if (k == 1) {
            int max = -1;
            for (int i = 0; i <= 50; i++) {
                if (freq[i] == 1) {
                    max = Math.max(max, i);
                }
            }
            return max;
        }
        
        int res = -1;
        if (freq[nums[0]] == 1) {
            res = Math.max(res, nums[0]);
        }
        if (freq[nums[n - 1]] == 1) {
            res = Math.max(res, nums[n - 1]);
        }
        
        return res;
    }
}