class Solution {
    private int runningSum = 0;
    public TreeNode bstToGst(TreeNode root) {
        if (root != null) {
            bstToGst(root.right);
            runningSum += root.val;
            root.val = runningSum;
            bstToGst(root.left);
            }
            return root;        
            }      
    }
