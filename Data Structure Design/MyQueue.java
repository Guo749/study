/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 用栈实现队列
 */
public class MyQueue {

    Deque<Integer> stack1;
    Deque<Integer> stack2;
    public MyQueue() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }
    
    public void push(int x) {
        stack1.addLast(x);
    }
    
    public int pop() {
        peek();       
        return stack2.pop();
    }
    
    public int peek() {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.addLast(stack1.pop());
            }
        }
        return stack2.peek();
    }
    
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
