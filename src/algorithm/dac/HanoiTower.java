package algorithm.dac;

/**
 * 分治算法解决汉诺塔问题
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
    }

    /**
     * 分治算法解决汉诺塔移动
     * @param num   盘子的个数
     * @param a a柱子
     * @param b b柱子
     * @param c c柱子
     */
    public static void hanoiTower(int num,char a,char b,char c){
        //如果只有一个盘
        if (num == 1){
            System.out.println("第1个盘从" + a + "->" + c);
        }else {
            //当n>=2时，分为两部分看，一个是最下面的盘，一个是上面的所有盘
            //先把上面的盘a->b
            hanoiTower(num - 1,a,c,b);
            //把最下面的盘a->c
            System.out.println("第" + num + "个盘从"  + a + "->" + c);
            //把b塔的盘移动到c
            hanoiTower(num - 1,b,a,c);
        }
    }
}
