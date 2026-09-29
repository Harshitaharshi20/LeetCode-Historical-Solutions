class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // If both nodes are null, the trees are the same at this position
        if (p == null && q == null) {
            return true;
        }
        
        // If only one of the nodes is null, the trees are structurally different
        if (p == null || q == null) {
            return false;
        }
        
        // If the values of the current nodes are different, the trees are not the same
        if (p.val != q.val) {
            return false;
        }
        
        // Recursively check if both the left subtrees and right subtrees are the same
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}