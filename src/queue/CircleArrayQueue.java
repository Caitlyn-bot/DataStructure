package queue;

import java.util.Scanner;

/**
 * 使用数组模拟环形队列
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        //创建一个环形队列
        //队列的实际有效长度比arrMaxSize少1,3-1=2，因为要留一个空间作为约定
        CircleArray circleArray = new CircleArray(3);
        char key =' ';//接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出菜单
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            System.out.println("e(exit):退出程序");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's'://查看队列
                    circleArray.showQueue();
                    break;
                case 'a'://添加数据
                    System.out.println("请输入要添加的数");
                    int value = scanner.nextInt();
                    circleArray.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = circleArray.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = circleArray.headQueue();
                        System.out.printf("队列的头数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    //关闭scanner
                    scanner.close();
                    //结束循环
                    loop = false;
                    break;
                default:
                    break;
            }
        }

    }
}
class CircleArray{
    private int maxSize;//表示队列（数组）的最大容量
    private int front;//指向队列的第一个元素
    private int rear;//指向队列最后一个元素的后一个位置
    private int[] arr;//该数组用于存放数据，模拟队列

    /**
     * 创建环形队列的构造器
     * @param arrayMaxSize
     */
    public CircleArray(int arrayMaxSize){
        maxSize = arrayMaxSize;
        //front和rear默认是零，这里可以不写
        front = 0;
        rear = 0;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否满
     * @return
     */
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }
    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return rear == front;
    }
    /**
     * 添加数据到队列
     */
    public void addQueue(int n){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;//将rear后移，必须考虑取模
    }

    /**
     * 获取队列的数据，出队列
     * @return
     */
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("队列为空，无法取出数据");
        }
        //将当前的front对应的值进行保存
        int value = arr[front];
        front = (front + 1) % maxSize;//front后移
        return value;
    }


    /**
     * 显示队列的所有数据
     */
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空，没有数据");
            return ;
        }
        //注意这里要从队列头开始遍历
        //队列中有效数据的个数
        for (int i = front ; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    /**
     * 求出队列中有效数据的个数
     * @return
     */
    public int size(){
        //rear = 2
        //front = 1
        //maxSize = 3
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 显示队列的头数据，不是取出数据
     * @return
     */
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        //这里返回的是front+1，但front本身的值并没有改变，所以没有出队列
        return arr[front+1];
    }
}
