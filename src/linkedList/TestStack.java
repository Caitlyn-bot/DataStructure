package linkedList;

import java.util.Stack;

/**
 * 演示栈的使用
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack();
        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");
        while (stack.size() > 0){
            System.out.println(stack.pop());//pop方法就是将栈顶的数据取出
        }

    }

}
