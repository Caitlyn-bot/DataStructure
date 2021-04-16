package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * 使用栈实现逆波兰表达式(后缀表达式)的计算
 */
public class PolandNotation {
    public static void main(String[] args) {
        //将一个中缀表达式转为后缀表达式
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpression = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list：" + infixExpression);
        List<String> strings = parseSuffixExpressionList(infixExpression);
        System.out.println("中缀表达式转成的后缀表达式：" + strings);
        int calculate = calculate(strings);
        System.out.println("运算结果： " + calculate);

        //先定义一个逆波兰表达式
        //(3+4)*5 - 6
        //为了方便，逆波兰式的数字和符号用空格隔开
        //String suffixExpression = "3 4 + 5 * 6 - ";
        //先将suffixExpression存放到ArrayList中
        //将ArrayList传给一个方法，遍历ArrayList配合栈完成计算
        //List<String> rpnList = getListString(suffixExpression);
        //System.out.println("rpnList = " + rpnList);
        //int res = calculate(rpnList);
        //System.out.println("res = " + res);

    }

    /**
     * 将中缀表达式转为对应的List
     * @param s
     * @return
     */
    public static List<String> toInfixExpressionList(String s){
        //定义一个List，存放中缀表达式的内容
        List<String> ls = new ArrayList<>();
        int i = 0;//这是一个指针用于遍历中缀表达式字符串
        String str;//对多位数的拼接
        char c ;//每遍历到一个字符，就放入到c中
        do{

            if (( c = s.charAt(i)) < 48 || ( c = s.charAt(i)) > 57){//c为非数字
                ls.add(String.valueOf(c));
                i++;
            }else {//c为数字，考虑多位数的问题
                str = "";//将str置为空
                while (i < s.length() && ( c = s.charAt(i)) >= 48 && ( c = s.charAt(i)) <= 57){
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        }while (i < s.length());
        return ls;
    }

    /**
     * 将得到的中缀表达式List转为后缀表达式的List
     * @param infixList 传入中缀表达式的List
     * @return 返回对应的后缀表达式的List
     */
    public static List<String> parseSuffixExpressionList(List<String> infixList){
        //定义两个栈
        Stack<String> s1 = new Stack<String>();//符号栈
        //s2这个栈，在整个转换过程中，没有pop操作，而且我们要进行逆序输出
        //可以选择使用List<String>来替代Stack<String>
        List<String> s2 = new ArrayList<String>();//储存中间结果的List2
        //遍历ls
        for (String item: infixList){
            //如果是一个数，加入s2
            if (item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")){
                s1.push(item);
            }else if (item.equals(")")){
                //如果是右括号")"，则一次弹出s1栈顶的运算符
                //并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将（左括号弹出
            }else {
                //当item的优先级小于等于s1栈顶运算符，将s1栈顶的运算符弹出并加入到s2中
                //再次与s1中新的栈顶运算符相比较
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }


        }
        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;//因为是存放到list中，因此按顺序输出就是对应的逆波兰式
    }

    /**
     * 将一个逆波兰表达式，依次将数据和运算符放到ArrayList中
     * @param suffixExpression
     * @return
     */
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele:split){
            list.add(ele);
        }
        return list;
    }

    /**
     * 完成对逆波兰表达式的运算
     * 从左至右扫描，将3和4压入堆栈
     * 遇到+运算符，因此弹出4和3，计算3+4的值，得7，将7入栈
     * 将5入栈
     * 遇到*，弹出5和7，计算5*7，得35，将35入栈
     * 将6入栈
     * 遇到-，弹出6和35，计算35-6，得29，即计算结果
     * @param ls
     * @return
     */
    public static int calculate(List<String> ls){
        //创建栈，只需要一个栈，不像之前需要符号栈和数栈
        Stack<String> stack = new Stack<String>();
        //遍历ls
        for (String item: ls){
            //使用正则表达式取出数
            if (item.matches("\\d+")){//匹配的是多位数
                //入栈
                stack.push(item);
            }else {//是运算符
                //从栈中pop出两个数并运算，结果再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                //判断当前的运算符
                if (item.equals("+")){
                    res = num1 + num2;
                }else if (item.equals("-")){
                    res = num1 - num2;
                }else if (item.equals("*")){
                    res = num1 * num2;
                }else if (item.equals("/")){
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                //将res入栈
                stack.push(String.valueOf(res));
            }
        }
        //最后留在栈中数就是结果
        return Integer.parseInt(stack.pop());
    }

}
//编写一个类Operation，可以返回一个运算符对应的优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    /**
     * 写一个方法，返回对应的优先级数字
     * @param operation
     * @return
     */
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}
