package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序，核心思想，每次循环将未确定的数中最大或最小的数放在队尾
 * 优化思路：
 * 当在某趟排序中，没有发生一次交换，可以提前结束冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        //待排序的数组arr
        int [] arr = {3,9,-1,10,20};
        System.out.println("排序前的数组");
        System.out.println(Arrays.toString(arr));
        bubble(arr);
        System.out.println("排序后的数组");
        System.out.println(Arrays.toString(arr));

        //测试冒泡排序的速度O(n^2),给定8万个数据测试
        int[] arrTest = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arrTest[i] = (int)(Math.random() * 8000000);//生成[0,8000000)的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间:" + dateStr1);
        bubble(arrTest);
        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间:" + dateStr2);
    }

    /**
     * 冒泡排序的方法
     * @param arr 传入待排序的数组
     */
    public static void bubble(int [] arr){
        //临时变量temp
        int temp = 0;
        //标识在排序过程中是否发生过交换
        boolean flag = false;
        //冒泡排序的时间复杂度O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i ; j++) {
                //如果前面的数比后面的大，就交换
                if (arr[j] > arr[j + 1]){
                    //改变flag，表示发生过排序交换
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if (!flag){//说明一次交换都没有发生
                //提前结束
                break;
            }else {
                //完成一次排序后重置flag
                flag = false;
            }
        }
    }
}
