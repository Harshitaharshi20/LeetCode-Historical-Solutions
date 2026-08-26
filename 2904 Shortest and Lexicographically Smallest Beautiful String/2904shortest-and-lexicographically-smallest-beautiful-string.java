class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        String ans = "";
        
        for (int i = 0; i < s.length(); i++) {
            int onesCount = 0;
            
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    onesCount++;
                }
                
                if (onesCount == k) {
                    String currentSub = s.substring(i, j + 1);
                    
                    if (ans.isEmpty()) {
                        ans = currentSub;
                    } else if (currentSub.length() < ans.length()) {
                        ans = currentSub;
                    } else if (currentSub.length() == ans.length() && currentSub.compareTo(ans) < 0) {
                        ans = currentSub;
                    }
                }
            }
        }
        
        return ans;
    }
}