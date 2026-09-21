class Solution {
    private int count = 0;
    private int result = -1;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return result;
    }

    private void inorder(TreeNode node, int k) {
        if (node == null) return;
        
        // Traverse left
        inorder(node.left, k);
        
        // Process node
        count++;
        if (count == k) {
            result = node.val;
            return; // Stop early
        }
        
        // Traverse right
        inorder(node.right, k);
    }
}