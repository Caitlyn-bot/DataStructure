package tree;

/**
 * 顺序存储二叉树
 * 顺序存储二叉树通常只考虑完全二叉树
 * 第n个元素的左子结点为2*n+1
 * 第n个元素的右子结点为2*n+2
 * 第n个元素的父结点为(n-1)/2
 * n:表示二叉树中的第几个元素
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder(0);
    }
}
//编写ArrayBinaryTree，实现顺序存储二叉树遍历
class ArrayBinaryTree{
    private int[] arr;//存储数据结点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载preOrder
    public void preOrder(){
        this.preOrder(0);
    }
    public void infixOrder(){
        this.infixOrder(0);
    }
    public void postOrder(){

    }

    /**
     * 编写一个方法，完成顺序存储二叉树的前序遍历
     * @param index 数组的下标
     */
    public void preOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归遍历
        if ((index * 2 + 1) < arr.length){
            preOrder(2 * index + 1);
        }
        //向右递归遍历
        if ((index * 2 + 2) < arr.length){
            preOrder(2 * index + 2);
        }
    }

    public void infixOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }

        //向左递归遍历
        if ((index * 2 + 1) < arr.length){
            preOrder(2 * index + 1);
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向右递归遍历
        if ((index * 2 + 2) < arr.length){
            preOrder(2 * index + 2);
        }
    }
    public void postOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //向左递归遍历
        if ((index * 2 + 1) < arr.length){
            preOrder(2 * index + 1);
        }
        //向右递归遍历
        if ((index * 2 + 2) < arr.length){
            preOrder(2 * index + 2);
        }
        //输出当前这个元素
        System.out.println(arr[index]);
    }
}