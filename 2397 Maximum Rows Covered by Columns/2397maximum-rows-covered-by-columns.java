class Solution {
    private int maxRowsCovered = 0;

    public int maximumRows(int[][] matrix, int numSelect) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] selectedCols = new boolean[n];
        
        backtrack(matrix, 0, numSelect, selectedCols);
        return maxRowsCovered;
    }

    private void backtrack(int[][] matrix, int colIndex, int numSelect, boolean[] selectedCols) {
        if (numSelect == 0) {
            maxRowsCovered = Math.max(maxRowsCovered, countCoveredRows(matrix, selectedCols));
            return;
        }
        
        int n = matrix[0].length;
        // If remaining columns are fewer than needed, prune the branch
        if (n - colIndex < numSelect) {
            return;
        }

        // Option 1: Choose the current column
        selectedCols[colIndex] = true;
        backtrack(matrix, colIndex + 1, numSelect - 1, selectedCols);

        // Option 2: Do not choose the current column
        selectedCols[colIndex] = false;
        backtrack(matrix, colIndex + 1, numSelect, selectedCols);
    }

    private int countCoveredRows(int[][] matrix, boolean[] selectedCols) {
        int coveredCount = 0;
        for (int[] row : matrix) {
            boolean isCovered = true;
            for (int j = 0; j < row.length; j++) {
                // If there is a 1 in a column that is not selected, the row is not covered
                if (row[j] == 1 && !selectedCols[j]) {
                    isCovered = false;
                    break;
                }
            }
            if (isCovered) {
                coveredCount++;
            }
        }
        return coveredCount;
    }
}