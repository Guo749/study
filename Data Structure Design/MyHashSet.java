/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description MyHashSet
 */
public class MyHashSet {

    Node[] nodes;
    public MyHashSet(int capacity) {
        nodes = new Node[capacity];
    }
    
    public void add(int key) {
        int index = getIndex(key);
        Node curr = nodes[index], dummy = curr;
        //if the node exists in nodes,
        if(curr != null){
            Node prev = null;
            while(dummy != null){
                if(dummy.val == key){
                    return;
                }
                prev = dummy;
                dummy = dummy.next;
            }
            dummy = prev;
        }
        Node node = new Node(key);
        if(dummy == null){
            nodes[index] = node;
        }else{
            dummy.next = node;
        }
    }
    
    public void remove(int key) {
        int index = getIndex(key);
        Node curr = nodes[index];
        if(curr != null){
            Node dummy = new Node(-1);
            dummy.next = curr;
            Node prev = dummy;
            while(prev.next != null){
                if(prev.next.val == key){
                    prev.next = prev.next.next;
                }else{
                    prev = prev.next;
                }
            }
            //update the node in nodes
            nodes[index] = dummy.next;
        }
    }
    
    public boolean contains(int key) {
        //step1: if the nodes array contains node, return true
        //step2: if the node on the nodes[i]s linked, return true
        //       else return false
        int index = getIndex(key);
        Node curr = nodes[index];
        if(curr != null){
            //step1
            if(curr.val == key) return true;
            Node dummy = curr;
            while(dummy != null){
                if(dummy.val == key){
                    return true;
                }
                dummy = dummy.next;
            }
        }
        return false;
    }

    public int getIndex(int key){
        //get the keys hashCode
        int hash = Integer.hashCode(key);
        //get the final hash
        hash ^= hash >>> 16;
        //return the final index
        return hash % nodes.length;
    }

    //initialize the Node
    class Node{
        int val;
        Node next;

        public Node(int val){
            this.val = val;
        }
    }
}
