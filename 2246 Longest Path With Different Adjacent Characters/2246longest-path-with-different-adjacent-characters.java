import java.util.*;

class Solution {
    int ans = 0;

    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }
        
        dfs(0, s, children);
        return ans;
    }
    
    private int dfs(int u, String s, List<Integer>[] children) {
        int max1 = 0;
        int max2 = 0; 
        
        for (int v : children[u]) {
            int len = dfs(v, s, children);
            
            if (s.charAt(v) != s.charAt(u)) {
                if (len > max1) {
                    max2 = max1;
                    max1 = len;
                } else if (len > max2) {
                    max2 = len;
                }
            }
        }
        
        ans = Math.max(ans, 1 + max1 + max2);
        return 1 + max1;
    }
}