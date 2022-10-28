/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 二叉树的直径
 */
public class diameterOfBinaryTree {

    //time:O(n)
    //space:O(n)
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        //the max diameter means the max of the sum of left maxDepth and right maxDepth
        //calculate the trees maxDepth,through the postorderTraversal of binaryTree to sum the diameter
        maxDepth(root);
        return diameter;
    }

    public int maxDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        //split the trees maxDepth to leftNode and RightNode
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        int sum = left + right;
        //compare every recursions diameter,pick the max one
        diameter = Math.max(diameter,sum);
        return Math.max(left,right) + 1;
    }
}
