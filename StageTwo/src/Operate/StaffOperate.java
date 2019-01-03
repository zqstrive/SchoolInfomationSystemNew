package Operate;

import ValueObject.Staff;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class StaffOperate implements PersonOperate {
    //定义读取的文件路径
    File fileSta = new File("d:"+File.separator+"Staff.txt");
    Scanner scanner = new Scanner(System.in);
    //绑定文件操作类和主菜单
    private InfoOperate infoo;
    private FileOperate fo;
    ArrayList<Staff> staal;
    public StaffOperate(InfoOperate infoo,FileOperate fo) throws Exception {
        this.infoo = infoo;
        this.fo = fo;
        /*
            拆分时的操作方法
         */
        //staal =  fo.loadStaText(fileSta) ;

        //合并操作方法的加载工人信息方法
        staal = (ArrayList<Staff>) fo.loadText(fileSta);
    }
    public void show() throws Exception {

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
                    add();
                    break;
                }
                case 2 :{
                    findAll();
                    break;
                }
                case 3 :{
                    findByLike();
                    break;
                }
                case 4 :{
                    delete();
                    break;
                }
                case 5 :{
                    update();
                    break;
                }
                case 6 :{
                    returnMenu();
                    break;
                }
                default:{
                    System.out.println("未知错误");
                    break;
                }
            }
        }
    }
    public void returnLast() throws Exception {
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
    public void add() throws Exception {
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
        System.out.println("输入工人工作");
        jobs = scanner.next();
        Staff newSta = new Staff(id,name,age,salary,jobs);
        //若文本文档内容是否为空，则新建集合类并添加元素
        if(staal==null||staal.size()==0){
            staal = new ArrayList<>();
            staal.add(newSta);
            System.out.println("添加成功");
        }else {
            for(int i=0;i<staal.size();i++){
                //查重
                if(staal.get(i).getId()!=newSta.getId()){
                    staal.add(newSta);
                    System.out.println("添加成功");
                    break;
                }else {
                    System.out.println("你输入的工人id已存在,检查无误后再次提交");
                }
            }
        }
        //fo.saveStaText(staal,fileSta);
        fo.saveText(staal,fileSta);
        returnLast();
    }
    public void delete() throws Exception {
        System.out.println("请输入要删除的工人id");
        long id = scanner.nextLong();
        for(int i =0;i<staal.size();i++){
            if (staal.get(i).getId()==id){
                System.out.println("删除成功  " + staal.remove(i));
            }
        }
        //fo.saveStaText(staal,fileSta);

        //保存工人信息到文本文档中
        fo.saveText(staal,fileSta);
        returnLast();
    }
    public void update() throws Exception {
        System.out.println("请输入要查询的工人id");
        long id = scanner.nextLong();
        int x = 0;
        for(int i=0;i<staal.size();i++){
            if(staal.get(i).getId()==id){
                x = i;
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
            int j = scanner.nextInt();
            switch (j){
                case 1 :{
                    System.out.println("输入要修改的姓名");
                    String newName = scanner.next();
                    staal.get(x).setName(newName);
                    break;
                }
                case 2 :{
                    System.out.println("输入要修改的年龄");
                    int newAge = scanner.nextInt();
                    staal.get(x).setAge(newAge);
                    break;
                }
                case 3 :{
                    System.out.println("输入要修改的薪水");
                    double newSalary = scanner.nextDouble();
                    staal.get(x).setSalary(newSalary);
                    break;
                }
                case 4 :{
                    System.out.println("请输入要修改的工作");
                    String newJobs = scanner.next();
                    staal.get(x).setJobs(newJobs);
                }
                case 5:{
                    flag = false;
                }
            }
        }
        System.out.println("工人信息修改完成");
        //fo.saveStaText(staal,fileSta);
        fo.saveText(staal,fileSta);
        returnLast();
    }
    public void findAll() throws Exception {
        if(staal==null){
            System.out.println("当前文本为空");
            returnLast();
        }
        try{
            for(int i=0;i<staal.size();i++){
                System.out.println(staal.get(i).toString());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("当前文本内容为空");
        }finally {
            if(staal.size()==0){         //若当前文本内容为空，则必定执行该语句
                System.out.println("当前文本为空********");
            }
            returnLast();
        }
    }
    public void findByLike() throws Exception {
        boolean f = false;
        System.out.println("请选择查询信息的方式");
        System.out.println("1、根据ID进行精确查询");
        System.out.println("2、根据姓名进行模糊查询");
        int x = scanner.nextInt();
        if(x==1){
            System.out.println("请输入工人的id");
            long id = scanner.nextLong();
            for(int i =0;i<staal.size();i++){
                if(staal.get(i).getId()==id){
                    System.out.println(staal.get(i).toString());
                    f = true;
                }
            }
        }
        if(x==2){
            System.out.println("请输入工人的姓名:");
            String name = scanner.next();
            for(int j = 0;j<staal.size();j++){
                if(staal.get(j).getName().equals(name)){
                    System.out.println(staal.get(j).toString());
                    f = true;
                }
            }
        }
        if(!f){
            System.out.println("查无此人");
        }
        returnLast();
    }
    public void returnMenu() throws Exception {
        infoo.show();
    }
}
