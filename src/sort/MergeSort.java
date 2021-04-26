package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 归并排序
 * 分治思想+递归
 * 先进行分解，分到每一个数为一个个体
 * 然后进行合并，将两个有序的合并成一个有序的
 * 不断合并，直到归一
 * 8000万1-2秒
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        int[] temp = new int[arr.length];
        mergeSort(arr,0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));


        //测试插入排序的速度,给定8000万个数据测试
        int[] arrTest = new int[8000000];
        int[] arrTemp = new int[arrTest.length];
        for (int i = 0; i < 8000000; i++) {
            arrTest[i] = (int)(Math.random() * 800000000);//生成[0,8000000)的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间:" + dateStr1);
        mergeSort(arrTest,0,arrTest.length - 1, arrTemp);
        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间:" + dateStr2);
    }


    public static void mergeSort(int [] arr,int left ,int right,int[] temp){
        if (left < right){//分到left = right为止
            int mid = (left + right) / 2;//中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //每分解一次就合并一次
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     * 合并
     * @param arr 待合并的数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边有序序列的索引
     * @param temp  临时存放的数组，用于中转
     */
    public static void merge(int[] arr,int left ,int mid,int right,int[] temp){
        int i = left;//初始化i,左边有序序列的初始索引
        int j = mid + 1;//初始化j，右边有序序列的初始索引
        int t = 0;//指向temp数组的当前索引，(存到哪里)
        //先把左右两边的有序数据按照规则填充到temp数组
        //直到两个序列有一边处理完成
        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]){//将较小的拷贝到temp中
                temp[t] = arr[i];
                t += 1;
                i += 1;
            }else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        //将有剩余的那一方依次填充到temp数组
        while (i <= mid){//左边的有序序列还有剩余，全部填充到temp中
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right){
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        //再将temp数组拷贝到arr
        //并不是每一次拷贝都拷贝所有
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){//第一次合并 tempLeft = 0，right = 1
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }

}
