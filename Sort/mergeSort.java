/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 归并排序
 */
public class MergeSort {

    public int[] sortArray(int[] nums) {
        int length = nums.length;
        int[] temp = new int[length];
        mergeSort(nums, 0, length - 1, temp);
        return nums;
    }

    /**
     * @Description mergeSort the array from start to end
     * @param nums:  original array
     * @param start: the left index of array
     * @param end:   the right index of array
     * @param temp:  the temporary array used to merge two ordered array to avoid multiple creation and destruction
     * @date 2022/11/3 10:53
     */
    private void mergeSort(int[] nums, int start, int end, int[] temp) {
        //if start equals end , stop recurse
        if (start == end) {
            return;
        }
        //get the mid index
        int mid = start + ((end - start) >> 1);
        //divide the array to [start...mid]
        mergeSort(nums, start, mid, temp);
        //divide the array to [mid+1,end];
        mergeSort(nums, mid + 1, end, temp);
        //end of the nums divide,start merge
        mergeOfTwoSortedArray(nums, start, mid, end, temp);
    }

    /**
     * @Description merge two sorted array: copy the array to temp,compare the element in temp array and merge to the original array
     * @date 2022/11/3 11:15
     * @param nums:  original array
     * @param start: the left index of array
     * @param mid: the mid index of array
     * @param end:   the right index of array
     * @param temp:  the temporary array used to merge two ordered array to avoid multiple creation and destruction
    */
    private void mergeOfTwoSortedArray(int[] nums, int start, int mid, int end, int[] temp) {
        for (int i = start; i <= end; i++) {
            temp[i] = nums[i];
        }
        int i = start, j = mid + 1;
        for(int k = start;k <= end; k++){
            if(i == mid + 1){
                nums[k] = nums[j++];
            } else if(j == right + 1){
                nums[k] = nums[i++];
            } else if(temp[i] <= temp[j]){
                nums[k] = nums[i++];
            } else{
                nums[k] = nums[j++];
            }
        }
    }
}
