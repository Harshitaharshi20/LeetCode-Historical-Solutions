class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) return false;
        int target = sum / k;
        java.util.Arrays.sort(nums);
        int n = nums.length;
        if (nums[n - 1] > target) return false;
        
        Boolean[] memo = new Boolean[1 << n];
        return dfs(nums, (1 << n) - 1, 0, target, memo);
    }
    
    private boolean dfs(int[] nums, int mask, int currentSum, int target, Boolean[] memo) {
        if (mask == 0) return true;
        if (memo[mask] != null) return memo[mask];
        
        for (int i = 0; i < nums.length; i++) {
            if ((mask & (1 << i)) != 0) {
                if (currentSum + nums[i] > target) {
                    break; 
                }
                
                int nextMask = mask ^ (1 << i);
                int nextSum = (currentSum + nums[i]) % target;
                
                if (dfs(nums, nextMask, nextSum, target, memo)) {
                    return memo[mask] = true;
                }
            }
        }
        
        return memo[mask] = false;
    }
}