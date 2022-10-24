package com.dailytest.dataStructureDesign;

/**
 * @author lzn
 * @date 2022/10/24 18:09
 * @Description
 */
public class MyCircularQueueLinkedList {

    ListNode front;
    ListNode rear;
    int capacity;
    int size;

    public MyCircularQueueLinkedList(int k) {
        capacity = k;
        size = 0;
    }

    public boolean enQueue(int value) {
        //队列已满
        if(isFull()) return false;
        ListNode next = new ListNode(value);
        if(front == null){
            front = rear = next;
        }else{
            rear.next = next;
            rear = next;
        }
        size++;
        return true;
    }

    public boolean deQueue() {
        //队列为空
        if(isEmpty()) return false;
        ListNode dummy = new ListNode();
        dummy.next = front;
        dummy.next = dummy.next.next;
        //队首元素出队后，更新front
        front = dummy.next;
        size--;
        return true;
    }

    public int Front() {
        return isEmpty() ? -1 : front.val;
    }

    public int Rear() {
        return isEmpty() ? -1 : rear.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}
