package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 基数排序
 * 用空间换时间
 * 将数按照各个位数进行排序
 */
public class RadixSort {
    public static void main(String[] args) {
        int [] arr = {53,3,542,748,14,214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));

        //测试基数排序的速度,给定800万个数据测试
        int[] arrTest = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arrTest[i] = (int)(Math.random() * 800000000);//生成[0,8000000)的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间:" + dateStr1);
        radixSort(arrTest);
        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间:" + dateStr2);
    }
    //基数排序
    public static void radixSort(int[] arr){
        //得到数组中最大的数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();

        //定义一个二维数组，表示10个桶，每个桶都是一个一维数组
        //二维数组包含10个一维数组
        //为了防止在放入数的时候，数据溢出，则每个一维数组的长度定义为arr.length
        int [][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据，定义一个一维数组，记录每个桶的有效数据个数
        int [] bucketElementCounts = new int[10];

        for (int i = 0,n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0;j < arr.length;j++){
                //取出个位的值
                int digitOfElement = arr[j]/n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement] ++;
            }
            //按照一维数组的下标依次取出数据，放入原数组
            //遍历每一个桶
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length;k++){
                //如果桶中有数据,放入原数组
                if (bucketElementCounts[k] != 0){//桶中有数据
                    //循环该桶
                    for (int l = 0;l < bucketElementCounts[k];l++){
                        //取出元素放入到arr中
                        arr[index] = bucket[k][l];
                        index ++;
                    }
                }
                bucketElementCounts[k] = 0;//将桶清空
            }

        }

    }
}
