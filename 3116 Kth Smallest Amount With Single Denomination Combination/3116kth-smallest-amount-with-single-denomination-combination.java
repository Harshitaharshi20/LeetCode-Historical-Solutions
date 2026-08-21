class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int maxMask = 1 << n;
        long[] lcmArray = new long[maxMask];
        int[] setBits = new int[maxMask];
        lcmArray[0] = 1;
        for (int i = 1; i < maxMask; i++) {
            int lowestBit = Integer.lowestOneBit(i);
            int bitIndex = Integer.numberOfTrailingZeros(lowestBit);
            int prevMask = i ^ lowestBit;
            
            lcmArray[i] = lcm(lcmArray[prevMask], coins[bitIndex]);
            setBits[i] = setBits[prevMask] + 1;
        }
        
        long minCoin = coins[0];
        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }
        
        long left = 1;
        long right = minCoin * k; 
        long ans = right;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            
            if (count(mid, lcmArray, setBits, maxMask) >= k) {
                ans = mid;
                right = mid - 1; 
            } else {
                left = mid + 1;
            }
        }
        
        return ans;
    }
    
    
    private long count(long maxAmount, long[] lcmArray, int[] setBits, int maxMask) {
        long total = 0;
        for (int i = 1; i < maxMask; i++) {
            if (setBits[i] % 2 == 1) {
                total += maxAmount / lcmArray[i];
            } else {
                total -= maxAmount / lcmArray[i];
            }
        }
        return total;
    }
    
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}