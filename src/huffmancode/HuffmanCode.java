package huffmancode;

import java.util.*;

/**
 * 哈夫曼编码
 * 创建哈夫曼编码树
 * 得到哈夫曼编码表（向左为0，向右为1）
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] contentBytes = str.getBytes();

        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("huffmanCodeBytes =" + Arrays.toString(huffmanCodeBytes));
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