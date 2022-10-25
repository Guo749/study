/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 二叉树的后序遍历-迭代版
 */
public class postorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        //存储上一个已经遍历过的节点
        TreeNode prev = null;
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.addFirst(root);
                root = root.left;
            }
            root = stack.getFirst();
            //右子树为空，直接弹出栈加到结果中即可
            if(root.right == null || root.right == prev){
                root = stack.removeFirst();
                ans.add(root.val);
                //节点已经遍历过，存起来
                prev = root;
                //这个节点需要置空，不然就无限循环了
                root = null;
            }else{
                root = root.right;
            }
        }
        return ans;
    }
}
