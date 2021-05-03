package sort;

import java.util.Arrays;

/**
 * 堆排序
 * O(nlogn)
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4,6,8,5,9};
        heapSort(arr);
    }
    //编写一个堆排序的方法
    public static void heapSort(int[] arr){
        int temp = 0;
        System.out.println("堆排序");
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr,i,arr.length);
        }

        //将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端
        //重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素
        //反复执行调整+交换步骤，直到整个序列有序
        for (int j = arr.length - 1;j > 0;j--){
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 将一个数组（二叉树）调整成大顶堆
     * 将以i对应的非叶子结点的树调整成大顶堆
     * @param arr 待调整的数组
     * @param i 表示非叶子结点在数组中的索引
     * @param length   表示对多少个元素继续调整 length不断减少
     */
    public  static void adjustHeap(int[] arr,int i,int length){
        int temp = arr[i];//先取出当前元素，保存在临时变量
        //开始调整
        //k = i * 2 +1 k是i结点的左子结点
        for (int k = i * 2 +1;k < length;k = k * 2+ 1){
            if ( k + 1 < length && arr[k] < arr[k + 1]){//左子结点小于右子结点
                k++;//让k指向右子结点
            }
            if (arr[k] > temp){//需要交换
                arr[i] = arr[k];//将较大的数赋给当前结点
                i = k;//让i指向k，继续循环比较
            }else {
                break;//因为我们从下向上进行调整，所以不用考虑下面的子树
            }
        }
        //当for循环结束后，我们将以i为父结点的最大值放在了当前局部树的顶
        arr[i] = temp;//将temp放到调整后的位置
    }
}
