/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description
 */
public class MyCircularQueue {

    int[] queue;
    int head;
    int tail;
    int length;

    public MyCircularQueue(int k) {
        queue = new int[k];
        head = -1;
        tail = -1;
        length = k;
    }

    public boolean enQueue(int value) {
        //队列已满
        if(isFull()) return false;
        //如果队列为空，头指针与尾指针均指向队列第一个元素
        if(isEmpty()) head = 0;
        //由于是循环队列，队满时尾指针指向队尾，这时队头可能有位置可以插入
        tail = (tail + 1) % length;
        queue[tail] = value;
        return true;
    }

    public boolean deQueue() {
        //队列为空
        if(isEmpty()) return false;
        //如果队尾与队头指针相遇，表示队列中只剩最后一个元素
        //出队后队列为空，即head和tail都为-1
        if(head == tail){
            head = -1;
            tail = -1;
        }else{
            head = (head + 1) % length;
        }
        return true;
    }

    public int Front() {
        return isEmpty() ? -1 : queue[head];
    }

    public int Rear() {
        return isEmpty() ? -1 : queue[tail];
    }

    public boolean isEmpty() {
        return head == -1 && tail == -1;
    }

    public boolean isFull() {
        //如果tail的下一个元素是head，则表示队列已满
        return (tail + 1) % length == head;
    }
}
