/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 二叉树的最近公共祖先2-递归版
 */
public class lowestCommonAncestor {

    //time: O(n)
    //space: O(n)
    //step1: if p and q in the current roots left and right node,return root
    //step2: if p or q equals root or root is null,return root
    //step3: use the postorder because we need to get the root by subtrees
    //step4: we can find the left or right node first;
    //       if nodes contains root,the ancestor must be the root,return root;
    //       if left is null means we can not find the p or q from the left node ,return right;
    //       if right is null means we can not find the p or q from the right node,return left;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        if(root == null) return root;
        for (TreeNode node:nodes){
            if (node==root){
                return root;
            }
        }
        TreeNode left = lowestCommonAncestor(root.left,nodes);
        TreeNode right = lowestCommonAncestor(root.right,nodes);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }
}
