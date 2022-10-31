/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 二叉树的最近公共祖先4-迭代版
 */
public class lowestCommonAncestor {

    //time: O(m+n)
    //space: O(1)
    public Node lowestCommonAncestor(Node p, Node q) {
        Node pt = p,qt = q;
        while(pt != qt){
            if(pt == null){
                pt = q;
            }else{
                pt = pt.parent;
            }
            if(qt == null){
                qt = p;
            }else{
                qt = qt.parent;
            }
        }
        return pt;
    }
}
