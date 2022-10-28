/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 二叉树的最长同值路径
 */
public class longestUnivaluePath {

    //time:O(n)
    //space:O(n)
    //step1: get the left and right nodes maxDepth
    //step2: compare the left and right nodes value to parent node,
    //step3: if their value are equals, sum them ,add to the root path(add 1)
    //step4: if their value are not equals, return to the prev recursion
    int diameter = 0;
    public int longestUnivaluePath(TreeNode root) {
        if(root == null) return 0;
        maxDepth(root,root.val);
        return diameter;
    }

    public int maxDepth(TreeNode root,int val){
        if(root == null) return 0;
        int left = maxDepth(root.left,root.val);
        int right = maxDepth(root.right,root.val);
        int sum = left + right;
        diameter = Math.max(diameter,sum);
        if(root.val != val) return 0;
        return Math.max(left,right) + 1;
    }
}
