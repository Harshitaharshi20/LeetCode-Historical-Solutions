class Solution {
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, matchIdx = -1;

        while (sIdx < sLen) {
            if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                sIdx++;
                pIdx++;
            }
            else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                starIdx = pIdx;
                matchIdx = sIdx;
                pIdx++;
            }
            else if (starIdx != -1) {
                pIdx = starIdx + 1;
                matchIdx++;
                sIdx = matchIdx;
            }
            else {
                return false;
            }
        }

        for (int i = pIdx; i < pLen; i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }

        return true;
    }
}