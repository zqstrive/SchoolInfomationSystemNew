package operatesystem;

import valueobject.Staff;

import java.util.Scanner;

public class StaffInfoSystem {
    private Staff[] staffArray = new Staff[10];
    //由总人数暂定不超过10人，定义count计数总人数
    public static int count = 0;
    Scanner scanner = new Scanner(System.in);
    //绑定父菜单
    private InfoSystem is;
    public StaffInfoSystem(InfoSystem is){
        this.is = is;
    }
    //工人菜单界面
    public void show(){
        System.out.println("工人信息管理");
        System.out.println("       1、增加工人信息");
        System.out.println("       2、列出全部工人信息");
        System.out.println("       3、查询工人信息");
        System.out.println("       4、删除工人信息");
        System.out.println("       5、修改工人信息");
        System.out.println("       6、返回上一级菜单");
        while(true){
            int i = scanner.nextInt();
            switch (i){
                case 1 :{
                    addStaInfo();
                    break;
                }
                case 2 :{
                    showStaDisplay();
                    break;
                }
                case 3 :{
                    searchStaInfo();
                    break;
                }
                case 4 :{
                    deleteStaInfo();
                    break;
                }
                case 5 :{
                    modifyStaInfo();
                    break;
                }
                case 6:{
                    returnInfoMenu();
                    break;
                }
                default:{
                    System.out.println("未知错误");
                    break;
                }
            }
        }
    }
    //返回工人菜单界面
    public void returnStaInfo(){
        Scanner scan = new Scanner(System.in);
        System.out.println("返回上一级菜单？（1/0）");
        int x = scan.nextInt();
        if(x==1) show();
        if(x==0)
        {
            scan.close();
            System.exit(0);
        }
    }
    //增加工人
    public void addStaInfo(){
        long id;
        String name;
        int age ;
        double salary;
        String jobs;
        System.out.println("输入工人id:");
        id = scanner.nextLong();
        System.out.println("输入工人姓名:");
        name = scanner.next();
        System.out.println("输入工人年龄:");
        age = scanner.nextInt();
        System.out.println("输入工人薪水:");
        salary = scanner.nextDouble();
        System.out.println("输入工人工作:");
        jobs = scanner.next();
        Staff newSta = new Staff(id,name,age,salary,jobs);
        //判断当前工人数量是否为0
        if(staffArray[0]==null){
            staffArray[0] = newSta;
            count++;
            System.out.println("添加成功");
        }else{
            for (int i = 0;i<count;i++){
                if(newSta.getId()!=staffArray[i].getId()){
                    staffArray[count++] = newSta;
                    System.out.println("增加成功");
                    break;
                }else {
                    System.out.println("你输入的学生id已存在，检查无误后再次提交");
                }
            }
        }
        returnStaInfo();
    }
    //遍历打印所有工人信息
    public void showStaDisplay(){
        for(int i = 0;i<count;i++){
            System.out.println(staffArray[i].toString());
        }
        returnStaInfo();
    }
    //按照id查询工人
    public void searchStaInfo(){
        boolean f = false;
        System.out.println("输入工人的id:");
        long id = scanner.nextLong();
        for(int i =0;i<count;i++){
            if(id==staffArray[i].getId()){
                System.out.println(staffArray[i].toString());
                f =true;
            }
        }
        if(!f){
            System.out.println("查无此人");
        }
        returnStaInfo();
    }
    //按照id删除工人
    public void deleteStaInfo(){
        System.out.println("输入要删除工人的id");
        long id = scanner.nextLong();
        Staff[] tempSta = new Staff[10];
        for(int i=0;i<count;i++){
            if(staffArray[i].getId()==id){
                //将删除的工人置null
                staffArray[i] = null;
                count--;
            }
        }
        //将存在的工人转移到新数组中
        for(int j = 0,x=0;j<count;j++){
            if(staffArray[j]!=null)
            {
                tempSta[x++] = staffArray[j];
            }
            count = x;
        }
        staffArray = tempSta;
        returnStaInfo();
    }
    //按照id修改工人信息
    public void modifyStaInfo(){
        System.out.println("请输入要修改工人的id");
        long id = scanner.nextLong();
        int y =0;
        for(int i = 0;i<count;i++){
            if(id == staffArray[i].getId()){
                y = i;
            }
        }
        System.out.println("请输入要修改的信息:");
        boolean flag = true;
        while(flag){
            System.out.println("1、工人姓名");
            System.out.println("2、工人年龄");
            System.out.println("3、工人薪水");
            System.out.println("4、工人工作");
            System.out.println("5、修改完成");
            int x = scanner.nextInt();
            switch (x){
                case 1 :{
                    System.out.println("输入要修改的姓名");
                    String newName = scanner.next();
                    staffArray[y].setName(newName);
                    break;
                }
                case 2 :{
                    System.out.println("输入要修改的年龄");
                    int newAge = scanner.nextInt();
                    staffArray[y].setAge(newAge);
                    break;
                }
                case 3 :{
                    System.out.println("输入要修改的薪水");
                    double newSalary = scanner.nextDouble();
                    staffArray[y].setSalary(newSalary);
                    break;
                }
                case 4 :{
                    System.out.println("输入要修改的工作");
                    String newJobs = scanner.next();
                    staffArray[y].setJobs(newJobs);
                    break;
                }
                case 5 :{
                    flag = false;
                }
            }
        }
        System.out.println("学生信息修改完成");
        returnStaInfo();
    }
    //返回父菜单
    public void returnInfoMenu(){
        is.show();
    }
}
