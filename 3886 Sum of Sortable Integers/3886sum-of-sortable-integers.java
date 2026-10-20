
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] prefixMax = new int[n];
        int[] suffixMin = new int[n];
        int[] prefixDrops = new int[n];

        prefixMax[0] = nums[0];

        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            prefixDrops[i] = prefixDrops[i - 1]
                    + (nums[i - 1] > nums[i] ? 1 : 0);
        }

        suffixMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        int answer = 0;

        // Check every divisor of n.
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            if (isSortable(nums, k, prefixMax,
                           suffixMin, prefixDrops)) {
                answer += k;
            }
        }

        return answer;
    }

    private boolean isSortable(
            int[] nums,
            int k,
            int[] prefixMax,
            int[] suffixMin,
            int[] prefixDrops) {

        int n = nums.length;

        // Check whether elements can be ordered across block boundaries.
        for (int i = k; i < n; i += k) {
            if (prefixMax[i - 1] > suffixMin[i]) {
                return false;
            }
        }

        // Check whether each block is a cyclic rotation
        // of a non-decreasing sequence.
        for (int start = 0; start < n; start += k) {
            int end = start + k - 1;

            int internalDrops =
                    prefixDrops[end] - prefixDrops[start];

            int circularDrop =
                    nums[end] > nums[start] ? 1 : 0;

            if (internalDrops + circularDrop > 1) {
                return false;
            }
        }

        return true;
    }
}
