class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0;
        for (int pile : piles) {
            high = Math.max(high, pile);
        }
        
        int ans = high;
        
        while (low <= high) {
            int k = low + (high - low) / 2;
            
            if (canEatInTime(piles, h, k)) {
                ans = k;          
                high = k - 1;
            } else {
                low = k + 1;      
            }
        }
        
        return ans;
    }
    
    private boolean canEatInTime(int[] piles, int h, int k) {
        long totalHours = 0;
        for (int pile : piles) {
            totalHours += (pile + k - 1) / k;
        }
        return totalHours <= h;
    }
}