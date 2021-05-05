package tree;

public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr ={4,3,6,5,7,8};
        AVLTree avlTree = new AVLTree();
        //添加结点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new AVLNode(arr[i]));
        }
        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("树的高度" + avlTree.getRoot().height());
        System.out.println("左子树的高度" + avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度" + avlTree.getRoot().rightHeight());
        System.out.println("当前的根结点：" + avlTree.getRoot());
    }
}
//创建AVLTree
class AVLTree{
    private AVLNode root;
    //查找要删除的结点
    public AVLNode search(int value){
        if (root == null){
            return null;
        }else {
            return root.search(value);
        }
    }
    //查找父结点
    public AVLNode searchParent(int value){
        if (root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    /**
     *  删除AVLNode为根结点的二叉排序树的最小结点
     *  返回以AVLNode为根结点的二叉排序树的最小结点的值
     * @param AVLNode  当做一颗二叉排序树的根结点
     * @return  返回以AVLNode为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(AVLNode AVLNode){
        AVLNode target = AVLNode;
        //循环的查找左结点，找到最小值
        while (target.left != null){
            target = target.left;
        }
        //删除最小结点
        delAVLNode(target.value);
        return target.value;
    }
    //删除结点
    public void delAVLNode(int value){
        if (root == null){
            return;
        }else {
            AVLNode targetAVLNode = search(value);
            if (targetAVLNode == null){//没找到要删除的结点
                return;
            }
            if (root.left == null && root.right == null){
                root = null;//删除
                return;
            }
            //找到targetAVLNode的父结点
            AVLNode parentAVLNode = searchParent(value);
            if (targetAVLNode.left == null && targetAVLNode.right == null){//如果要删除的结点是叶子结点
                if (parentAVLNode.left != null && parentAVLNode.left.value == value){
                    parentAVLNode.left = null;
                }else if (parentAVLNode.right != null && parentAVLNode.right.value == value){
                    parentAVLNode.right = null;
                }
            }else if (targetAVLNode.left != null && targetAVLNode.right != null){//要删除的结点有两个子树
                //minValue是右子树中的最小结点值
                int minValue = delRightTreeMin(targetAVLNode.right);
                targetAVLNode.value = minValue;
            }else {//要删除的结点只有一颗子树
                if (targetAVLNode.left != null){//有左子树
                    if (parentAVLNode != null){
                        if ( parentAVLNode.left.value == value){
                            parentAVLNode.left = targetAVLNode.left;
                        }else {
                            parentAVLNode.right = targetAVLNode.left;
                        }
                    }else {
                        root = targetAVLNode.left;
                    }
                }else {//有右子树
                    if (parentAVLNode != null) {
                        if (parentAVLNode.left.value == value) {
                            parentAVLNode.left = targetAVLNode.right;
                        } else {
                            parentAVLNode.right = targetAVLNode.right;
                        }
                    }else {
                        root = targetAVLNode.right;
                    }
                }
            }
        }
    }
    //添加结点的方法
    public void add(AVLNode AVLNode){
        if (root == null){
            root = AVLNode;
        }else {
            root.add(AVLNode);
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

    public AVLNode getRoot() {
        return root;
    }

    public void setRoot(AVLNode root) {
        this.root = root;
    }
}
//创建AVLNode结点
class AVLNode{
    int value;
    AVLNode left;
    AVLNode right;

    public AVLNode(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight(){
        if (left == null){
            return 0;
        }else {
            return left.height();
        }
    }
    //返回右子树的高度
    public int rightHeight(){
        if (right == null){
            return 0;
        }else {
            return right.height();
        }
    }

    //返回当前结点的高度，以该结点为根结点的树的高度
    public int height(){
        //这里加一是算上根结点本身
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height() ) + 1;
    }

    //左旋转方法
    private void leftRotate(){
        //以当前根结点的值创建新的结点
        AVLNode newNode = new AVLNode(value);
        //把新的结点的左子树设置成当前结点的左子树
        newNode.left = left;
        //把新的结点的右子树设置成当前结点的右子树的左子树
        newNode.right = right.left;
        //把当前结点的值替换成右子结点的值
        value = right.value;
        //把当前结点的右子树设置成当前结点的右子树的右子树
        right = right.right;
        //把当前结点的左子结点设置成新的结点
        left = newNode;
    }

    //右旋转
    private void rightRotate() {
        AVLNode newNode = new AVLNode(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    /**
     * 查找要删除的结点
     * @param value 希望删除的结点的值
     * @return  如果找到返回该结点，否则返回null
     */
    public AVLNode search(int value){
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
    public AVLNode searchParent(int value){
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
        return "AVLNode{" +
                "value=" + value +
                '}';
    }

    //添加结点的方法
    //递归的形式添加结点，注意需要满足二叉排序树的要求
    public void add(AVLNode AVLNode){
        if (AVLNode == null){
            return;
        }
        //判断传入结点的值和当前结点的根结点的值的关系
        if (AVLNode.value < this.value){
            if (this.left == null){//如果当前结点左子结点为空
                this.left = AVLNode;
            }else {
                //递归的向左子树添加
                this.left.add(AVLNode);
            }
        }else {//添加结点的值大于等于当前结点的值
            if (this.right == null){
                this.right = AVLNode;
            }else {
                this.right.add(AVLNode);
            }
        }
        //当添加完一个结点后，如果: (右子树的高度-左子树的高度) > 1 , 左旋转
        if(rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if(right != null && right.leftHeight() > right.rightHeight()) {
                //先对右子结点进行右旋转
                right.rightRotate();
                //然后在对当前结点进行左旋转
                leftRotate(); //左旋转..
            } else {
                //直接进行左旋转即可
                leftRotate();
            }
            return ; //必须要!!!
        }

        //当添加完一个结点后，如果 (左子树的高度 - 右子树的高度) > 1, 右旋转
        if(leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树高度大于它的左子树的高度
            if(left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前结点的左结点(左子树)->左旋转
                left.leftRotate();
                //再对当前结点进行右旋转
                rightRotate();
            } else {
                //直接进行右旋转即可
                rightRotate();
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