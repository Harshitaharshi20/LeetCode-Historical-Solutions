import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        
        boolean[] cols = new boolean[n];
        boolean[] posDiag = new boolean[2 * n]; 
        boolean[] negDiag = new boolean[2 * n]; 
        
        backtrack(0, n, board, res, cols, posDiag, negDiag);
        return res;
    }

    private void backtrack(int r, int n, char[][] board, List<List<String>> res, 
                           boolean[] cols, boolean[] posDiag, boolean[] negDiag) {
        if (r == n) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(new String(board[i]));
            }
            res.add(list);
            return;
        }

        for (int c = 0; c < n; c++) {
            if (cols[c] || posDiag[r + c] || negDiag[r - c + n]) {
                continue;
            }

            board[r][c] = 'Q';
            cols[c] = true;
            posDiag[r + c] = true;
            negDiag[r - c + n] = true;

            backtrack(r + 1, n, board, res, cols, posDiag, negDiag);

            board[r][c] = '.';
            cols[c] = false;
            posDiag[r + c] = false;
            negDiag[r - c + n] = false;
        }
    }
}