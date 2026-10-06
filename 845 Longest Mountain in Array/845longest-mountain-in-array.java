class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int maxLength = 0;
        int i = 1;

        while (i < n - 1) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                int left = i;
                int right = i;

                // Expand left
                while (left > 0 && arr[left] > arr[left - 1]) {
                    left--;
                }

                // Expand right
                while (right < n - 1 && arr[right] > arr[right + 1]) {
                    right++;
                }

                // Update max length
                maxLength = Math.max(maxLength, right - left + 1);
                i = right;
            } else {
                i++;
            }
        }

        return maxLength;
    }
}