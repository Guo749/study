/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 根据前序和后序遍历构造二叉树-递归版
 */
public class constructFromPrePost {

    //time: O(n)
    //space: O(n)
    HashMap<Integer,Integer> postorderMap = new HashMap<>();
    int start = 0,length;
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        length = postorder.length;
        if(length == 1) return new TreeNode(preorder[0]);
        //define the hashMap to save the trees postorder value
        for(int i = 0;i < length;i++){
            postorderMap.put(postorder[i],i);
        }
        return buildTree(preorder,postorder,0,length-1);
    }

    public TreeNode buildTree(int[] preorder, int[] postorder,int postorderStart,int postorderEnd){
        if(postorderStart > postorderEnd || start >= length) return null;
        int headVal = preorder[start++];
        System.out.println("headVal:" + headVal);
        TreeNode head = new TreeNode(headVal);
        System.out.println("postorderStart:" + postorderStart);
        System.out.println("postorderEnd:" + postorderEnd);
        System.out.println("---------------------------");
        //if the start equals end,it means the value in the  postorder are null or one single number,return the head node
        if(postorderStart == postorderEnd) return head;
        int leftVal = preorder[start];
        int leftIndex = postorderMap.get(leftVal);
        //its different from "through postorder and inorder to Construct Binary Tree" and "through preorder and inorder to Construct Binary Tree"
        //in those situations,the inorder can index the root number,the left node on left side,the right node on the righr side
        //if the root index is i,the left node in [0,i-1],the right node in [i+1,length-1]
        //if we find the left nodes and right nodes in the postorder,if the root index is i,we fine the roots left node on i+1
        //then we through the left nodes position to find the left node and the right node on postorder
        //so the left nodes in [0,index],the right node in [index+1,length -1]
        head.left = buildTree(preorder,postorder, postorderStart , leftIndex);
        //the last element is root,so find the right node need postorderEnd - 1
        head.right = buildTree(preorder,postorder,leftIndex + 1,postorderEnd - 1);
        return head;
    }
}
