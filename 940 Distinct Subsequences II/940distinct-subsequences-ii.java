class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        
        long[] endsWith = new long[26];
        long total = 0; 
        
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            
            long added = (total + 1 - endsWith[idx]) % MOD;
            
            if (added < 0) {
                added += MOD;
            }
            
            total = (total + added) % MOD;
            endsWith[idx] = (endsWith[idx] + added) % MOD;
        }
        
        return (int) total;
    }
}