/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 设计一个支持增量操作的栈-increment O(n)
 */
public class CustomStack {

    int[] elements;
    int tail, capacity;
    public CustomStack(int maxSize) {
        elements = new int[maxSize];
        tail = 0;
        capacity = maxSize;
    }
    
    //time: O(1)
    //space: O(capacity)
    public void push(int x) {
        if(tail >= capacity){
            return;
        }
        elements[tail] = x;
        tail++;
    }
    
    //time: O(1)
    //space: O(1)
    public int pop() {
        if(isEmpty()){
            return -1;
        }
        int ans = elements[tail - 1];
        elements[tail - 1] = 0;
        tail--;
        return ans;
    }
    
    //time: O(k)
    //space: O(1)
    public void increment(int k, int val) {
        for(int i = 0;i < (k < tail ? k : tail);i++){
            elements[i] += val;
        }
    }

    private boolean isEmpty(){
        return tail == 0;
    }
}
