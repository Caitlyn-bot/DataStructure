package stack;

import java.util.Scanner;

/**
 * 数组模拟栈
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
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
//定义一个ArrayStack表示栈结构
class ArrayStack{
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据存在该数组
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }
    //判断栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }
    //判断栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈push
    public void push(int value){
        //栈是否满
        if (isFull()){
            System.out.println("栈满，无法入栈");
            return;
        }
        top ++;
        stack[top] = value;
    }
    //出栈pop,将栈顶的数据返回
    public int pop(){
        //栈是否为空
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top -- ;
        return value;
    }
    //显示栈的情况，遍历,需要从栈顶开始显示
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        //需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
