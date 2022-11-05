/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 数组中的逆序对
 */
public class Solution {

    //times: O(nlogn)
    //space: O(n)
    public int reversePairs(int[] nums) {
        int length = nums.length;
        if(length < 2) return 0;
        int[] temp = new int[length];
        return mergeSort(nums, 0, length-1, temp);
    }

    private int mergeSort(int[] nums, int left, int right, int[] temp){
        if(left == right){
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        int leftPair = mergeSort(nums, left, mid, temp);
        int rightPair = mergeSort(nums, mid + 1, right, temp);
        if(nums[mid] <= nums[mid+1]){
            return leftPair + rightPair;
        }
        int crossPair = mergeTwoSortedNums(nums, left, mid, right, temp);
        return leftPair + rightPair + crossPair;
    }

    private int mergeTwoSortedNums(int[] nums, int left, int mid, int right, int[] temp){
        for(int i = left;i <= right; i++){
            temp[i] = nums[i];
        }

        int i = left, j = mid + 1, res = 0;
        for(int k = left; k <= right; k++){
            if(i == mid + 1){
                nums[k] = temp[j++];
            }else if(j == right + 1){
                nums[k] = temp[i++];
            }else if(temp[i] <= temp[j]){
                nums[k] = temp[i++];
            } else{
                nums[k] = temp[j++];
                res += mid - i + 1;
            }
        }
        return res;
    }
}
