package recursion;

public class RecursionTest {
    public static void main(String[] args) {
        //通过打印问题，回顾递归机制
        test(4);
        //阶乘问题
        int res = factorial(8);
        System.out.println("res = " + res);
    }
    public static void test(int n){
        if (n > 2){
            test(n - 1);
        }
        System.out.println("n = " + n);
    }
    //阶乘问题
    public static int factorial(int n){
        if (n == 1){
            return 1;
        }else {
            return n * factorial((n - 1));
        }
    }
}
