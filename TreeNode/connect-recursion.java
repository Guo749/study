/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 填充每个节点的下一个右侧节点指针-递归版
 */
public class connect {

    //time:O(n)
    //space:O(1)
    //step1: use two pointers points to current levels left node 
    //step2: one pointers move to the same levels next node,the other moves to next levels left
    public Node connect(Node root) {
        if(root == null) return root;
        Node dummy = root;
        while(dummy != null){
            Node curr = dummy;
            while(curr != null){
                if(curr.left != null){
                    curr.left.next = curr.right;
                }
                //different subNode cannot find the next node directly,we can use current nodes.next.left find this node
                //then current nodes right.next points to this node
                if(curr.right != null){
                    curr.right.next = curr.next == null ? null : curr.next.left;
                }
                curr = curr.next;
            }
            dummy = dummy.left;
        }
        return root;
    }
}
