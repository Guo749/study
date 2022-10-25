/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 在每个树行中找最大值
 */
public class largestValues {

    //time:O(n)
    //space:O(n)
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for(int i = 0;i < size;i++){
                TreeNode head = queue.poll();
                max = Math.max(max,head.val);
                if(head.left != null) queue.offer(head.left);
                if(head.right != null) queue.offer(head.right);
            }
            ans.add(max);
        }
        return ans;
    }
}
