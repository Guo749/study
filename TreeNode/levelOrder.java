/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 二叉树的层序遍历-迭代版
 */
public class levelOrder {

    //time:O(n)
    //space:O(n)
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }
        List<Integer> nums; 
        //define a queue to store the TreeNode
        Deque<TreeNode> queue = new ArrayDeque<>();
        //enQueue the root node and start the iterate
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            //poll from the queue and add the val to the ArrayList
            nums = new ArrayList<>();
            //for loop and let the queue dequeue,add val to nums
            //enQueue the left and right node to next while iterate
            for(int i = 0;i < size;i++){
                TreeNode head = queue.poll();
                nums.add(head.val);
                if(head.left != null) queue.offer(head.left);
                if(head.right != null) queue.offer(head.right);
            }                
            ans.add(nums);   
        }

        return ans;
    }
}
