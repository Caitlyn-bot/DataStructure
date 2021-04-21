package recursion;

public class MiGong {
    public static void main(String[] args) {
        //先创建一个数组，模拟迷宫

        //地图
        int [][] map = new int[8][7];

        //使用1表示墙
        //上下全置为1
        for (int i = 0; i < 7 ; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        //输出map
        System.out.println("地图的情况如下：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        //使用递归回溯给小球找路
        setWay(map,1,1);
        //输出新的地图
        System.out.println("地图的通路情况如下：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * 使用递归回溯来给小球找路
     * 如果小球找到(6,5)这个位置，说明通路找到
     * 当map[i,j]为0表示没走过，1表示墙，2表示可以走，3表示可以走，但走不通
     * 设置方向的优先级：右->下->上->左，如果该点走不通，再回溯
     * @param map 表示地图
     * @param i 从哪个位置开始找横坐标
     * @param j 从哪个位置开始找纵坐标
     * @return 是否找到通路
     */
    public static boolean setWay(int [][] map ,int i ,int j){
        if (map[6][5] == 2){
            //说明已经找到通路
            return true;
        }else {
            if (map[i][j] == 0){//如果当前的点还没有走过
                map[i][j] = 2;//假定该点是可以走通的
                if (setWay(map,i + 1,j)){//向下走
                    return true;
                }else if (setWay(map,i,j + 1)){//向右走
                    return true;
                }else if (setWay(map,i - 1,j)){//向上走
                    return true;
                }else if (setWay(map,i,j - 1)){//向左走
                    return true;
                }else {
                    //说明该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            }else {//如果不为0，可能是1,2,3
                return false;
            }
        }
    }
}
