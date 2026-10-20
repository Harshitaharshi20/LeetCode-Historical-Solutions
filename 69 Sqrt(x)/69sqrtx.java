class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        
        long low = 1;
        long high = x;
        long ans = 0;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid < x) {
                ans = mid;          // Store the valid floor value
                low = mid + 1;      // Try to find a larger integer root
            } else {
                high = mid - 1;     // Too large, search lower half
            }
        }
        
        return (int) ans;
    }
}