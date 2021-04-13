package linkedList;

/**
 * 单向循环链表（环形单链表）
 * 解决约瑟夫问题
 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.show();
        circleSingleLinkedList.countBoy(1,2,5);//2->4->1->5->3
    }
}
//创建单向循环链表类
class CircleSingleLinkedList{
    //创建first结点,当前没有编号
    private Boy first = new Boy(-1);


    /**
     * 根据用户的输入，计算小孩出圈的顺序
     * @param startNo 表示从第几个开始报数
     * @param countNum 表示数几下然后让人出圈
     * @param nums 表示最初圈里有多少人
     */
    public void countBoy(int startNo,int countNum,int nums){
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建辅助指针
        Boy helper = first;
        //这个while循环是为了让helper指针指向队尾
        while (true){
            if (helper.next == first){//说明到最后结点
                break;
            }
            helper = helper.next;
        }
        //报数前，先让first和helper移动k-1次，保障从要开始的地方开始
        for (int i = 0; i < startNo - 1; i++) {
            first = first.next;
            helper = helper.next;
        }
        //让first和helper同时移动m-1次，然后出圈
        while (true){
            if (helper == first){//说明圈内只剩下一个节点了
                break;
            }
            //让first和helper移动countNum - 1
            for (int i = 0; i < countNum - 1; i++) {
                first = first.next;
                helper = helper.next;
            }
            //这时first所指的就是要出圈的节点
            System.out.printf("小孩%d出圈\n",first.no);
            //将其出圈
            first = first.next;
            helper.setNext(first);
        }
        System.out.printf("最后留在圈里小孩编号为%d",first.no);

    }


    /**
     * 遍历当前的环形链表
     */
    public void show(){
        if (first == null){
            //判断链表是否为空
            System.out.println("链表为空");
            return;
        }
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号%d\n",curBoy.getNo());
            if (curBoy.next == first){//说明已经遍历完毕
                break;
            }
            curBoy = curBoy.next;//curBoy后移
        }

    }

    /**
     * 添加结点，构成环形链表
     * @param nums 要添加的节点个数
     */
    public void addBoy(int nums){
        //对nums做数据校验
        if (nums < 1){
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针
        //使用for来创建我们的环形链表
        for (int i = 1; i <= nums ; i++) {
            //根据编号，创建小孩结点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1){
                first = boy;
                first.setNext(first);//构成环状
                curBoy = first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;//后移
            }
        }
    }
}

//创建结点类Boy
class Boy{
    public int no;
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
