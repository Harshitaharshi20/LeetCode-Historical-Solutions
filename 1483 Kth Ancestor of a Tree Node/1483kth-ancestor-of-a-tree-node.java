class TreeAncestor {
    int[][] up;
    int maxPow;

    public TreeAncestor(int n, int[] parent) {
        maxPow = 17; 
        up = new int[n][maxPow];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < maxPow; j++) {
                up[i][j] = -1;
            }
        }
        
        for (int i = 0; i < n; i++) {
            up[i][0] = parent[i];
        }
        
        for (int j = 1; j < maxPow; j++) {
            for (int i = 0; i < n; i++) {
                if (up[i][j - 1] != -1) {
                    up[i][j] = up[up[i][j - 1]][j - 1];
                }
            }
        }
    }
    
    public int getKthAncestor(int node, int k) {
        for (int j = 0; j < maxPow; j++) {
            if ((k & (1 << j)) != 0) {
                node = up[node][j];
                if (node == -1) {
                    break;
                }
            }
        }
        return node;
    }
}