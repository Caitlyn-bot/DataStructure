package linkedList;

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
        //singleLinkedList.add(hero1);
        //singleLinkedList.add(hero2);
        //singleLinkedList.add(hero3);
        //singleLinkedList.add(hero4);

        //乱序插入，看是否正序
        //将1重复添加，查看是否有提示信息
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero1);

        //显示链表
        singleLinkedList.list();

        //测试修改节点
        //HeroNode newHeroNode = new HeroNode(1, "宋公明", "及时雨");
        //singleLinkedList.update(newHeroNode);
        //System.out.println("修改后的结果");

        //测试删除节点
        singleLinkedList.delete(1);
        System.out.println("链表删除后的结果");


        //显示链表
        singleLinkedList.list();
    }
}
//定义SingleLinkedList来管理我们的HeroNode
class SingleLinkedList{
    //先初始化一个头结点，头结点不要动,不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");


    /**
     * 添加节点到单向链表
     * 找到当前链表的最后节点
     * 将这个节点的next指向新的节点
     * @param heroNode
     */
    public void add(HeroNode heroNode){
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
                ", nickname='" + nickname +
                '}';
    }
}
