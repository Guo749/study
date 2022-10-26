/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 二叉树的路径总和-递归版
 */
public class hasPathSum {

    //time:O(n)
    //space:O(n)
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        //if subTreeNode do not have any child node,it mast be the leefNode
        //compare the value to the left targetsSum
        if(root.left == null && root.right == null){
            return root.val == targetSum;
        }
        //use the target minus current nodes value,if recursive to the leefNode,compare them
        return hasPathSum(root.left,targetSum - root.val) || hasPathSum(root.right,targetSum - root.val);
    }
}
