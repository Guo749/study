/**
 * @author lzn
 * @date 2022/11/03 10:40
 * @Description 快速排序
 */
public class QuickSort {

    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }
    
    /**
     * step1: initialize the dummy index(normally the left bounds index)
     * step2: one pointers is i = l, the other is j = r
     * step3: j-- to find the first element which smaller than left
     * step4: i++ to find the first element which larger than left
     * step5: exchange the element i and j
     * step6: repeat the process from step3 to step 5, finally we will get the pivot = i
     * step7: exchange the element left and i, now the left array is smaller than pivot, the right array is larger than pivot
     * step8: divide the array from l...pivot-1,pivot...r, recurse the quickSort
     * @param nums: the array which we send in
     * @param l: the left bounds of the array
     * @param r: the right bounds of the array
    */
    private void quickSort(int[] nums, int l, int r) {
        //if the nums length is 0 or 1, return
        if (l >= r) {
            return;
        }
        int i = l, j = r;
        while (i < j) {
            while (i < j && nums[j] >= nums[l]) {
                j--;
            }
            while (i < j && nums[i] <= nums[l]) {
                i++;
            }
            swap(nums, i, j);
        }
        swap(nums, i, l);
        //divide the array to [left...pivot - 1]
        quickSort(nums, l, i - 1);
        //divide the array to [pivot + 1...right];
        quickSort(nums, i + 1, r);
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums = new int[]{8, 6, 7, 2, 3, 5, 4, 1};
        int[] res = quickSort.sortArray(nums);
        System.out.println(Arrays.toString(res));
    }
}
