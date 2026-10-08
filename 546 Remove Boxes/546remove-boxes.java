class Solution {
    private int[][][] dp;

    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        dp = new int[n][n][n];
        return calculate(boxes, 0, n - 1, 0);
    }

    private int calculate(int[] boxes, int l, int r, int k) {
        if (l > r) return 0;
        if (dp[l][r][k] != 0) return dp[l][r][k];
        int originalL = l;
        int originalK = k;
        while (l + 1 <= r && boxes[l + 1] == boxes[l]) {
            l++;
            k++;
        }
        int res = (k + 1) * (k + 1) + calculate(boxes, l + 1, r, 0);
        for (int i = l + 1; i <= r; i++) {
            if (boxes[i] == boxes[l]) {
                res = Math.max(res, calculate(boxes, l + 1, i - 1, 0) + calculate(boxes, i, r, k + 1));
            }
        }

        dp[originalL][r][originalK] = res;
        return res;
    }
}