import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private int timer = 0;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> bridges = new ArrayList<>();
        List<Integer>[] graph = new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> conn : connections) {
            int u = conn.get(0);
            int v = conn.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }

        int[] disc = new int[n]; 
        int[] low = new int[n];  
        Arrays.fill(disc, -1);   

        dfs(0, -1, disc, low, graph, bridges);

        return bridges;
    }

    private void dfs(int u, int parent, int[] disc, int[] low, List<Integer>[] graph, List<List<Integer>> bridges) {
        disc[u] = low[u] = timer++;
        
        for (int v : graph[u]) {
            if (v == parent) {
                continue;
            }
            
            if (disc[v] == -1) {
                dfs(v, u, disc, low, graph, bridges);
                low[u] = Math.min(low[u], low[v]);
                
                if (low[v] > disc[u]) {
                    bridges.add(Arrays.asList(u, v));
                }
            } else {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
}