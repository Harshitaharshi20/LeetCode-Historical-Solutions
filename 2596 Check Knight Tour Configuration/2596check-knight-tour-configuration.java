class Solution {
    public boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) {
            return false;
        }
        
        int n = grid.length;
        int[][] positions = new int[n * n][2];
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int step = grid[r][c];
                positions[step][0] = r;
                positions[step][1] = c;
            }
        }
        for (int i = 1; i < n * n; i++) {
            int prevRow = positions[i - 1][0];
            int prevCol = positions[i - 1][1];
            int currRow = positions[i][0];
            int currCol = positions[i][1];
            
            int rowDiff = Math.abs(currRow - prevRow);
            int colDiff = Math.abs(currCol - prevCol);
        
            if (rowDiff * colDiff != 2) {
                return false;
            }
        }
        
        return true;
    }
}