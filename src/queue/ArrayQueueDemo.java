package queue;
/**
 * 这个类是演示数组模拟队列
 * 存在问题：数组只能使用一次就无法继续使用
 */

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args){
        //创建一个队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key =' ';//接受用户输入‘
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
                    arrayQueue.showQueue();
                    break;
                case 'a'://添加数据
                    System.out.println("请输入要添加的数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
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

/**
 * 使用数组模拟队列，编写一个类ArrayQueue
 */
class ArrayQueue{
    private int maxSize;//表示数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数组用于存放数据，模拟队列

    /**
     * 创建队列的构造器
     * @param arrMaxSize
     */
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部，front是指向队列头的前一个位置
        rear = -1;//指向队列尾部，rear指向队列尾的数据，也就是队列的最后一个数据
    }

    /**
     * 判断队列是否满
     * @return
     */
    public boolean isFull(){
        return rear ==maxSize - 1;
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
        rear ++;//让rear后移
        arr[rear] = n;
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
        front++;//front后移
        return arr[front];
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
        for (int i = front + 1; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
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
