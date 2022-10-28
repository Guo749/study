/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 填充每个节点的下一个右侧节点指针2-递归版
 */
public class connect {

    //time:O(n)
    //space:O(1)
    //step1: use two pointers,one points to current level,the other points to next level 
    //step2: one pointers move to the same levels next node,the other moves to next levels left
    public Node connect(Node root) {
        if(root == null) return root;
        //define the current node,points to root;
        Node curr = root;
        while(curr != null){
            Node pre = new Node(-1),p = pre;
            while(curr != null){
                if(curr.left != null){
                    p.next = curr.left;
                    p = p.next;
                }
                if(curr.right != null){
                    p.next = curr.right;
                    p = p.next;
                }
                curr = curr.next;
            }
            curr = pre.next;
        }
        return root;
    }
}
