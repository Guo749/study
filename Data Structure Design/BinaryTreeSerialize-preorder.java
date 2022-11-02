/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 二叉树的序列化与反序列化-前序遍历版
 */
public class BinaryTreeSerialize {

    //time: O(n2)
    //space: O(n)
    String SEP = ",";
    String NULL = "#";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder stb = new StringBuilder();
        serializeTreeNode(root,stb);
        return stb.toString();
    }

    private void serializeTreeNode(TreeNode root,StringBuilder stb){
        if(root == null) {
            stb.append(NULL).append(SEP);
            return ;
        }
        stb.append(root.val).append(SEP);
        serializeTreeNode(root.left,stb);
        serializeTreeNode(root.right,stb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null) return null;
        String[] datas = data.split(",");
        return deserializeData(datas);
    }

    int index = 0;
    public TreeNode deserializeData(String[] datas){
        String value = datas[index];
        if(NULL.equals(value)){
            index++;
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(value));
        index++;
        head.left = deserializeData(datas);
        head.right = deserializeData(datas);
        return head;
    }
}
