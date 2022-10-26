/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 判断是否为对称二叉树-迭代版
 */
public class isSymmetric {

    //time:O(n)
    //space:O(n)
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode next = queue.poll();
            if(node == null && next == null) continue;
            if(node == null || next == null || node.val != next.val) return false;
            queue.offer(node.left);
            queue.offer(next.right);
            queue.offer(node.right);
            queue.offer(next.left); 
        }
        return true;
    }
}
