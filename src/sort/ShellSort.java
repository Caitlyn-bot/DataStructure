package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序
 * 分组加插入排序
 * 交换式希尔排序7秒完成
 * 移动式希尔排序1秒内完成
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        shellSortMove(arr);
        System.out.println(Arrays.toString(arr));

        int[] arrTest = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arrTest[i] = (int)(Math.random() * 8000000);//生成[0,8000000)的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat.format(date1);
        System.out.println("交换式希尔排序前的时间:" + dateStr1);
        shellSortExchange(arrTest);
        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("交换式希尔排序后的时间:" + dateStr2);

        Date date3 = new Date();
        String dateStr3 = simpleDateFormat.format(date3);
        System.out.println("移动式希尔排序前的时间:" + dateStr3);
        shellSortMove(arrTest);
        Date date4 = new Date();
        String dateStr4 = simpleDateFormat.format(date4);
        System.out.println("移动式希尔排序后的时间:" + dateStr4);
    }

    //交换式希尔排序
    //在对有序序列插入时采用交换法
    public static void shellSortExchange(int[] arr){
        for (int gap = arr.length / 2;gap > 0;gap /= 2){//gap表示步长
            //辅助交换变量
            int temp = 0;
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中的所有元素，共gap组，步长为gap
                for (int j = i - gap; j >= 0 ; j -= gap) {
                    //如果当前元素大于加上步长那个元素，说明需要交换
                    if (arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }

    }


    //移动式希尔排序：分组+插入
    public static void shellSortMove(int[] arr){
        for (int gap = arr.length / 2;gap > 0;gap /= 2){//gap表示步长
            //从第gap个元素开始，逐个对其所在的组直接进行插入排序
            for (int i = gap; i < arr.length; i++) {
               int j = i;
               int temp = arr[j];
               if (arr[j] < arr[j - gap]){
                   while (j - gap >= 0 && temp < arr[j - gap]){
                       //移动
                       arr[j] = arr[j - gap];
                       j -= gap;
                   }
                   //当退出循环，说明找到插入位置
                   arr[j] = temp;
               }
            }
        }

    }
}
