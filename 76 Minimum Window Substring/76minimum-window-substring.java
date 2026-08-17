class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }

        int left = 0;
        int right = 0;
        int minLeft = 0;
        int minLen = Integer.MAX_VALUE;
        int count = 0; 

        while (right < s.length()) {
            char rightChar = s.charAt(right);
            
            if (map[rightChar] > 0) {
                count++;
            }
            
            map[rightChar]--;
            right++;

            while (count == t.length()) {
                if (right - left < minLen) {
                    minLen = right - left;
                    minLeft = left;
                }

                char leftChar = s.charAt(left);
                map[leftChar]++;
                
                if (map[leftChar] > 0) {
                    count--;
                }
                left++; 
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }
}