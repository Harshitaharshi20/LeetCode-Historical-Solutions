class Solution {
    public int minimizedMaximum(int n, int[] quantities) {
        int left = 1;
        int right = 0;
        
        for (int q : quantities) {
            right = Math.max(right, q);
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canDistribute(mid, quantities, n)) {
                right = mid; 
            } else {
                left = mid + 1; 
            }
        }
        
        return left;
    }
    
    private boolean canDistribute(int x, int[] quantities, int n) {
        int storesNeeded = 0;
        for (int q : quantities) {
            storesNeeded += (q + x - 1) / x; 
        }
        return storesNeeded <= n;
    }
}