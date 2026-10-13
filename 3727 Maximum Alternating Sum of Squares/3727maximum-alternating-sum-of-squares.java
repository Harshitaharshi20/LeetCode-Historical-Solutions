
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];

        // Calculate the square of each element
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        // Sort the squares in ascending order
        Arrays.sort(squares);

        long sum = 0;

        // Add the largest ceil(n / 2) squares
        for (int i = n / 2; i < n; i++) {
            sum += squares[i];
        }

        // Subtract the smallest floor(n / 2) squares
        for (int i = 0; i < n / 2; i++) {
            sum -= squares[i];
        }

        return sum;
    }
}
