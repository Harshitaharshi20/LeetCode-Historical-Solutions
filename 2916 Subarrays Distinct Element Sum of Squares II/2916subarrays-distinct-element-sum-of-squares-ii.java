class Solution {
    static final int MOD = 1_000_000_007;
    
    long[] sumSq;
    long[] sumC;
    long[] lazy;
    int size;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        size = n;
        
        sumSq = new long[4 * n];
        sumC = new long[4 * n];
        lazy = new long[4 * n];
        
        // lastSeen map to store the previous occurrence index of each number
        java.util.HashMap<Integer, Integer> lastSeen = new java.util.HashMap<>();
        
        long totalSumSq = 0;
        long currentSumC = 0;
        
        for (int j = 0; j < n; j++) {
            int val = nums[j];
            int p = lastSeen.getOrDefault(val, -1);
            
            // Range update: add 1 to distinct counts for starting indices from p + 1 to j
            if (p + 1 <= j) {
                update(1, 0, n - 1, p + 1, j, 1);
            }
            lastSeen.put(val, j);
            
            // The root of the segment tree now holds the sum of squares of all subarrays ending at j
            totalSumSq = (totalSumSq + sumSq[1]) % MOD;
        }
        
        return (int) totalSumSq;
    }
    
    private void pushDown(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }
    
    private void apply(int node, int l, int r, long val) {
        long len = (r - l + 1);
        // (c + val)^2 = c^2 + 2 * val * c + val^2 * len
        sumSq[node] = (sumSq[node] + 2 * val * sumC[node] + val * val * len) % MOD;
        // c + val * len
        sumC[node] = (sumC[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }
    
    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        pushDown(node, l, r);
        int mid = (l + r) / 2;
        if (ql <= mid) {
            update(2 * node, l, mid, ql, qr, val);
        }
        if (qr > mid) {
            update(2 * node + 1, mid + 1, r, ql, qr, val);
        }
        sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
        sumC[node] = (sumC[2 * node] + sumC[2 * node + 1]) % MOD;
    }
}