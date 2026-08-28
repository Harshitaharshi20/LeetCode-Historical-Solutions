class Solution {
    char[] res;
    int[] count;
    String target;
    int n;
    char midChar;

    public String lexPalindromicPermutation(String s, String target) {
        this.n = s.length();
        this.target = target;
        this.count = new int[26];
        this.res = new char[n];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        int oddCount = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char) (i + 'a');
            }
        }

        if (oddCount > 1) {
            return "";
        }

        if (dfs(0, false)) {
            return new String(res);
        }
        
        return "";
    }

    private boolean dfs(int i, boolean isGreater) {
        if (i == n / 2) {
            if (n % 2 != 0) {
                res[i] = midChar;
            }
            
            for (int j = 0; j < n / 2; j++) {
                res[n - 1 - j] = res[j];
            }
            
            String current = new String(res);
            return current.compareTo(target) > 0;
        }

        for (int c = 0; c < 26; c++) {
            if (count[c] >= 2) {
                char ch = (char) (c + 'a');
                
                if (!isGreater && ch < target.charAt(i)) {
                    continue;
                }

                res[i] = ch;
                count[c] -= 2;
                
                if (dfs(i + 1, isGreater || ch > target.charAt(i))) {
                    return true;
                }
                
                count[c] += 2;
            }
        }
        
        return false;
    }
}