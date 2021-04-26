package search;

import java.util.Arrays;

/**
 * 插值查找是在二分查找的基础上，对mid的取值进行改进，减少递归的次数，从而提高查找效率
 * mid = left + (right - left) * (findVal - arr[left])/(arr[right] - arr[left])
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
        int search = insertValueSearch(arr, 0, arr.length - 1, 4);
        System.out.println(search);
    }

    /**
     * 插值查找算法
     * @param arr 待查找的数组
     * @param left  左边的索引
     * @param right 右边的索引
     * @param findVal 要查找的值
     * @return 如果找到，就返回下标,没找到，就返回-1
     */
    public static int insertValueSearch(int[] arr,int left,int right,int findVal){
        if (left > right|| findVal < arr[0] || findVal > arr[arr.length - 1]){
            return -1;
        }
        int mid = left + (right - left) * (findVal - arr[left])/(arr[right] - arr[left]);
        int midValue = arr[mid];
        if (findVal > midValue){//向右递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        }else if (findVal < midValue){//向左递归
            return insertValueSearch(arr, left, mid - 1, findVal);
        }else {
            return mid;
        }
    }


}
