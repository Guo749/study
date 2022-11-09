/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description MyHashMap
 */
class MyHashMap {

    Node[] nodes;
    public MyHashMap() {
        nodes = new Node[1000009];
    }
    
    public void put(int key, int value) {
        int index = getIndex(key);
        Node curr = nodes[index], dummy = curr;
        if(curr != null){
            Node prev = null;
            while(dummy != null){
                if(dummy.key == key){
                    dummy.val = value;
                    return;
                }
                prev = dummy;
                dummy = dummy.next;
            }
            dummy = prev;
        }
        Node newNode = new Node(key, value);
        if(dummy == null){
            nodes[index] = newNode;
        } else{
            dummy.next = newNode;
        }
    }
    
    public int get(int key) {
        int index = getIndex(key);
        Node curr = nodes[index], dummy = curr;
        if(curr != null){
            while(dummy != null){
                if(dummy.key == key){
                    return dummy.val;
                }
                dummy = dummy.next;
            }
        }
        return -1;
    }
    
    public void remove(int key) {
        int index = getIndex(key);
        Node curr = nodes[index], dummy = curr;
        if(curr != null){
            if(curr.key == key){
                nodes[index] = null;
                return;
            }
            Node prev = new Node(-1,-1);
            prev.next = dummy;
            Node temp = prev;
            while(temp.next != null){
                if(temp.key == key){
                    temp.next = temp.next.next;
                } else{
                    temp = temp.next;
                }
            }
            nodes[index] = prev.next;
        }
    }

    public int getIndex(int key){
        int hash = Integer.hashCode(key);
        hash ^= hash >>> 16;
        return hash % nodes.length;
    }
}

class Node{
    public int key;
    public int val;
    public Node next;

    public Node(int key, int val){
        this.key = key;
        this.val = val;
    }
}
