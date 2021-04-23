package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序：每次找出a[i]到a[n]的数中的最值与第i个数进行交换
 * 2秒完成
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101,34,119,1,-1,90,123};
        System.out.println("排序前的数组");
        System.out.println(Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后的数组");
        System.out.println(Arrays.toString(arr));
        //测试选择排序的速度O(n^2),给定8万个数据测试
        int[] arrTest = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arrTest[i] = (int)(Math.random() * 8000000);//生成[0,8000000)的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间:" + dateStr1);
        selectSort(arrTest);
        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间:" + dateStr2);
    }

    /**
     * 选择排序
     * @param arr 待排序的数组
     */
    public static void selectSort(int[] arr){
        //先易后难，将复杂的算法拆分成简单的问题

        for(int i = 0; i < arr.length;i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {//最小值不是最小，进行交换
                    min = arr[j];//重置min
                    minIndex = j;//重置minindex
                }
            }
            if (minIndex != i) {
                //进行交换
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }

    }
}
