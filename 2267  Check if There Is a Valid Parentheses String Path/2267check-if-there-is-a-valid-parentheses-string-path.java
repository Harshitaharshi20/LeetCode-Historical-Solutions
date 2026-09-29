class Solution {
    int m, n;
    Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        
        // A valid path must have an even length to balance pairs
        if ((m + n - 1) % 2 != 0) return false;
        
        // A valid path must start with '(' and end with ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;

        // The maximum possible balance is half the path length
        int maxBal = (m + n) / 2;
        memo = new Boolean[m][n][maxBal + 1];

        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int r, int c, int balance) {
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        // Invalid if there are more closing brackets, or if the current balance 
        // exceeds the remaining number of steps to close them.
        if (balance < 0 || balance > (m - r + n - c - 1)) {
            return false;
        }

        // Reached destination
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        // Return cached result if already computed
        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }

        boolean res = false;
        
        // Try moving down
        if (r + 1 < m) {
            res |= dfs(grid, r + 1, c, balance);
        }
        
        // Try moving right
        if (!res && c + 1 < n) {
            res |= dfs(grid, r, c + 1, balance);
        }

        return memo[r][c][balance] = res;
    }
}