package recursion;

/**
 * 利用递归解决八皇后问题
 */
public class Queue8 {
    //定义max表示有多少个皇后
    int max = 8;
    //定义数组array，保存皇后放置的结果
    int[] array = new int[max];
    //统计解法的次数
    static int count = 0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d种解法",count);
    }

    /**
     * 放置第n个皇后
     * @param n 第几个皇后
     */
    private void check(int n){
        if (n == max){// n=8 就是第九个皇后
            //也就是前八个皇后已经放好了
            //打印输出
            print();
            return;
        }
        //依次放入皇后并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n,先放在第一列
            array[n] = i;
            //当放置第n个皇后到i列时，是否冲突
            if (judge(n)){//如果不冲突，放n+1
                check(n + 1);//开始递归
            }
            //如果冲突，就继续执行array[n] = i,即将皇后放在下一个位置
        }
    }

    /**
     * 判断第n个皇后摆放时是否和已有的皇后冲突
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n]||Math.abs(n - i) == Math.abs(array[n] - array[i])){
                //array[i] == array[n]表示在同一列
                //Math.abs(n - i) == Math.abs(array[n] - array[i])表示在同一个斜线
                return false;
            }
        }
        return true;
    }

    /**
     * 将皇后摆放的位置输出
     */
    private void print(){

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        count++;
    }

}
