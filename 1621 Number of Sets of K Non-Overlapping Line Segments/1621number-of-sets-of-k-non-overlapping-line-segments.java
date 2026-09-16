class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        int N = n + k - 1;
        int K = 2 * k;
        
        if (K > N) return 0;
        
        long res = 1;
        for (int i = 1; i <= K; i++) {
            res = res * (N - i + 1) % MOD;
            
            res = res * power(i, MOD - 2, MOD) % MOD;
        }
        
        return (int) res;
    }
    
    private long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = res * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }
        return res;
    }
}