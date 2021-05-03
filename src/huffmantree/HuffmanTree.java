package huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 哈夫曼树的实现
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    public static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("空树，无法遍历");
        }
    }
    /**
     * 创建哈夫曼树
     * @param arr
     * @return
     */
    public static Node createHuffmanTree(int[] arr){
        //遍历arr数组
        //将arr的每个元素构建一个Node
        //将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<>();

        for(int value : arr){
            nodes.add(new Node(value));
        }

        //循环处理
        while (nodes.size() > 1) {

            //排序
            Collections.sort(nodes);

            System.out.println("nodes = " + nodes);

            //取出根结点权值最小的两颗二叉树
            //取出权值最小的结点
            Node leftNode = nodes.get(0);
            //取出第二小的结点
            Node rightNode = nodes.get(1);
            //构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //从ArrayList中删除已经使用过的结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的结点加入到ArrayList
            nodes.add(parent);
        }
        System.out.println("处理后：" + nodes);
        return nodes.get(0);
    }
}
//创建结点类
//为了让Node对象持续排序Collections集合排序
//让Node 实现Comparable接口
class Node implements Comparable<Node>{
    int value;//结点的权值
    Node left;//左子结点
    Node right;//右子结点

    public Node(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大的排序
        return this.value - o.value;
    }

    /**
     * 前序遍历
     */
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