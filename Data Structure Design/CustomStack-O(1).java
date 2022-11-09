/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 设计一个支持增量操作的栈-increment O(1)
 */
public class CustomStack {

    int[] elements, increment;
    int tail, capacity;
    public CustomStack(int maxSize) {
        elements = new int[maxSize];
        increment = new int[maxSize + 1];
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
        if(increment[tail] != 0){
            //add the increments value
            ans += increment[tail];
            //the left increase value add to the previous value
            increment[tail - 1] += increment[tail];
            //the current position is already increased, put 0 on it
            increment[tail] = 0;
        }
        elements[tail - 1] = 0;
        tail--;
        return ans;
    }
    
    //time: O(1)
    //space: O(1)
    public void increment(int k, int val) {
        //put the total increase value to the array
        //the increments index=min(k,tail),it means the value in array [0...k] need to increase
        increment[Math.min(k, tail)] += val;
    }

    private boolean isEmpty(){
        return tail == 0;
    }
}
