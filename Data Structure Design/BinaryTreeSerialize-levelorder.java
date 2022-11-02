/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 二叉树的序列化与反序列化-层序遍历版
 */
public class BinaryTreeSerialize {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return null;
        StringBuilder stb = new StringBuilder();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode head = queue.poll();
                if(head == null) {
                    stb.append("#").append(",");
                }else{
                    stb.append(head.val).append(",");
                    queue.offer(head.left);
                    queue.offer(head.right);
                }     
            }
        }
        return stb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null) return null;
        String[] datas = data.split(",");
        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode head = new TreeNode(Integer.parseInt(datas[0]));
        queue.offer(head);
        int index = 1;
        while(!queue.isEmpty()){
            TreeNode top = queue.poll();
            if("#".equals(datas[index])){
                top.left = null;
            }else{
                top.left = new TreeNode(Integer.parseInt(datas[index]));
                queue.offer(top.left);
            }
            index++;
            if("#".equals(datas[index])){
                top.right = null;
            }else{
                top.right = new TreeNode(Integer.parseInt(datas[index]));
                queue.offer(top.right);
            }
            index++;
        }
        return head;
    }
}
