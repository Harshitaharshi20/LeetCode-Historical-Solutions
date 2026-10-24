import java.util.HashMap;
import java.util.Map;

class LFUCache {
    
    class Node {
        int key, value, freq;
        Node prev, next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1; 
        }
    }
    
    class DoublyLinkedList {
        int size;
        Node head, tail;
        
        DoublyLinkedList() {
            size = 0;
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }
        
        void addNode(Node node) {
            Node nextNode = head.next;
            head.next = node;
            node.prev = head;
            node.next = nextNode;
            nextNode.prev = node;
            size++;
        }
        
        void removeNode(Node node) {
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            size--;
        }
        
        Node removeTail() {
            if (size > 0) {
                Node node = tail.prev;
                removeNode(node);
                return node;
            }
            return null;
        }
    }
    
    private int capacity;
    private int minFreq;
    private Map<Integer, Node> keyNodeMap;
    private Map<Integer, DoublyLinkedList> freqListMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.keyNodeMap = new HashMap<>();
        this.freqListMap = new HashMap<>();
    }
    
    public int get(int key) {
        if (!keyNodeMap.containsKey(key)) {
            return -1;
        }
        Node node = keyNodeMap.get(key);
        updateFreq(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) return;
        if (keyNodeMap.containsKey(key)) {
            Node node = keyNodeMap.get(key);
            node.value = value;
            updateFreq(node);
            return;
        }
        
        if (keyNodeMap.size() >= capacity) {
            DoublyLinkedList minFreqList = freqListMap.get(minFreq);
            Node lruNode = minFreqList.removeTail();
            keyNodeMap.remove(lruNode.key);
        }
        
        Node newNode = new Node(key, value);
        minFreq = 1; 
        DoublyLinkedList newList = freqListMap.getOrDefault(minFreq, new DoublyLinkedList());
        newList.addNode(newNode);
        freqListMap.put(minFreq, newList);
        
        keyNodeMap.put(key, newNode);
    }
    
    private void updateFreq(Node node) {
        int freq = node.freq;
        DoublyLinkedList oldList = freqListMap.get(freq);
        oldList.removeNode(node);
        
        if (freq == minFreq && oldList.size == 0) {
            minFreq++;
        }
        
        node.freq++;
        DoublyLinkedList newList = freqListMap.getOrDefault(node.freq, new DoublyLinkedList());
        newList.addNode(node);
        freqListMap.put(node.freq, newList);
    }
}