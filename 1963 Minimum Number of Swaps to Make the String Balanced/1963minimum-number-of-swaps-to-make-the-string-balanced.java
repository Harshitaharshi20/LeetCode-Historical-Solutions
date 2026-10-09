class Solution {
    public int minSwaps(String s) {
        int mismatched = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '[') {
                mismatched++;
            } else {
                if (mismatched > 0) {
                    mismatched--;
                }
            }
        }
        return (mismatched + 1) / 2;
    }
}