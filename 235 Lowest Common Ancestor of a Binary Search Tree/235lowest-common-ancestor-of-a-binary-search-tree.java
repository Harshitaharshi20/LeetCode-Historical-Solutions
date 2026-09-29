class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            // If both p and q are less than root, LCA must be in the left subtree
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } 
            // If both p and q are greater than root, LCA must be in the right subtree
            else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } 
            // If they split (one is less, one is greater) or one matches the root, we found the LCA
            else {
                return root;
            }
        }
        return null;
    }
}