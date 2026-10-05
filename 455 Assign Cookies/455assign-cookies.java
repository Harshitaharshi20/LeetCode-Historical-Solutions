import java.util.Arrays;

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        
        int i = 0; // Pointer for children
        int j = 0; // Pointer for cookies
        
        while (i < g.length && j < s.length) {
            // If the cookie is big enough to satisfy the child's greed
            if (s[j] >= g[i]) {
                i++; // Move to the next child
            }
            j++; // Always move to the next cookie
        }
        
        return i; // i represents the number of content children
    }
}