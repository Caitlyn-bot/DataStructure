package sparseArray;

import java.io.*;
import java.util.Arrays;

/**
 * 稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) throws IOException {
        //创建一个11*11的二维数组
        //0表示没有棋子，1表示黑子，2表示白子
        int chessArray01[][] = new int[11][11];
        chessArray01[1][2] = 1;
        chessArray01[2][3] = 2;
        chessArray01[5][6] = 1;
        //原始的二维数组
        System.out.println("原始的二维数组为:");
        for (int[] row:chessArray01){
            for (int data :row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //将二维数组转为稀疏数组的思路

        //先遍历二维数组，得到非零数据的个数
        int sum = 0;
        for (int i = 0; i < chessArray01.length; i++) {
            for (int j = 0; j < chessArray01[0].length; j++) {
                if (chessArray01[i][j]!=0){
                    sum++;
                }
            }
        }
        //创建稀疏数组
        int sparseArray[][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        //遍历二维数组，将非零的值存放到稀疏数组
        int count = 0;//count用来记录是第几个非零数据
        for (int i = 0; i < chessArray01.length; i++) {
            for (int j = 0; j < chessArray01[0].length; j++) {
                if (chessArray01[i][j]!=0){
                    count++;//记录一次非零数据
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray01[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println("得到的稀疏数组为:");
        for (int i = 0; i <sparseArray.length ; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
        }
        //将得到的稀疏数组保存到文件map.data中
        File file = new File("map.data");
        FileWriter out = new FileWriter(file);
        for (int i = 0; i <sparseArray.length ; i++) {
            out.write(sparseArray[i][0]+"\t"+sparseArray[i][1]+"\t"+sparseArray[i][2]+"\n");
        }
        out.close();
        //从文件中读取稀疏数组
        BufferedReader in = new BufferedReader(new FileReader(file));
        //定义新的稀疏数组
        String line;//一行数据
        int rowCount = 0;
        int [][] sparseArray02 = {};
        //读取第一行数据
        while ((line = in.readLine())!=null){
            if (rowCount==0){
                String[] str = line.split("\t");
                sparseArray02 = new int[Integer.parseInt(str[2])+1][3];
                sparseArray02[rowCount][0] = Integer.parseInt(str[0]);
                sparseArray02[rowCount][1] = Integer.parseInt(str[1]);
                sparseArray02[rowCount][2] = Integer.parseInt(str[2]);
                rowCount++;
            }else {
                String[] str = line.split("\t");
                sparseArray02[rowCount][0] = Integer.parseInt(str[0]);
                sparseArray02[rowCount][1] = Integer.parseInt(str[1]);
                sparseArray02[rowCount][2] = Integer.parseInt(str[2]);
                rowCount++;
            }
        };
        //输出从文件中读取的稀疏数组
        System.out.println("从文件中读取的稀疏数组为:");
        for (int i = 0; i <sparseArray02.length ; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArray02[i][0],sparseArray02[i][1],sparseArray02[i][2]);
        }

        //将稀疏数组恢复成二维数组
        //根据稀疏数组的第一行创建二维数组
        int chessArray02[][] = new int[sparseArray02[0][0]][sparseArray02[0][1]];
        //循环稀疏数组的后几行，给二维数组赋值
        for (int i = 1; i <sparseArray02.length ; i++) {
            chessArray02[sparseArray02[i][0]][sparseArray02[i][1]] = sparseArray02[i][2];
        }
        //输出恢复后的二维数组
        System.out.println("恢复后的二维数组为：");
        for (int[] row:chessArray02){
            for (int data :row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

    }
}
