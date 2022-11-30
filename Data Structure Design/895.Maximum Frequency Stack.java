class FreqStack {

    //step1: initilize the maxFreq to calculate the max frequency
    //step2: use HashMap -> key: val, value: freq
    //step3: use HashMap -> key: freq, value: Stack -> the val which the frequency are equals and the most recently used val is on the top of stack
    int maxFreq;
    HashMap<Integer, Integer> freqMap;
    HashMap<Integer, Deque<Integer>> sameFreqMap;
    public FreqStack() {
        maxFreq = 0;
        freqMap = new HashMap<>();
        sameFreqMap = new HashMap<>(); 
    }
    
    public void push(int val) {
        int freq = freqMap.getOrDefault(val, 0) + 1;
        freqMap.put(val, freq);
        sameFreqMap.putIfAbsent(freq, new ArrayDeque<>());
        sameFreqMap.get(freq).push(val);
        maxFreq = Math.max(maxFreq, freq);
    }
    
    public int pop() {
        Deque<Integer> stack = sameFreqMap.get(maxFreq);
        int top = stack.pop();
        freqMap.put(top, freqMap.get(top) - 1);
        if(stack.isEmpty()){
            maxFreq--;
        }
        return top;
    }
}