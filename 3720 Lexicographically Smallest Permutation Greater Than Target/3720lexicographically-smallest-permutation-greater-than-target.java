class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int matchLen = 0;
        while (matchLen < n && freq[target.charAt(matchLen) - 'a'] > 0) {
            freq[target.charAt(matchLen) - 'a']--;
            matchLen++;
        }

        int start = Math.min(n - 1, matchLen);

        for (int i = start; i >= 0; i--) {
            if (i < matchLen) {
                freq[target.charAt(i) - 'a']++;
            }

            char tChar = target.charAt(i);
            
            for (int c = tChar - 'a' + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    freq[c]--; 
                    
                    StringBuilder sb = new StringBuilder();
                    sb.append(target.substring(0, i)); 
                    sb.append((char) (c + 'a'));       
                    for (int k = 0; k < 26; k++) {
                        while (freq[k] > 0) {
                            sb.append((char) (k + 'a'));
                            freq[k]--;
                        }
                    }
                    return sb.toString();
                }
            }
        }

        return "";
    }
}