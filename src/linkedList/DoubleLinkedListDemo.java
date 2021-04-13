package linkedList;

/**
 * 双向链表的实现
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        LinkNode h1 = new LinkNode(1,"宋江","及时雨");
        LinkNode h2 = new LinkNode(2,"卢俊义","玉麒麟");
        LinkNode h3 = new LinkNode(3,"吴用","智多星");
        LinkNode h4 = new LinkNode(4,"公孙胜","入云龙");
        DoubleLinkedList list = new DoubleLinkedList();
        DoubleLinkedList list1 = new DoubleLinkedList();
        //乱序填入，排序添加
        System.out.println("乱序输入，排序添加");
        list1.addByOrder(h4);
        list1.addByOrder(h1);
        list1.addByOrder(h3);
        list1.addByOrder(h2);
        list1.list();
        //按尾部添加
        System.out.println("正常添加");
        list.add(h1);
        list.add(h2);
        list.add(h3);
        list.add(h4);
        list.list();
        System.out.println("修改后");
        LinkNode h = new LinkNode(1,"宋江","呼保义");
        list.update(h);
        list.list();
        System.out.println("删除后");
        list.del(1);
        list.list();
    }
}
//创建一个双向链表的类
class DoubleLinkedList{
    //初始化一个头结点
    private LinkNode head = new LinkNode(0,"","");

    public LinkNode getHead() {
        return head;
    }

    /**
     * 删除结点
     * 对于双向链表，我们可以通过要删除的结点本身即可完成删除
     * temp.pre.next = temp.next;
     * temp.next.pre = temp.pre;
     * @param no
     */
    public void del(int no){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        LinkNode temp = head.next;
        while (true){
            if (temp == null){//遍历了一圈
                break;
            }
            if (temp.no == no){//找到，进行删除

                //这里要注意temp是最后一个节点的情况
                if (temp.next == null){
                    temp.pre.next = temp.next;
                }else {
                    temp.pre.next = temp.next;
                    temp.next.pre = temp.pre;
                }

                break;
            }
            temp = temp.next;
        }

    }

    /**
     * 修改节点的信息
     * 不修改编号
     * @param newLinkNode
     */
    public void update(LinkNode newLinkNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //根据no找到要修改的节点

        //定义一个辅助变量temp
        //因为头节点没有数据，所有从head的next开始
        LinkNode temp = head.next;
        //flag判断是否找到该节点，默认未找到
        boolean flag = false;
        while (true){
            if (temp == null){
                //表示已经到了链表的最后
                break;
            }
            if (temp.no == newLinkNode.no){
                //找到要修改的节点
                flag = true;
                break;
            }
            //没找到，接着找下一个
            temp = temp.next;
        }

        if (flag){
            //找到
            temp.name = newLinkNode.name;
            temp.nickname = newLinkNode.nickname;
        }else {
            System.out.printf("没有找到要修改的编号为%d的节点",newLinkNode.no);
        }

    }

    /**
     * 添加新的节点到链表尾部
     * @param linkNode 传入要添加的节点
     */
    public void add(LinkNode linkNode){
        linkNode.next = null;
        LinkNode temp = head;
        while (true){
            if (temp.next == null){//找到最后的位置
                break;
            }
            temp = temp.next;
        }
        temp.next = linkNode;
        linkNode.pre = temp;
    }

    /**
     *
     * @param linkNode
     */
    public void addByOrder(LinkNode linkNode){
        if (head.next == null){//链表为空
            head.next = linkNode;
            return;
        }
        LinkNode temp = head;
        while (true){
            if (temp.next == null){//遍历到最后
                temp.next = linkNode;
                linkNode.pre = temp;
                break;
            }
            if (linkNode.no < temp.next.no){
                    linkNode.pre = temp;
                    linkNode.next = temp.next;
                    temp.next.pre = linkNode;
                    temp.next = linkNode;
                    break;
            }else if (linkNode.no == temp.next.no){
                System.out.println("要添加的编号已经存在");
                break;
            }else {
                temp = temp.next;
            }
        }
    }

    /**
     * 遍历链表
     */
    public void list(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //定义辅助节点帮助遍历，原因：头结点不能动
        LinkNode temp = head.next;
        while (true){
            if (temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }

    }

}
//创建双向链表的节点类
class LinkNode{
    public int no;
    public String name;
    public String nickname;
    public LinkNode pre;//前驱，指向前一个节点
    public LinkNode next;//后继，指向后一个节点

    //构造器


    public LinkNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "LinkNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
