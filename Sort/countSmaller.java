/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 计算右侧小于当前元素的个数
 */
public class Solution {

    //times: O(nlogn)
    //space: O(n)
    public List<Integer> countSmaller(int[] nums) {
        int length = nums.length;
        List<Integer> counts = new ArrayList<>(length);
        //initialize the integer array to save the answer 
        int[] ans = new int[length];
        //initialize the integer array to save the original nums index;
        //because the subject requires the sum which is smaller than the current value
        //so we not only need to know the elements,but also neet to know the index
        //use the index array can let us to find the element on O(1) times 
        int[] indexes = new int[length];
        for(int i = 0;i < length;i++){
            indexes[i] = i;
        }
        //initializ the integer array to save the index in order to compare the element 
        int[] temp = new int[length];
        mergeSort(nums, 0, length-1, indexes, temp, ans);
        for(int i = 0; i < length;i++){
            counts.add(ans[i]);
        }

        return counts;
    }

    private void mergeSort(int[] nums, int left, int right, int[] indexes, int[] temp, int[] ans){
        if(left == right){
            return;
        }

        int mid = left + ((right - left) >> 1);
        mergeSort(nums, left, mid, indexes, temp, ans);
        mergeSort(nums, mid+1, right, indexes, temp, ans);
        if(nums[indexes[mid]]  < nums[indexes[mid + 1]]){
            return;
        }
        mergeTwoSortedNums(nums, left, mid, right, indexes, temp, ans);
    }
    
    private void mergeTwoSortedNums(int[] nums, int left, int mid, int right, int[] indexes, int[] temp, int[] ans){
        for(int i = left; i <= right; i++){
            temp[i] = indexes[i];
        }

        int i = left, j = mid + 1;
        for(int k = left;k <= right;k++){
            if(i == mid + 1){
                indexes[k] = temp[j++];
            }else if(j == right + 1){
                indexes[k] = temp[i++];
                ans[indexes[k]] += (right - mid);
            }else if(nums[temp[i]] <= nums[temp[j]]){
                indexes[k] = temp[i++];
                ans[indexes[k]] += j - mid -1; 
            } else{
                indexes[k] = temp[j++];  
            }
        }
    }
}
