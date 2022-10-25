/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 二叉树的中序遍历-迭代版
 */
public class inorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.removeFirst();
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }
}
