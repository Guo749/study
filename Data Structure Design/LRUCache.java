/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description LRUCache
 */
public class LRUCache {
    
    HashMap<Integer, Node> cacheMap;
    DoubleLinkedList cacheList;
    int capacity;
    public LRUCache(int capacity){
        cacheMap = new HashMap<>();
        cacheList = new DoubleLinkedList();
        this.capacity = capacity;
    }

    public int get(int key){
        if(!cacheMap.containsKey(key)){
            return -1;
        }

        int val = cacheMap.get(key).val;
        put(key, val);
        return val;
    }

    public void put(int key, int val){
        Node curr = new Node(key, val);
        if(cacheMap.containsKey(key)){
            cacheList.removeNode(cacheMap.get(key));
        }else{
            if(cacheMap.size() == capacity){
                Node last = cacheList.removeLast();
                cacheMap.remove(last.key);
            }
        }
        cacheMap.put(key, curr);
        cacheList.addFirst(curr);
    }
}

class DoubleLinkedList{
    Node head;
    Node tail;

    public DoubleLinkedList(){
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(Node node){
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public Node removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
        return node;
    }

    public Node removeLast(){
        if(head.next == tail){
            return head;
        }
        return removeNode(tail.prev);
    }
}

class Node{
    public int key;
    public int val;
    public Node prev;
    public Node next;

    public Node(int key, int val){
        this.key = key;
        this.val = val;
    }
}
