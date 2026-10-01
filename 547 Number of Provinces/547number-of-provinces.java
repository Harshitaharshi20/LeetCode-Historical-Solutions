class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                provinces++;
                dfs(isConnected, visited, i, n);
            }
        }
        
        return provinces;
    }
    
    private void dfs(int[][] isConnected, boolean[] visited, int u, int n) {
        visited[u] = true;
        for (int v = 0; v < n; v++) {
            if (isConnected[u][v] == 1 && !visited[v]) {
                dfs(isConnected, visited, v, n);
            }
        }
    }
}