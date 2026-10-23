class Solution {
    public void deleteNode(ListNode node) {
        // Copy the value of the next node to the current node
        node.val = node.next.val;
        
        // Bypass the next node
        node.next = node.next.next;
    }
}