/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 排序链表
 */
public class sortList {
    //times: O(nlogn)
    //space: O(n)
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        return mergeSort(head, null);
    }
    public ListNode mergeSort(ListNode left, ListNode right){
        if(left == right){
            return left;
        }
        if (left.next == right) {
            left.next = null;
            return left;
        }
        ListNode slow = left, fast = left;
        while(fast != right && fast.next != right){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode leftNode = mergeSort(left, slow);
        ListNode rightNode = mergeSort(slow, right);
        return mergeTwoSortedListNode(leftNode, rightNode);
    }

    public ListNode mergeTwoSortedListNode(ListNode left, ListNode right){
        ListNode dummy = new ListNode(0);
        ListNode temp1 = left, temp2 = right, prev = dummy;
        while(temp1 != null && temp2 != null){
            if(temp1.val <= temp2.val){
                prev.next = temp1;
                temp1 = temp1.next;
            }else{
                prev.next = temp2;
                temp2 = temp2.next;
            }
            prev = prev.next;
        }
        if(temp1 != null){
            prev.next = temp1;
        }
        if(temp2 != null){
            prev.next = temp2;
        }
        return dummy.next;
    }
}
