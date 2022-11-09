/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 用队列实现栈-用一个队列实现栈
 */
public class MyStack {
    Deque<Integer> queue;
    public MyStack() {
        queue = new ArrayDeque<>();
    }
    
    public void push(int x) {
        int size = queue.size();
        queue.offer(x);
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
    }
    
    public int pop() {      
        return queue.poll();
    }
    
    public int top() {
        return queue.peek();
    }
    
    public boolean empty() {
        return queue.isEmpty();
    }
}
