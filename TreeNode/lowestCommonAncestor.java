/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 二叉树的最近公共祖先-递归版
 */
public class longestUnivaluePath {

    //time: O(n)
    //space: O(n)
    //step1: if p and q in the current roots left and right node,return root
    //step2: if p or q equals root or root is null,return root
    //step3: use the postorder because we need to get the root by subtrees
    //step4: we can find the left or right node first;
    //       if we find p or q from the left and right node and they are not null,return root;
    //       if left is null means we can not find the p or q from the left node ,return right;
    //       if right is null means we can not find the p or q from the right node,return left;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root == null || root == p || root == q) return root;
        //find the p and q from the left subtree
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        //find the p and q from the right subtree
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }
}
