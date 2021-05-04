package tree;

/**
 * 二叉排序树
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] array = {7,3,10,12,5,1,9};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加结点到二叉排序树
        for (int i = 0; i < array.length; i++) {
            Node node = new Node(array[i]);
            binarySortTree.add(node);
        }
        //中序遍历二叉排序树
        binarySortTree.infixOrder();

        binarySortTree.delNode(7);
        System.out.println("删除后");
        binarySortTree.infixOrder();
    }
}
//创建二叉排序树
class BinarySortTree{
    private Node root;
    //查找要删除的结点
    public Node search(int value){
        if (root == null){
            return null;
        }else {
            return root.search(value);
        }
    }
    //查找父结点
    public Node searchParent(int value){
        if (root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    /**
     *  删除Node为根结点的二叉排序树的最小结点
     *  返回以Node为根结点的二叉排序树的最小结点的值
     * @param node  当做一颗二叉排序树的根结点
     * @return  返回以Node为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环的查找左结点，找到最小值
        while (target.left != null){
            target = target.left;
        }
        //删除最小结点
        delNode(target.value);
        return target.value;
    }
    //删除结点
    public void delNode(int value){
        if (root == null){
            return;
        }else {
            Node targetNode = search(value);
            if (targetNode == null){//没找到要删除的结点
                return;
            }
            if (root.left == null && root.right == null){
                root = null;//删除
                return;
            }
            //找到targetNode的父结点
            Node parentNode = searchParent(value);
            if (targetNode.left == null && targetNode.right == null){//如果要删除的结点是叶子结点
                if (parentNode.left != null && parentNode.left.value == value){
                    parentNode.left = null;
                }else if (parentNode.right != null && parentNode.right.value == value){
                    parentNode.right = null;
                }
            }else if (targetNode.left != null && targetNode.right != null){//要删除的结点有两个子树
                //minValue是右子树中的最小结点值
                int minValue = delRightTreeMin(targetNode.right);
                targetNode.value = minValue;
            }else {//要删除的结点只有一颗子树
                if (targetNode.left != null){//有左子树
                    if (parentNode != null){
                        if ( parentNode.left.value == value){
                            parentNode.left = targetNode.left;
                        }else {
                            parentNode.right = targetNode.left;
                        }
                    }else {
                        root = targetNode.left;
                    }
                }else {//有右子树
                    if (parentNode != null) {
                        if (parentNode.left.value == value) {
                            parentNode.left = targetNode.right;
                        } else {
                            parentNode.right = targetNode.right;
                        }
                    }else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }
    //添加结点的方法
    public void add(Node node){
        if (root == null){
            root = node;
        }else {
            root.add(node);
        }
    }
    //中序遍历
    public void infixOrder(){
        if (root != null){
            root.infixOrder();
        }else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }
}

//创建Node结点
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 查找要删除的结点
     * @param value 希望删除的结点的值
     * @return  如果找到返回该结点，否则返回null
     */
    public Node search(int value){
        if (value == this.value){//说明找到
            return this;
        }else if (value < this.value){//应该向左子树递归查找
            if (this.left != null){
                return this.left.search(value);
            }else {
                return null;
            }
        }else {
            if (this.right != null){
                return this.right.search(value);
            }else {
                return null;
            }
        }
    }

    //

    /**
     * 查找要删除结点的父节点
     * @param value 希望删除的结点的值
     * @return  要删除结点的父节点
     */
    public Node searchParent(int value){
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        }else {
            //如果查找的值小于当前结点的值，并且当前结点的左子结点不为空
            if (value < this.value && this.left != null){//向左子树递归查找
                return this.left.searchParent(value);
            }else if (value >= this.value && this.right != null){//向右子树递归查找
                return this.right.searchParent(value);
            }else {
                return null;//没有找到父结点
            }
        }

    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加结点的方法
    //递归的形式添加结点，注意需要满足二叉排序树的要求
    public void add(Node node){
        if (node == null){
            return;
        }
        //判断传入结点的值和当前结点的根结点的值的关系
        if (node.value < this.value){
            if (this.left == null){//如果当前结点左子结点为空
                this.left = node;
            }else {
                //递归的向左子树添加
                this.left.add(node);
            }
        }else {//添加结点的值大于等于当前结点的值
            if (this.right == null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }

    }

    //中序遍历
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }

}