class Solution {

    class Node {
        int totalProd;
        int[] prefixCounts;
        
        Node(int k) {
            prefixCounts = new int[k];
        }
    }

    Node[] tree;

    private Node merge(Node left, Node right, int k) {
        if (left == null) return right;
        if (right == null) return left;
        
        Node res = new Node(k);
        res.totalProd = (left.totalProd * right.totalProd) % k;
        
        for (int i = 0; i < k; i++) {
            res.prefixCounts[i] += left.prefixCounts[i];
        }
        
        for (int i = 0; i < k; i++) {
            int newMod = (left.totalProd * i) % k;
            res.prefixCounts[newMod] += right.prefixCounts[i];
        }
        
        return res;
    }

    private void build(int node, int start, int end, int[] nums, int k) {
        tree[node] = new Node(k);
        if (start == end) {
            int val = nums[start] % k;
            tree[node].totalProd = val;
            tree[node].prefixCounts[val] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node + 1, start, mid, nums, k);
        build(2 * node + 2, mid + 1, end, nums, k);
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2], k);
    }

    // Point update for nums[idx] = val
    private void update(int node, int start, int end, int idx, int val, int k) {
        if (start == end) {
            int v = val % k;
            tree[node].totalProd = v;
            for (int i = 0; i < k; i++) {
                tree[node].prefixCounts[i] = 0;
            }
            tree[node].prefixCounts[v] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node + 1, start, mid, idx, val, k);
        } else {
            update(2 * node + 2, mid + 1, end, idx, val, k);
        }
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2], k);
    }

    // Range query to get prefix frequencies from start to n-1
    private Node query(int node, int start, int end, int l, int r, int k) {
        if (r < start || end < l) return null;
        if (l <= start && end <= r) return tree[node];
        
        int mid = start + (end - start) / 2;
        Node left = query(2 * node + 1, start, mid, l, r, k);
        Node right = query(2 * node + 2, mid + 1, end, l, r, k);
        
        return merge(left, right, k);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        tree = new Node[4 * n];
        
        build(0, 0, n - 1, nums, k);

        int m = queries.length;
        int[] result = new int[m];

        for (int i = 0; i < m; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(0, 0, n - 1, index, value, k);
            
            Node resNode = query(0, 0, n - 1, start, n - 1, k);
            
            result[i] = resNode == null ? 0 : resNode.prefixCounts[x];
        }

        return result;
    }
}