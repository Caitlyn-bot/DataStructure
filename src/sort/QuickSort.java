package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 快速排序：对冒泡排序的改进
 * 折半+递归
 * 八百万一秒左右完成
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {0,0,2,-1,0,-1,0,-1,0,-1};
        quickSort(arr,0, arr.length-1);
        System.out.println(Arrays.toString(arr));

        //测试插入排序的速度,给定8万个数据测试
        int[] arrTest = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arrTest[i] = (int)(Math.random() * 800000000);//生成[0,8000000)的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间:" + dateStr1);
        quickSort(arrTest,0,arrTest.length - 1);
        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间:" + dateStr2);
    }

    /**
     * 快速排序
     * @param arr 传入数组
     * @param left 左索引
     * @param right 右索引
     */
    public static void quickSort(int[] arr,int left,int right){
        int l = left;//左索引
        int r = right;//右索引
        int pivot = arr[(left + right) / 2];//pivot中轴值
        int temp = 0;//用于交换
        //while循环的目的是让比pivot值小的放到pivot的左边，比pivot大的值放到pivot的右边
        while (l < r){
            while ( arr[l] < pivot){//在pivot的左边一直找，找到一个大于等于pivot的值退出
                l += 1;
            }
            while (arr[r] > pivot){//在pivot的右边一直找，找到一个小于等于pivot的值退出
                r -= 1;
            }
            //如果l>=r说明左右两边都已经按照预想的实现了，即l与r相遇，左右都循环过了
            if (l >= r){
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现arr[l]==pivot,r--,前移
            if (arr[l] == pivot){
                r -= 1;
            }
            //如果交换完后，发现arr[r]==pivot,l++,后移
            if (arr[r] == pivot){
                l += 1;
            }
        }

        //如果l==r，一定让l++,r--，否则会出现栈溢出的现象
        if (l == r){
            l += 1;
            r -= 1;
        }
        //注意left和right从来没有变过，一直是传入时的状态
        if (left < r){//向左递归
            quickSort(arr, left, r);
        }
        if (l < right){//向右递归
            quickSort(arr,l,right);
        }
    }
}
