package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 * 要求数组必须是有序的
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr ={1,8,10,89,1000,1234};
        int[] arr2 ={1,8,10,89,1000,1000,1000,1000,1234};
        int resultIndex = binarySearch(arr,0,arr.length,1000);
        System.out.println(resultIndex);
        List<Integer> list = binarySearch2(arr2, 0, arr2.length, 1000);
        System.out.println(list);
    }

    /**
     * 二分查找法
     * 返回第一个找到的下标
     * @param arr 待查找的数组
     * @param left  左边的索引
     * @param right 右边的索引
     * @param findValue 要查找的值
     * @return 如果找到，就返回下标,没找到，就返回-1
     */
    public static int binarySearch(int [] arr,int left,int right,int findValue){
        //当left>right说明递归了整个数组，但是没有找到
        if (left > right){
            return -1;
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (findValue > midValue){//向右递归
            return binarySearch(arr, mid + 1, right, findValue);
        }else if (findValue < midValue){//向左递归
            return binarySearch(arr, left, mid - 1, findValue);
        }else {
            return mid;
        }
    }


    /**
     * 将集合中所有的与findValue相同值的下标都返回
     * @param arr
     * @param left
     * @param right
     * @param findValue
     * @return
     */
    public static List<Integer> binarySearch2(int [] arr, int left, int right, int findValue){
        //当left>right说明递归了整个数组，但是没有找到
        if (left > right){
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (findValue > midValue){//向右递归
            return binarySearch2(arr, mid + 1, right, findValue);
        }else if (findValue < midValue){//向左递归
            return binarySearch2(arr, left, mid - 1, findValue);
        }else {
            List<Integer> resIndexList = new ArrayList<Integer>();
            //向左扫描
            int temp = mid -1;
            while (true){
                if (temp < 0 || arr[temp] != findValue){//扫描到最左边或者不等于要找的数
                    break;
                }
                //将temp放入到集合中
                resIndexList.add(temp);
                temp -= 1;
            }
            //最后记得将中间的放入集合
            resIndexList.add(mid);
            //向右扫描
            temp = mid + 1;
            while (true){
                if (temp > arr.length - 1 || arr[temp]!= findValue){
                    break;
                }
                resIndexList.add(temp);
                temp += 1;
            }
            return resIndexList;
        }
    }

}
