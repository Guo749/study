/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 二叉树的最近公共祖先3-递归版
 */
public class lowestCommonAncestor {

    //time: O(n)
    //space: O(n)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(contains(root,p) && contains(root,q)) return LCA(root,p,q);
        return null;
    }

    public TreeNode LCA(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || root == p || root == q) return root;
        TreeNode left = LCA(root.left,p,q);
        TreeNode right = LCA(root.right,p,q);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }

    public boolean contains(TreeNode root,TreeNode subNode){
        if(root == null) return false;
        if(root == subNode) return true;
        return contains(root.left,subNode) || contains(root.right,subNode);
    }
}
