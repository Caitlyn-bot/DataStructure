package tree;

/**
 * 线索化二叉树
 * 线索化后的二叉树不能再使用之前的遍历方式
 * 以中序遍历为例，要修改只需修改左右子树处理的位置即可
 * 前序：根左右
 * 中序：左根右
 * 后序：左右根
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        MyNode root = new MyNode(1, "tom");
        MyNode node2 = new MyNode(3, "jack");
        MyNode node3 = new MyNode(6, "tom");
        MyNode node4 = new MyNode(8, "mary");
        MyNode node5 = new MyNode(10, "king");
        MyNode node6 = new MyNode(14, "dim");

        //手动创建
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //线索化
        tree.threadedNodes(root);
        System.out.println("使用中序线索化遍历");
        tree.threadedList();
        //MyNode left = node5.getLeft();
        //MyNode right = node5.getRight();
        //System.out.println(left);
        //System.out.println(right);
    }
}
class ThreadedBinaryTree{
    //根节点
    private MyNode root;
    //为了实现线索化，需要创建指向当前结点的前驱结点的指针
    //在递归进行线索化时，pre总是保留前一个结点
    private MyNode pre = null;

    public void setRoot(MyNode root) {
        this.root = root;
    }

    //中序遍历线索化二叉树
    public void threadedList(){
        //定义一个变量，存储当前遍历的结点，从root开始
        MyNode node = root;
        while (node != null){
            //循环的找到第一个leftType == 1的结点，就是中序遍历的第一个结点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前的结点
            System.out.println(node);
            //如果当前结点的右指针指向的是后继结点，就一直输出
            while (node.getRightType() == 1){
                //获取到当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            //替换遍历的结点
            node = node.getRight();
        }

    }
    /**
     * 对二叉树进行中序线索化
     * @param node 当前需要线索化的结点
     */
    public void threadedNodes(MyNode node){
        if (node == null){//node为空，不能线索化
            return;
        }
        //线索化左子树
        threadedNodes(node.getLeft());
        //线索化当前结点
        //处理当前结点的前驱结点
        if (node.getLeft() == null){
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针类型，指向前驱结点
            node.setLeftType(1);
        }
        //处理后继结点
        if (pre != null && pre.getRight() == null){
            //让pre结点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针属性
            pre.setRightType(1);
        }
        //每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;
        //线索化右子树
        threadedNodes(node.getRight());
    }
    //前序遍历
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }
    //中序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }
    //后续遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }
    //前序遍历查找
    public MyNode preOrderSearch(int no){
        if (root != null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    //中序遍历查找
    public MyNode infixOrderSearch(int no){
        if (root != null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序遍历查找
    public MyNode postOrderSearch(int no){
        if (root != null){
            return root.postOrderSearch(no);
        }else {
            return null;
        }
    }
    //删除结点
    public void delNode(int no){
        if (root != null){
            if (root.getNo() == no){
                root = null;
            }else {
                root.delNode(no);
            }
        }else {
            System.out.println("空树，不能删除");
        }
    }
}
class MyNode {

    private int no;
    private String name;
    private MyNode left;//默认为null
    private MyNode right;//默认为null

    //如果leftType == 0表示指向的是左子树，如果1则表示指向前驱结点
    //如果rightType == 0表示指向的是右子树，如果1则表示指向后继结点
    private int leftType;
    private int rightType;


    public MyNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyNode getLeft() {
        return left;
    }

    public void setLeft(MyNode left) {
        this.left = left;
    }

    public MyNode getRight() {
        return right;
    }

    public void setRight(MyNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
    //前序遍历
    public void preOrder(){
        System.out.println(this);//先输出父结点
        //递归向左子树
        if (this.left != null){
            this.left.preOrder();
        }
        //递归向右子树
        if (this.right != null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder(){
        //递归向左子树
        if (this.left != null){
            this.left.infixOrder();
        }
        //输出当前结点
        System.out.println(this);
        //递归向右子树
        if (this.right != null){
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        //递归向左子树
        if (this.left != null){
            this.left.postOrder();
        }
        //递归向右子树
        if (this.right != null){
            this.right.postOrder();
        }
        //输出当前结点
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     * @param no 待查找结点的编号
     * @return 返回查找到的结点
     */
    public MyNode preOrderSearch(int no){
        //判断当前结点是否是要查找的结点
        if (this.no == no){
            return this;
        }
        //判断当前子结点的左结点是否为空，如果不为空则递归前序查找
        MyNode resNode = null;
        if (this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null){//说明找到了
            return resNode;
        }
        //判断当前结点的右子结点是否为空
        if (this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序遍历查找
     * @param no
     * @return
     */
    public MyNode infixOrderSearch(int no){
        MyNode resNode = null;
        if (this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.no == no){
            return this;
        }
        if (this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后续遍历查找
     * @param no
     * @return
     */
    public MyNode postOrderSearch(int no){
        MyNode resNode = null;
        if (this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.no == no){
            return this;
        }
        return resNode;
    }

    /**
     * 递归删除结点
     * 如果删除的结点是叶子结点，则删除该结点
     * 如果删除的结点是非叶子结点，则删除该子树
     * @param no
     */
    public void delNode(int no){
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        //向左子树进行递归删除
        if (this.left != null ){
            this.left.delNode(no);
        }
        //向右子树进行递归删除
        if (this.right != null){
            this.right.delNode(no);
        }
    }
}