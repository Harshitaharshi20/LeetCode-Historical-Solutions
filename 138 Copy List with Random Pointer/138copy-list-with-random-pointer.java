class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        Node curr = head;
        while (curr != null) {
            Node newNode = new Node(curr.val);
            newNode.next = curr.next;
            curr.next = newNode;
            curr = newNode.next;
        }
        
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
        
        curr = head;
        Node pseudoHead = new Node(0);
        Node copyCurr = pseudoHead;
        
        while (curr != null) {
            copyCurr.next = curr.next;
            copyCurr = copyCurr.next;
            
            curr.next = curr.next.next;
            curr = curr.next;
        }
        
        return pseudoHead.next;
    }
}