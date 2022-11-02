/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 二叉树的序列化与反序列化-后序遍历版
 */
public class BinaryTreeSerialize {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder stb = new StringBuilder();
        serializeTree(root,stb);
        return stb.toString();
    }

    private void serializeTree(TreeNode root,StringBuilder stb){
        if(root == null) {
            stb.append("#").append(",");
            return;
        }
        serializeTree(root.left,stb);
        serializeTree(root.right,stb);
        stb.append(root.val).append(",");
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null) return null;
        String[] datas = data.split(",");
        LinkedList<String> list = new LinkedList<>();
        for(String str : datas){
            list.addLast(str);
        }
        return deserializeTree(list);
    }

    public TreeNode deserializeTree(LinkedList<String> list) {
        String value = list.removeLast();
        if("#".equals(value)) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(value));
        head.right = deserializeTree(list);
        head.left = deserializeTree(list);
        return head;
    }
}
