package search;

import java.util.Arrays;

/**
 * 斐波那契(黄金分割)查找
 */
public class FibonacciSearch {
    public static int maxSize = 20;
    public static void main(String[] args) {
        int [] arr ={1,8,10,89,1000,1234};
        System.out.println(fibSearch(arr,89));
    }

    /**
     * 得到斐波那契数列
     * @return 返回斐波那契数列
     */
    public static int[] fib(){
        int[] f = new int[20];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 2] + f[i - 1];
        }
        return f;
    }

    /**
     * 斐波那契查找
     * @param arr   待查找的数组
     * @param key   要查找的值
     * @return
     */
    public static int fibSearch(int[] arr,int key){
        int low = 0;
        int high = arr.length - 1;
        int k = 0;//表示斐波那契分割数值对应的下标
        int mid = 0;//存放mid值
        int [] f =fib();//获取斐波那契数列
        //获取到斐波那契分割数值的下标
        while (high > f[k] - 1){
            k ++;
        }

        //因为f[k]的值可能大于a的长度
        //不足的部分使用0填充
        int[] temp = Arrays.copyOf(arr,f[k]);

        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        while (low <= high){//满足条件，就一直找
            mid = low + f[k - 1]-1;
            if (key < temp[mid]){//向左查找
                high = mid - 1;//右边界
                k--;
            }else if (key > temp[mid]){//向右查找
                low = mid + 1;
                k -= 2;
            }else {
                if (mid <= high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }
}
