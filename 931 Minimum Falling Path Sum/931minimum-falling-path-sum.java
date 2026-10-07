class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        
        // Start from the second to last row and move upwards
        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col < n; col++) {
                int down = matrix[row + 1][col];
                
                int leftDiagonal = (col > 0) ? matrix[row + 1][col - 1] : Integer.MAX_VALUE;
                int rightDiagonal = (col < n - 1) ? matrix[row + 1][col + 1] : Integer.MAX_VALUE;
                
                // Fixed: Update matrix[row][col] instead of matrix[row]
                matrix[row][col] += Math.min(down, Math.min(leftDiagonal, rightDiagonal));
            }
        }
        
        // The answer is the minimum value in the first row
        int minSum = Integer.MAX_VALUE;
        for (int col = 0; col < n; col++) {
            minSum = Math.min(minSum, matrix[0][col]);
        }
        
        return minSum;
    }
}