import java.util.ArrayList;
import java.util.List;

class Solution {
    // TrieNode structure
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; 
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (board == null || board.length == 0 || words == null || words.length == 0) {
            return result;
        }

        // Step 1: Build the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }
            current.word = word; // Mark the end of the word
        }

        // Step 2: Backtracking from each cell on the board
        int rows = board.length;
        int cols = board[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(board, r, c, root, result);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int r, int c, TrieNode parent, List<String> result) {
        char letter = board[r][c];
        
        if (letter == '#' || parent.children[letter - 'a'] == null) {
            return;
        }

        TrieNode curr = parent.children[letter - 'a'];

        // Check if we found a valid word
        if (curr.word != null) {
            result.add(curr.word);
            curr.word = null; 
        }

        // Mark the current cell as visited
        board[r][c] = '#';

        // Explore all 4 possible directions: Up, Down, Left, Right
        int[] rowOffsets = {-1, 1, 0, 0};
        int[] colOffsets = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int newRow = r + rowOffsets[i];
            int newCol = c + colOffsets[i];

            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
                if (board[newRow][newCol] != '#') {
                    dfs(board, newRow, newCol, curr, result);
                }
            }
        }

        board[r][c] = letter;

        boolean isLeaf = true;
        for (TrieNode child : curr.children) {
            if (child != null) {
                isLeaf = false;
                break;
            }
        }
        if (isLeaf) {
            parent.children[letter - 'a'] = null;
        }
    }
}