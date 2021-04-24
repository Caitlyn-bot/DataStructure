package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序，将待排序的数看成两部分，一部分是有序列表，一部分是无序列表
 * 不断从无序的列表里取数插入有序列表
 * 1秒完成
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101,24,119,1};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));

        //测试插入排序的速度,给定8万个数据测试
        int[] arrTest = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arrTest[i] = (int)(Math.random() * 8000000);//生成[0,8000000)的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间:" + dateStr1);
        insertSort(arrTest);
        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间:" + dateStr2);
    }
    //插入排序
    public static void insertSort(int[] arr){
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            insertVal = arr[i];
            insertIndex = i - 1;//arr[1]前面这个数的下标

            //给insertVal找到插入的位置
            //insertIndex >= 0保障不越界
            //insertVal < arr[insertIndex]说明待插入的数还未找到适当的位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出循环时，说明找到位置insertIndex+1
            //这里加入判断是否需要赋值
            if (insertIndex + 1 != i){
                arr[insertIndex + 1] = insertVal;
            }
        }

    }
}
