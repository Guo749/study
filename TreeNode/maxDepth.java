/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 二叉树的最大深度
 */
public class maxDepth {

    //time:O(n)
    //space:O(K),k=height。recursion space depends on stack space,and stack space depends on trees height
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left)+1,maxDepth(root.right)+1);
    }
}
