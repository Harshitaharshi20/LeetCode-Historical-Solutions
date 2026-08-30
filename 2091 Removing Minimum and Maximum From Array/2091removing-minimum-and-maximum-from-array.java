class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        int minIndex = 0;
        int maxIndex = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        int i = Math.min(minIndex, maxIndex);
        int j = Math.max(minIndex, maxIndex);

        int deleteFromFront = j + 1;
        int deleteFromBack = n - i;
        int deleteFromBoth = (i + 1) + (n - j);

        return Math.min(deleteFromFront, Math.min(deleteFromBack, deleteFromBoth));
    }
}