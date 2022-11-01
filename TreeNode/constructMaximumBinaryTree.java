/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 最大二叉树-递归版
 */
public class constructFromPrePost {

    //time: O(n2)
    //space: O(n)
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildBinaryTree(nums,0,nums.length - 1);
    }
    public TreeNode buildBinaryTree(int[] nums,int start,int end){
        if(start > end) return null;
        int max = start;
        for(int i = start + 1;i <= end;i++){
            if(nums[i] > nums[max]){
                max = i;
            }
        }
        TreeNode head = new TreeNode(nums[max]);
        if(start == end) return head;
        head.left = buildBinaryTree(nums,start,max -1);
        head.right = buildBinaryTree(nums,max + 1,end);
        return head;
    }
}
