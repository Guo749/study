/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 从中序与后序遍历序列构造二叉树-迭代版
 */
public class buildTree {

    //time:O(n)
    //space:O(n)
    //step1: build the hashMap to store the inorders key and value;
    //step2: get the head index from inorder by the last elements in the postorders;
    //step3: put the right node by range [index + 1,length -1] and left node by range [0,index-1];
    //       because the postorders order is left->right->head,so the recursions order is head->right->left,so we need put the right node first
    //step4: recurse the process and build the tree
    int length;
    HashMap<Integer,Integer> inorderMap = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = inorder.length;
        if(len == 1) return new TreeNode(inorder[0]);
        for(int i = 0;i < len;i++){
            inorderMap.put(inorder[i],i);
        }
        length = len - 1;
        return buildTreeHelper(inorder,postorder,0,length);
    }
    public TreeNode buildTreeHelper(int[] inorder,int[] postorder,int inorderStart,int inorderEnd){
        if(inorderStart > inorderEnd){
            return null;
        }
        int headVal = postorder[length--];
        int index = inorderMap.get(headVal);
        TreeNode head = new TreeNode(headVal);
        head.right = buildTreeHelper(inorder,postorder,index + 1,inorderEnd);
        head.left = buildTreeHelper(inorder,postorder,inorderStart,index - 1);
        return head;
    }
}
