import java.util.*;

class Solution {
    public int minReorder(int n, int[][] connections) {
        
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] c : connections) {
            int u = c[0], v = c[1];
            adj[u].add(new int[]{v, 1}); 
            adj[v].add(new int[]{u, 0});
        }
        
        return dfs(0, -1, adj);
    }
    
    private int dfs(int curr, int parent, List<int[]>[] adj) {
        int changes = 0;
        for (int[] neighbor : adj[curr]) {
            int next = neighbor[0];
            int cost = neighbor[1];
            
            if (next != parent) {
                changes += cost + dfs(next, curr, adj);
            }
        }
        return changes;
    }
}