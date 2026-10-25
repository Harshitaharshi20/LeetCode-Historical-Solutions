class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        markParents(root, null, parentMap);
        
        Map<TreeNode, Boolean> visited = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        
        queue.offer(target);
        visited.put(target, true);
        
        int currentDistance = 0;
        while (!queue.isEmpty()) {
            if (currentDistance == k) {
                break;
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                
                // Check left child
                if (current.left != null && !visited.containsKey(current.left)) {
                    queue.offer(current.left);
                    visited.put(current.left, true);
                }
                
                // Check right child
                if (current.right != null && !visited.containsKey(current.right)) {
                    queue.offer(current.right);
                    visited.put(current.right, true);
                }
                
                // Check parent
                TreeNode parent = parentMap.get(current);
                if (parent != null && !visited.containsKey(parent)) {
                    queue.offer(parent);
                    visited.put(parent, true);
                }
            }
            currentDistance++;
        }
        
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll().val);
        }
        return result;
    }
    
    private void markParents(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> parentMap) {
        if (node == null) return;
        if (parent != null) {
            parentMap.put(node, parent);
        }
        markParents(node.left, node, parentMap);
        markParents(node.right, node, parentMap);
    }
}