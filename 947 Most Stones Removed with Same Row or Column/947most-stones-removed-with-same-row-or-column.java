class Solution {
    public int removeStones(int[][] stones) {
        int n = stones.length;
        boolean[] visited = new boolean[n];
        int connectedComponents = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                connectedComponents++;
                dfs(stones, visited, i);
            }
        }
        
        return n - connectedComponents;
    }
    
    private void dfs(int[][] stones, boolean[] visited, int currentIndex) {
        visited[currentIndex] = true;
        
        for (int i = 0; i < stones.length; i++) {
            if (!visited[i]) {
                if (stones[i][0] == stones[currentIndex][0] || stones[i][1] == stones[currentIndex][1]) {
                    dfs(stones, visited, i);
                }
            }
        }
    }
}