class Solution {
    Integer[][][] memo;
    int rows, cols;
    int[][] gridData;

    public int cherryPickup(int[][] grid) {
        this.gridData = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.memo = new Integer[rows][cols][cols];
        
        return dp(0, 0, cols - 1);
    }

    private int dp(int r, int c1, int c2) {
        if (c1 < 0 || c1 >= cols || c2 < 0 || c2 >= cols) {
            return 0;
        }
        if (memo[r][c1][c2] != null) {
            return memo[r][c1][c2];
        }
        int cherries = (c1 == c2) ? gridData[r][c1] : gridData[r][c1] + gridData[r][c2];
        if (r < rows - 1) {
            int maxFutureCherries = 0;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    maxFutureCherries = Math.max(maxFutureCherries, dp(r + 1, c1 + i, c2 + j));
                }
            }
            cherries += maxFutureCherries;
        }

        return memo[r][c1][c2] = cherries;
    }
}