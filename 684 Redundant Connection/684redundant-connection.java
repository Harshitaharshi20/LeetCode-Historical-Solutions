class Solution {
    int[] parent;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];
        
        // Initialize each node as its own parent
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        
        // Process each edge
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            
            // If both nodes belong to the same set, this edge forms a cycle
            if (find(u) == find(v)) {
                return edge;
            }
            
            // Otherwise, join the two sets
            union(u, v);
        }
        
        return new int[0];
    }
    
    // Find the root parent of a node with path compression
    private int find(int node) {
        if (parent[node] == node) {
            return node;
        }
        return parent[node] = find(parent[node]); 
    }
    
    // Union two components
    private void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        
        if (rootU != rootV) {
            parent[rootU] = rootV;
        }
    }
}