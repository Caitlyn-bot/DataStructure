package huffmancode;

import java.io.*;
import java.util.*;

/**
 * 哈夫曼编码
 * 创建哈夫曼编码树
 * 得到哈夫曼编码表（向左为0，向右为1）
 * @author 张志伟
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] contentBytes = str.getBytes();

        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("huffmanCodeBytes =" + Arrays.toString(huffmanCodeBytes));
        byte[] bytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("解码后的字符串" + new  String(bytes));
        String srcFile = "G:\\IDEA-WorkSpace\\DataStructure\\src\\huffmancode\\src.jpg";
        String dstFile = "G:\\IDEA-WorkSpace\\DataStructure\\src\\huffmancode\\dst.zip";
        String srcFile2 = "G:\\IDEA-WorkSpace\\DataStructure\\src\\huffmancode\\src2.jpg";
        zipFile(srcFile,dstFile);
        unZipFile(dstFile,srcFile2);
    }


    /**
     * 解压文件
     * @param zipFile 准备解压的文件
     * @param dstFile   将文件解压到哪个位置
     */
    public static void unZipFile(String zipFile,String dstFile){
        //定义文件的输入流
        InputStream is = null;
        //定义对象输入流
        ObjectInputStream ois = null;
        //定义文件输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建于is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取字节数组
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取哈夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte,String>) ois.readObject();
            //开始解码
            byte[] bytes = decode(huffmanCodes,huffmanBytes);
            //将bytes写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到文件中
            os.write(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    /**
     * 将文件进行压缩
     * @param srcFile   希望压缩的文件的完整路径
     * @param dstFile   压缩后将压缩文件放到哪个目录下
     */
    public static void zipFile(String srcFile,String dstFile){
        //创建文件的输入流，准备读取文件
        FileInputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建输出流
        try {
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]数组
            //is.available()返回源文件的大小
            byte[] b =new byte[is.available()];
            //读取文件
            is.read(b);
            //使用哈夫曼编码进行编码
            //直接对原文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流，准备存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //以对象流的方法写入哈夫曼编码，方便恢复原文件
            oos.writeObject(huffmanBytes);
            //注意一定要把哈夫曼编码表写入压缩文件
            oos.writeObject(huffmanCodes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //完成数据的解压
    //将压缩后的哈弗曼编码重新转为二进制的字符串
    //按照哈弗曼编码表将二进制字符串转为字符串

    /**
     * 完成对压缩数据的解码
     * @param huffmanCodes  哈夫曼编码表
     * @param huffmanBytes  哈夫曼编码得到的字节数组
     * @return  返回原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        //先得到huffmanBytes对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte[]转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            //如果是最后一个字节，无需补高位
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag,huffmanBytes[i]));
        }
        //将字符串按照指定的哈夫曼编码进行解码
        //将哈夫曼编码表进行调换
        Map<String,Byte> map = new HashMap<>();
        for (Map.Entry<Byte,String> entry: huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
        //创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0;i < stringBuilder.length();){//i可以理解成一个索引，在不断的扫描
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag){
                //取出一个字符1,0
                String key = stringBuilder.substring(i, i + count);
                //i不动，让count移动，直到匹配到一个字符
                b = map.get(key);
                if (b == null){//没有匹配到
                    count++;
                }else {
                    //匹配到,退出
                    flag = false;
                }
            }
            list.add(b);
            i += count;//让i直接移动到count
        }
        //当for循环结束后，list中就存放了所有的字符
        //将list中的数据放入byte[]并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte转为二进制的字符串
     * @param b     传入的byte值
     * @param flag  标识是否需要补高位 如果是最后一个字节，无需补高位
     * @return  传入的b对应的二进制的字符串(按照补码返回)
     */
    private static String byteToBitString(boolean flag,byte b){
        //使用变量保存b
        int temp = b;
        //如果不足8位，要补高位
        if (flag){
            //按位或 |：两位有一个为1，结果为1，否则为0
            temp |= 256;//按位或 ，256:1 0000 0000
        }
        String str = Integer.toBinaryString(temp);

        if (flag) {
            //取最后8位
            return str.substring(str.length() - 8);
        }else {
            return str;
        }
    }


    /**
     * 封装生成哈夫曼编码的方法
     * @param bytes 原始的字符串对应的字节数组
     * @return
     */
    private static byte[] huffmanZip(byte[] bytes){
        //将字符数组转为结点
        List<Node> nodes = getNodes(bytes);
        //创建哈夫曼树
        Node HuffmanTreeRoot = createHuffmanTree(nodes);
        //获取哈夫曼编码表
        getCodes(HuffmanTreeRoot,"",stringBuilder);
        //查看最后的编码
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    /**
     * 将字符串对应的byte[]数组，通过生成哈夫曼编码表，返回一个哈夫曼编码压缩后的byte[]
     * @param bytes 原始的字符串对应的byte[]数组
     * @param huffmanCodes 哈夫曼编码表
     * @return  返回huffmanCodes处理后的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte,String> huffmanCodes){
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }

        //将stringBuilder转为byte[]
        int len;
        if (stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        }else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte[]数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个byte
        //i += 8 步长为8
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()){//最后一个不足8位
                strByte = stringBuilder.substring(i);//从i取到最后
            }else {
                strByte = stringBuilder.substring(i,i + 8);//从i取到i+8
            }

            //将strByte转为byte放入huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte,2);
            index ++;
        }
        return huffmanCodeBytes;
    }

    //生成哈夫曼树对应的哈弗曼编码
    //将哈夫曼编码表存放在Map<Byte,String>
    //在生成哈弗曼编码时，需要去拼接01路径，定义一个StringBuilder存储某个叶子结点的路径
    static Map<Byte,String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();
    /**
     * 将传入的node结点的所有叶子结点的哈夫曼编码得到并存放到HuffmanCode集合
     * @param node  传入结点
     * @param code  路径：左子结点是0，右子结点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入stringBuilder2
        stringBuilder2.append(code);
        if (node != null){
            //判断当前结点是叶子结点还是非叶子结点
            if (node.data == null){//非叶子结点
                //递归处理
                //向左递归
                getCodes(node.left,"0",stringBuilder2);
                //向右递归
                getCodes(node.right,"1",stringBuilder2);
            }else {//是叶子结点
                //存放到编码表
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }

    /**
     *
     * @param bytes 接收字节数组
     * @return  返回List
     */
    private static List<Node> getNodes(byte[] bytes){
        //创建ArrayList
        List<Node> nodes = new ArrayList<>();
        //遍历统计每个byte出现的次数->map
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes){
            Integer count = counts.get(b);
            if (count == null){//map没有这个字符数据
                counts.put(b,1);
            }else {
                counts.put(b,count + 1);
            }
        }
        //将每个键值对转成一个node对象并加入nodes集合
        for (Map.Entry<Byte,Integer> entry : counts.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    //前序遍历
    private static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("哈夫曼树为空");
        }
    }

    /**
     * 创建哈夫曼二叉树
     * @param nodes
     * @return
     */
    private static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1){
            //从小到大
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //根结点只有权值，没有data
            Node parent = new Node(null,leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
class Node implements Comparable<Node>{
    Byte data;//存放数据本身
    int weight;//权值：表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
}