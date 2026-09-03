/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        if (head == null) return null;
        
        Node curr = head;
        
        while (curr != null) {
            if (curr.child != null) {
                Node nextNode = curr.next;
                Node childNode = curr.child;
                
                Node childTail = childNode;
                while (childTail.next != null) {
                    childTail = childTail.next;
                }
                
                childTail.next = nextNode;
                if (nextNode != null) {
                    nextNode.prev = childTail;
                }
                
                curr.next = childNode;
                childNode.prev = curr;
                curr.child = null;
            }
            curr = curr.next;
        }
        
        return head;
    }
}