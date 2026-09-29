class Solution {
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean validate(TreeNode node, long min, long max) {
        // An empty tree is a valid BST
        if (node == null) {
            return true;
        }
        
        
        if (node.val <= min || node.val >= max) {
            return false;
        }
        
        
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }
}