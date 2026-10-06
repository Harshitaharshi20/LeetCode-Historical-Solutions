class Solution {
    public int minAddToMakeValid(String s) {
        int leftUnmatched = 0; 
        int rightUnmatched = 0; 

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftUnmatched++;
            } else {
                if (leftUnmatched > 0) {
                    leftUnmatched--; 
                } else {
                    rightUnmatched++; 
                }
            }
        }

        return leftUnmatched + rightUnmatched;
    }
}