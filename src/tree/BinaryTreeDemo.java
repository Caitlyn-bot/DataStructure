package tree;

/**
 * 二叉树的简单实现
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的结点
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"卢俊义");
        HeroNode node3 = new HeroNode(3,"吴用");
        HeroNode node4 = new HeroNode(4,"公孙胜");
        HeroNode node5 = new HeroNode(5,"林冲");

        //手动创建二叉树
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);

        System.out.println("删除前");
        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();


        binaryTree.delNode(5);

        System.out.println("删除后");
        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();
    }
}
//定义BinaryTree
class BinaryTree{
    //根节点
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
    public HeroNode preOrderSearch(int no){
        if (root != null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        if (root != null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序遍历查找
    public HeroNode postOrderSearch(int no){
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
//先创建HeroNode节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;//默认为null
    private HeroNode right;//默认为null

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
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
    public HeroNode preOrderSearch(int no){
        //判断当前结点是否是要查找的结点
        if (this.no == no){
            return this;
        }
        //判断当前子结点的左结点是否为空，如果不为空则递归前序查找
        HeroNode resNode = null;
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
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode = null;
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
    public HeroNode postOrderSearch(int no){
        HeroNode resNode = null;
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