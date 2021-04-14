package stack;

/**
 * 仅支持加减乘除，未考虑小括号
 * 使用栈完成表达式计算的思路
 * 1.通过一个index值(索引)，来遍历我们的表达式
 * 2.如果我们发现是一个数字，就直接入数栈
 * 3.如果发现扫描到是一个符号，就分如下情况
 * 3.1如果当前的符号栈为空，就直接入符号栈
 * 3.2如果发现符号栈有操作符，就进行比较
 * 如果当前操作符的优先级小于或者等于栈中的操作符
 * ，就需要从数栈中pop出两个数，进行运算
 * 将得到的结果，入数栈
 * 然后将当前的操作符入符号栈
 * 如果当前操作符的优先级大于栈中的操作符，就直接入符号栈
 * 4.当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
 * 5.最后再数栈只有一个数字，就是表达式的结果
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "30+2*6-2";
        //创建两个栈，一个数栈，一个符号栈
        CalArrayStack numStack = new CalArrayStack(10);
        CalArrayStack operStack = new CalArrayStack(10);
        //定义index索引帮助扫描
        int index = 0;
        int num1 = 0 ;
        int num2 = 0 ;
        char oper = 0;
        int res = 0;
        char ch =' ';//将每次扫描得到的char保存到ch
        //定义一个字符串用于拼接多位数keepNum
        String keepNum = "";

        //扫描expression
        while (true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，进行相应的处理
            if (operStack.isOper(ch)){//是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()){//不为空

                        //如果发现符号栈有操作符，就进行比较
                    //如果当前操作符的优先级小于或者等于栈中的操作符
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        //就需要从数栈中pop出两个数，进行运算
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = (char) operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        //将得到的结果，入数栈
                        numStack.push(res);
                        //然后将当前的操作符入符号栈
                        operStack.push(ch);
                    }else {//如果当前操作符的优先级大于栈中的操作符
                        //直接入栈
                        operStack.push(ch);
                    }
                }else {
                    //如果为空，直接入栈
                    operStack.push(ch);
                }
            }else {//如果是数，直接入栈
                //numStack.push(ch - 48);//因为现在是字符，而不是数

                /**
                 * 当发现是数时，不能直接入栈，因为可能是多位数
                 * 应该向后再看一位，如果是数就进行扫描
                 * 如果是符号就让数入栈
                 */
                //定义一个字符串用于拼接多位数keepNum
                keepNum += ch;
                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    //这里不进行处理就是继续扫描！！！！
                    //注意是看最后一位，不是index++
                    if (operStack.isOper(expression.substring(index + 1,index + 2).charAt(0))){
                        //如果后一位是运算符，则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //将keepNum置为空
                        keepNum = "";
                    }
                }

            }
            //让index加一，并判断expression是否扫描到最后
            index++;
            if (index == expression.length()){//扫描完毕
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
        while(true){
            //如果符号栈为空，则计算到了最后的结果，数栈中的唯一一个数就是结果
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = (char) operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        //将数栈最后的数pop出，就是结果
        int result = numStack.pop();
        System.out.printf("表达式%s = %d",expression,result);


    }
}
//定义一个ArrayStack表示栈结构
class CalArrayStack{
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据存在该数组
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public CalArrayStack(int maxSize){
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
    //返回运算符的优先级，由自己进行定义
    //数字越大，优先级就越高
    public int priority(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        }else if (oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;//假定目前表达式只有+,-,*,/
        }
    }
    //判断是不是一个运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    //计算方法
    //int和char可以混用
    public int cal(int num1,int num2 , char oper){
        int res = 0;//res用于存放计算的结果
        //这里要注意顺序，减法和除法的顺序会影响结果
        //是1-2还是2-1
        switch (oper){
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    //查看当前栈顶的值
    public int peek() {
        return stack[top];
    }
}
