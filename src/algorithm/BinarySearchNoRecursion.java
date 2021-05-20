package algorithm;

/**
 * 二分查找(非递归)
 * 二分查找要求待查找的数组有序
 */
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        int[] arr = {1,3,8,10,11,67,100};
        int index = binarySearch(arr,11);
        System.out.println("index =" + index);
    }

    /**
     * 二分查找的非递归实现
     * @param array 待查找的数组，array为升序排列
     * @param target  要查找的目标
     * @return  返回对应下标，-1表示未找到
     */
    public static int binarySearch(int[] array,int target){
        int left = 0;
        int right = array.length - 1;
        while (left <= right){//说明可以继续查找
            int mid = (left + right) / 2;
            if (array[mid] == target){
                return mid;
            }else if (array[mid] > target){
                right = mid - 1;//将数组向左查找
            }else {
                left = mid + 1;//将数组向右查找
            }
        }
        return -1;
    }
}
