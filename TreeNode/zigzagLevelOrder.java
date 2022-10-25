/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 二叉树的锯齿形层序遍历
 */
public class zigzagLevelOrder {

    //time:O(n)
    //space:O(n)
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        boolean reverse = true;
        //use the LinkedList to put the node into first position or last
        LinkedList<Integer> res;
        while(!deque.isEmpty()){
            int size = deque.size();           
            res = new LinkedList<>();
            for(int i = 0;i < size;i++){
                TreeNode head = deque.poll();
                if(reverse){
                    res.addLast(head.val);
                }else{
                    res.addFirst(head.val);
                }
                if(head.left != null) deque.offer(head.left);
                if(head.right != null) deque.offer(head.right);
            }
            ans.add(res);
            reverse = !reverse;
        }
        return ans;
    }
}
