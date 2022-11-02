/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 寻找重复的子树-递归版
 */
public class constructFromPrePost {

    //time: O(n2)
    //space: O(n)
    List<TreeNode> ans = new ArrayList<>();
    HashMap<String,Integer> map = new HashMap<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        duplicateTree(root);
        return ans;
    }

    public TreeNode duplicateTree(TreeNode node){
        if(node == null) return null;
        TreeNode left = duplicateTree(node.left);
        TreeNode right = duplicateTree(node.right);
        String key = node.val + "-" + (left == null ? "201" : left.val) + "-" + (right == null ? "202" : right.val);
        map.put(key,map.getOrDefault(key,0) + 1);
        System.out.println(map);
        if(map.get(key) == 2){
            ans.add(node);
        }
        return node;
    }
}
