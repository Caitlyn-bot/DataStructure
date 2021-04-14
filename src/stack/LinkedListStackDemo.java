package stack;

import java.util.Scanner;

/**
 * 链表模拟栈
 */
public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();
        String key = "";
        boolean loop = true;//判断是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("show:显示栈");
            System.out.println("exit:退出栈");
            System.out.println("push:添加数据进栈");
            System.out.println("pop:栈顶数据出栈");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "exit":
                    //记得关闭scanner
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println("出栈的数据为：" + res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
//定义LinkedListStack来模拟栈
class LinkedListStack{
    //定义头结点
    LinkedNode head = new LinkedNode(-1);

    //判断栈是否为空
    public boolean isEmpty(){
        return head.next == null;
    }
    //向栈内插入数据push
    public void push(int value){
        LinkedNode node = new LinkedNode(value);
        if (isEmpty()){
            head.next = node;
            return;
        }
        node.next = head.next;
        head.next = node;
    }
    //从栈顶取出数据pop
    public int pop(){
        if (isEmpty()){
            throw  new RuntimeException("栈为空");
        }
        int value = head.next.data;
        head.next = head.next.next;
        return value;
    }
    //遍历栈
    public void list(){
        if (isEmpty()){
            System.out.println("栈为空");
            return;
        }
        LinkedNode temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            System.out.println(temp.next.data);
            temp = temp.next;
        }
    }
}
//定义链表节点类
class LinkedNode{
    public int data;
    public LinkedNode next;

    public LinkedNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public LinkedNode getNext() {
        return next;
    }

    public void setNext(LinkedNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "LinkedNode{" +
                "data=" + data +
                '}';
    }
}