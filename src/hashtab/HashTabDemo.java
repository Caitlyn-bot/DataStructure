package hashtab;

import java.util.Scanner;

/**
 * 使用哈希表来管理多条无表头的链表
 */
public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        Scanner scanner = new Scanner(System.in);
        String key ="";
        boolean flag = true;
        while (flag){
            System.out.println("add:添加雇员");
            System.out.println("add2:按顺序添加雇员");
            System.out.println("del:删除雇员");
            System.out.println("update:修改雇员");
            System.out.println("find:查找雇员");
            System.out.println("list:显示雇员");
            System.out.println("exit:退出系统");
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case "add2":
                    System.out.println("输入id");
                    id = scanner.nextInt();
                    System.out.println("输入名字");
                    name = scanner.next();
                    emp = new Emp(id,name);
                    hashTab.add2(emp);
                    break;
                case "del":
                    System.out.println("请输入删除的id");
                    id = scanner.nextInt();
                    hashTab.del(id);
                    break;
                case "update":
                    System.out.println("输入待修改雇员的id");
                    id = scanner.nextInt();
                    System.out.println("输入修改后的名字");
                    name = scanner.next();
                    hashTab.update(id,name);
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    int findId = scanner.nextInt();
                    hashTab.findEmpById(findId);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    scanner.next();
                    flag = false;
                    break;
                default:
                    break;
            }
        }

    }
}

//创建HashTab管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArr;
    private int size;
    /**
     * 构造器
     * @param size  链表的条数
     */
    public HashTab(int size){
        this.size = size;
        //初始化empLinkedListArr
        empLinkedListArr = new EmpLinkedList[size];
        //不要忘记分别初始化每条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArr[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工的id，判断应该存放到哪一条链表中去
        int empLinkedListNo = hashFun(emp.id);
        //将emp添加到链表
        empLinkedListArr[empLinkedListNo].add(emp);
    }
    //按照id顺序添加雇员
    public void add2(Emp emp){
        //根据员工的id，判断应该存放到哪一条链表中去
        int empLinkedListNo = hashFun(emp.id);
        //将emp添加到链表
        empLinkedListArr[empLinkedListNo].add2(emp);
    }
    //根据id删除雇员
    public void del(int id){
        int empLinkedListNo = hashFun(id);
        empLinkedListArr[empLinkedListNo].del(id);
    }

    //根据id修改雇员姓名
    public void update(int id,String name){
        int empLinkedListNo = hashFun(id);
        empLinkedListArr[empLinkedListNo].update(id,name);
    }

    //遍历哈希表
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArr[i].list(i);
        }
    }

    //根据输入的id查找雇员
    public void findEmpById(int id){
        //使用散列确定在哪条链表里查找
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArr[empLinkedListNo].findEmpById(id);
        if (emp != null){
            System.out.println("在第"+ (empLinkedListNo + 1) + "中找到雇员 id =" + emp.id + "name=" + emp.name);
        }else {
            System.out.println("在哈希表中未找到该雇员");
        }
    }

    //编写散列函数,使用简单的取模法
    //一般按照业务需求进行散列，比如按照部门进行划分
    public int hashFun(int id){
        return id % size;
    }

}

//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;//next默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList表示链表
class EmpLinkedList{
    //头指针，执行第一个Emp，因此我们这个链表的head是直接指向第一个Emp的
    private Emp head;//默认为空

    //添加员工到链表
    //假定添加雇员时，id是自增的，id总是从小到大
    //所以添加时直接加入到链表的尾部即可
    public void add(Emp emp){
        if (head == null){//添加第一个雇员
            head = emp;
            return;
        }
        //如果不是添加第一个，使用辅助指针，帮助遍历
        Emp curEmp = head;
        while (true){
            if (curEmp.next == null){//说明到链表最后
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    //乱序插入，顺序排列
    public void add2(Emp emp){
        if (head == null){//添加第一个雇员
            head = emp;
            return;
        }
        //如果不是添加第一个，使用辅助指针，帮助遍历
        Emp curEmp = head;
        if (emp.id < curEmp.id){
            emp.next = curEmp;
            System.out.println(emp.next);
            head = emp;
            return;
        }
        while (true){
            if (curEmp.next == null){//说明到链表最后
                break;
            }
            if (emp.id < curEmp.next.id){//找到要插入的位置
                break;
            }

            curEmp = curEmp.next;
        }
        emp.next = curEmp.next;
        curEmp.next = emp;
    }
    //根据id删除雇员
    public void del(int id){
        if (head == null){
            System.out.println("链表为空");
            return;
        }
        Emp curEmp = head;
        if (curEmp.id == id){
            head = curEmp.next;
            return;
        }
        while (true){
            if (curEmp.next.id == id){
                break;
            }
            if (curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        if (curEmp.next.next != null){
            curEmp.next = curEmp.next.next;
        }else {
            curEmp.next = null;
        }
    }
    //根据id修改雇员名称
    public void update(int id,String name){
        //判断链表是否为空
        if (head == null){
            System.out.println("链表为空");
            return ;
        }
        Emp curEmp = head;
        while (true){
            if (curEmp.id == id){//查找到
                break;
            }
            if (curEmp.next == null){//遍历完整个链表
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        if (curEmp == null){
            System.out.println("未找到待修改的雇员");
        }else {
            curEmp.name = name;
            System.out.println("修改成功");
        }
    }

    //遍历链表的雇员信息
    public void list(int no){
        if (head == null){
            System.out.println("第"+(no + 1)+"条链表为空");
            return;
        }
        System.out.println("第"+(no + 1)+"条链表的信息为：");
        Emp curEmp = head;
        while (true){
            System.out.printf("=>id=%d name=%s\t",curEmp.id,curEmp.name);
            if (curEmp.next == null){//说明当前已经是最后结点
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();//换行
    }

    //根据id查找雇员信息
    public Emp findEmpById(int id){
        //判断链表是否为空
        if (head == null){
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (true){
            if (curEmp.id == id){//查找到
                break;
            }
            if (curEmp.next == null){//遍历完整个链表
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

}