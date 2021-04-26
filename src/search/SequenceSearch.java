package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 顺序查找
 */
public class SequenceSearch {
    public static void main(String[] args) {
        int[] arr = {1,9,11,-1,34,89,1};
        List<Integer> list = seqSearch(arr, 1);
        System.out.println(list);
    }

    public static List<Integer> seqSearch(int[] arr,int value){
        List<Integer> list = new ArrayList<>();
        //线性查找是逐一比对，发现有相同值，就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                list.add(i);
            }
        }
        return list;
    }

}
