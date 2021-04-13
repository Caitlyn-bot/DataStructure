package linkedList;

import java.util.Stack;

/**
 * 单链表的实现
 * 头结点不能动
 * 定义一个节点类
 * 定义链表类来管理节点
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "公孙胜", "入云龙");
        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //将节点加入链表
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        System.out.println("最初的链表");
        singleLinkedList.list();
        System.out.println("反转后的链表");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();

        //测试逆序打印
        System.out.println("进行逆序打印");
        reversePrint(singleLinkedList.getHead());

        System.out.println("测试两个有序单链表的合并");
        SingleLinkedList list1 = new SingleLinkedList();
        SingleLinkedList list2 = new SingleLinkedList();
        list1.add(hero1);
        list1.add(hero3);
        list1.add(hero4);
        list2.add(hero2);
        System.out.println("1ist1:");
        list1.list();
        System.out.println("list2:");
        list2.list();
        System.out.println("合并后：");
        HeroNode node = mergeList(list1.getHead(), list2.getHead());
        HeroNode temp = node.next;
        while (temp != null){
            System.out.println(temp.toString());
            temp = temp.next;
        }

        //乱序插入，看是否正序
        //将1重复添加，查看是否有提示信息
        //singleLinkedList.addByOrder(hero4);
        //singleLinkedList.addByOrder(hero2);
        //singleLinkedList.addByOrder(hero1);
        //singleLinkedList.addByOrder(hero3);
        //singleLinkedList.addByOrder(hero1);

        //显示链表
        //singleLinkedList.list();

        //测试修改节点
        //HeroNode newHeroNode = new HeroNode(1, "宋公明", "及时雨");
        //singleLinkedList.update(newHeroNode);
        //System.out.println("修改后的结果");

        //测试删除节点
        //singleLinkedList.delete(1);
        //System.out.println("链表删除后的结果");

        //显示链表
        //singleLinkedList.list();
        //System.out.println("有效的节点个数：" + getLength(singleLinkedList.getHead()));//3




        //获取倒数第k个节点
        //HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 1);
        //System.out.println("res = " + res);
    }

    /**
     * 合并两个有序的单链表，使之合并后依然有序
     *
     * @param head1
     * @param head2
     * @return
     */
    public static HeroNode mergeList(HeroNode head1,HeroNode head2){
        HeroNode newHead = new HeroNode(0,"","");
        if (head1.next == null && head2.next == null){
            return newHead;
        }
        //定义辅助节点
        HeroNode cur1 = head1.next;
        HeroNode cur2 = head2.next;
        HeroNode temp = newHead;
        HeroNode next = null;
        while (cur1 != null||cur2 != null){
            if (cur1 == null){
                if (cur2 == null){
                    break;
                }else {
                    //暂时保存cur2的next
                    next = cur2.next;
                    //将cur2的后面摘下，将cur2挂载newHead上
                    cur2.next = temp.next;
                    temp.next = cur2;
                    cur2 = next;
                    temp = temp.next;
                }
            }else {
                if (cur2 == null){
                    next = cur1.next;
                    cur1.next = temp.next;
                    temp.next = cur1;
                    cur1 = next;
                    temp = temp.next;
                }else {
                    if (cur1.no < cur2.no){
                        next = cur1.next;
                        cur1.next = temp.next;
                        temp.next = cur1;
                        cur1 = next;
                        temp = temp.next;
                    }else if (cur1.no == cur2.no){
                        next = cur1.next;
                        cur1.next = temp.next;
                        temp.next = cur1;
                        cur1 = next;
                        cur2 = cur2.next;
                        temp = temp.next;
                    }else{
                        next = cur2.next;
                        cur2.next = temp.next;
                        temp.next = cur2;
                        cur2 = next;
                        temp = temp.next;
                    }
                }
            }

        }
        return newHead;
    }

    /**
     * 利用栈这个数据结构实现链表逆序打印
     * 栈后进先出
     * 这样不会改变原本链表的顺序
     * @param head 传入要逆序打印的链表的头结点
     */
    public static void reversePrint(HeroNode head){
        if (head.next == null){
            //链表为空
            return;
        }
        //创建一个栈，将链表的各个结点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有结点压入栈中
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }

    }

    /**
     * 反转单链表
     * 思路
     * 将当前链表按顺序遍历，从前往后一个个取下节点，
     * 插入新的链表中，一直插到新的头结点的next中
     * 这样就实现了反转
     * 再让原来的头结点的next指向新的节点的next，完成反转
     * @param head 传入原本链表的头结点
     */
    public static void reverseList(HeroNode head){
        //如果当前链表为空或只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null){
            return;
        }
        //定义一个辅助的指针
        HeroNode cur = head.next;
        //next指向当前节点【cur】的下一个节点
        HeroNode next = null;
        //定义一个新的头结点
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表并取出放在新的链表的首端
        while (cur != null){
            next = cur.next;//保存当前节点的下一个节点
            cur.next = reverseHead.next;//将cur的后边接到新链表上
            reverseHead.next = cur;//将cur的前边连接到新的链表上
            cur = next;
        }
        //将head指向reverseHead
        head.next = reverseHead.next;

    }

    /**
     * 先得到链表的长度size = getLength
     * 从链表的第一个遍历(size - index)个，得到目标节点
     * @param head 接受要遍历的链表的头结点
     * @param index 接受要查找的是倒数第几个节点
     * @return 返回倒数第index个节点
     */
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        if (head.next == null){
            //说明链表为空
            return null;
        }
        //得到链表的长度
        int size = getLength(head);
        //遍历size - index
        //先判断index的合理性
        if (index <= 0 || index > size){
            return null;
        }
        //定义辅助变量
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }


    /**
     *获取到链表的节点的个数
     * 如果是带头节点的链表，不计算头结点
     * @param head 链表的头结点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head){
        if (head.next == null){
            //说明链表为空
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;//遍历
        }
        return length;
    }
}
//定义SingleLinkedList来管理我们的HeroNode
class SingleLinkedList{
    //先初始化一个头结点，头结点不要动,不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");


    /**
     *
     * @return 返回头结点
     */
    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单向链表
     * 找到当前链表的最后节点
     * 将这个节点的next指向新的节点
     * @param heroNode
     */
    public void add(HeroNode heroNode){
        //先将heroNode的后面置为空，避免出现使用过的链以前进行插入
        heroNode.next = null;
        //因为head节点不能动，因此我们需要一个辅助变量temp来帮助遍历
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true){
            //当temp的next为空，说明找到了链表最后
            if (temp.next == null){
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向最后一个节点
        //将最后一个节点的next指向新的节点
        temp.next = heroNode;
    }

    /**
     * 根据顺序将节点插入到指定位置
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode){
        //先将heroNode的后面置为空，避免出现使用过的链以前进行插入
        heroNode.next = null;
        //使用辅助变量temp找到添加的位置
        HeroNode temp = head;
        //单链表要找添加位置的前一个结点，否则无法插入，因为单链表只有后继

        //定义flag标识要添加的节点序号是否存在，默认不存在
        boolean flag = false;
        while (true){
            if (temp.next == null){
                //temp已经在链表的最后，只需要将该节点加在最后
                break;
            }
            if (temp.next.no > heroNode.no){
                //位置找到，就在temp的后面插入
                break;
            }else if (temp.next.no == heroNode.no){
                //说明当前节点已经存在
                flag = true;
                break;
            }else {
                //未找到，接着找
                temp = temp.next;
            }
        }
        if (flag){
            System.out.printf("要添加的节点编号%d已经存在,不能再重复添加\n",heroNode.no);
        }else {
            //先将temp原本的next值赋给新节点的next
            heroNode.next = temp.next;
            //再将temp的next设为新的节点
            temp.next = heroNode;
        }


    }

    /**
     * 修改节点的信息
     * 不修改编号
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //根据no找到要修改的节点

        //定义一个辅助变量temp
        //因为头节点没有数据，所有从head的next开始
        HeroNode temp = head.next;
        //flag判断是否找到该节点，默认未找到
        boolean flag = false;
        while (true){
            if (temp == null){
                //表示已经到了链表的最后
                break;
            }
            if (temp.no == newHeroNode.no){
                //找到要修改的节点
                flag = true;
                break;
            }
            //没找到，接着找下一个
            temp = temp.next;
        }

        if (flag){
            //找到
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else {
            System.out.printf("没有找到要修改的编号为%d的节点",newHeroNode.no);
        }

    }

    /**
     * 删除节点
     * 找到temp.next的no与所找no相同
     * temp.next = temp.next.next
     * @param no
     */
    public void delete(int no){
        //temp辅助查找
        HeroNode temp = head;
        //flag标志是否找到待删除节点
        boolean flag = false;
        while (true){
            if (temp.next == null){
                //已经遍历到链表最后
                break;
            }
            if (temp.next.no == no){
                //找到待删除节点的前一个节点
                flag = true;
                break;
            }
            //没找到接着找
            temp = temp.next;
        }
        if (flag){
            //进行删除
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的编号为%d的节点未找到",no);
        }
    }

    /**
     * 显示链表(遍历)
     */
    public void list(){
        //先判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，使用辅助变量temp来进行遍历
        //这里直接指向next，因为head节点没有数据，不需要打印
        HeroNode temp = head.next;
        while (true){
            //temp为null，说明链表完全遍历
            if (temp == null){
                break;
            }
            //输出节点的信息
            System.out.println(temp.toString());
            //将temp后移
            temp = temp.next;
        }
    }

}
//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点
    //构造器
    public HeroNode(int hNo,String hName,String hNickname){
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }
    //方便打印，重写toString()方法
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +'\''+
                '}';
    }
}
