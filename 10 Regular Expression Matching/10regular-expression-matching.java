class Solution {
    public boolean isMatch(String s, String p) {
        Integer[][] memo = new Integer[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p, memo);
    }
    
    private boolean dp(int i, int j, String s, String p, Integer[][] memo) {
        if (j == p.length()) {
            return i == s.length();
        }
        if (memo[i][j] != null) {
            return memo[i][j] == 1;
        }
        boolean firstMatch = (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));
        
        boolean ans;
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            ans = (dp(i, j + 2, s, p, memo)) || (firstMatch && dp(i + 1, j, s, p, memo));
        } else {
            ans = firstMatch && dp(i + 1, j + 1, s, p, memo);
        }
        
        memo[i][j] = ans ? 1 : 0;
        return ans;
    }
}