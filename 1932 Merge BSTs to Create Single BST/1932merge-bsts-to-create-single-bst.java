/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode canMerge(List<TreeNode> trees) {
        Map<Integer, TreeNode> rootMap = new HashMap<>();
        Map<Integer, Integer> leafCounts = new HashMap<>();
        
        for (TreeNode tree : trees) {
            rootMap.put(tree.val, tree);
            if (tree.left != null) {
                leafCounts.put(tree.left.val, leafCounts.getOrDefault(tree.left.val, 0) + 1);
            }
            if (tree.right != null) {
                leafCounts.put(tree.right.val, leafCounts.getOrDefault(tree.right.val, 0) + 1);
            }
        }
        
        TreeNode mainRoot = null;
        for (TreeNode tree : trees) {
            if (!leafCounts.containsKey(tree.val)) {
                if (mainRoot != null) return null; 
                mainRoot = tree;
            }
        }
        
        if (mainRoot == null) return null;
        
        rootMap.remove(mainRoot.val);
        
        if (!isValidAndMerge(mainRoot, rootMap, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            return null;
        }
        
        if (!rootMap.isEmpty()) {
            return null;
        }
        
        return mainRoot;
    }
    
    private boolean isValidAndMerge(TreeNode node, Map<Integer, TreeNode> rootMap, int min, int max) {
        if (node == null) return true;
        
        if (node.val <= min || node.val >= max) {
            return false;
        }
        
        if (node.left == null && node.right == null) {
            if (rootMap.containsKey(node.val)) {
                TreeNode nextTree = rootMap.get(node.val);
                rootMap.remove(node.val);
                
                
                node.left = nextTree.left;
                node.right = nextTree.right;
            }
        }
        
        return isValidAndMerge(node.left, rootMap, min, node.val) && 
               isValidAndMerge(node.right, rootMap, node.val, max);
    }
}