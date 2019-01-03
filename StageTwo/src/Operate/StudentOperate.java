package Operate;

import ValueObject.Student;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentOperate implements PersonOperate {
    //定义读取的文件路径
    File fileStu = new File("d:"+ File.separator+"Student.txt");
    Scanner scanner = new Scanner(System.in);
    //绑定文件操作类和主菜单
    private InfoOperate infoo ;
    private FileOperate fo;
    ArrayList<Student> stual;
    public StudentOperate(InfoOperate infoo,FileOperate fo) throws Exception {
        this.infoo = infoo;
        this.fo = fo;
        /*
            拆分时的操作方法
         */
        //stual = fo.loadStuText(fileStu);

        //合并操作方法的加载学生信息方法
        stual = (ArrayList<Student>) fo.loadText(fileStu);
    }
    public void show() throws Exception {

        System.out.println("学生信息管理");
        System.out.println("       1、增加学生信息");
        System.out.println("       2、列出全部学生信息");
        System.out.println("       3、查询学生信息");
        System.out.println("       4、删除学生信息");
        System.out.println("       5、修改学生信息");
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
        double grade;
        System.out.println("输入学生id:");
        id = scanner.nextLong();
        System.out.println("输入学生姓名:");
        name = scanner.next();
        System.out.println("输入学生年龄:");
        age = scanner.nextInt();
        System.out.println("输入学生成绩:");
        grade = scanner.nextDouble();
        Student newStu = new Student(id,name,age,grade);
        //若文本文档内容是否为空，则新建集合类并添加元素
        if(stual==null||stual.size()==0){
            stual = new ArrayList<>();
            stual.add(newStu);
            System.out.println("添加成功");
        }else{
            for(int i=0;i<stual.size();i++){
                //查重
                if(stual.get(i).getId()!=newStu.getId()){
                    stual.add(newStu);
                    System.out.println("添加成功");
                    break;
                }else {
                    System.out.println("你输入的学生id已存在,检查无误后再次提交");
                }
            }
        }
        //fo.saveStuText(stual,fileStu);

        //保存学生信息到文本文档中
        fo.saveText(stual,fileStu);
        returnLast();
    }
    public void delete() throws Exception {
        System.out.println("请输入要删除的学生id");
        long id = scanner.nextLong();
        for(int i =0;i<stual.size();i++){
            if (stual.get(i).getId()==id){
                System.out.println("删除成功  " + stual.remove(i));
            }
        }
        //fo.saveStuText(stual,fileStu);
        fo.saveText(stual,fileStu);
        returnLast();
    }
    public void update() throws Exception {
        System.out.println("请输入要查询的学生id");
        long id = scanner.nextLong();
        int x = 0;
        for(int i=0;i<stual.size();i++){
            if(stual.get(i).getId()==id){
                x = i;
            }
        }
        System.out.println("请输入要修改的信息:");
        boolean flag = true;
        while(flag){
            System.out.println("1、学生姓名");
            System.out.println("2、学生年龄");
            System.out.println("3、学生成绩");
            System.out.println("4、修改完成");
            int j = scanner.nextInt();
            switch (j){
                case 1 :{
                    System.out.println("输入要修改的姓名");
                    String newName = scanner.next();
                    stual.get(x).setName(newName);
                    break;
                }
                case 2 :{
                    System.out.println("输入要修改的年龄");
                    int newAge = scanner.nextInt();
                    stual.get(x).setAge(newAge);
                    break;
                }
                case 3 :{
                    System.out.println("输入要修改的成绩");
                    double newGrade = scanner.nextDouble();
                    stual.get(x).setGrade(newGrade);
                    break;
                }
                case 4:{
                    flag = false;
                }
            }
        }
        System.out.println("学生信息修改完成");
        //fo.saveStuText(stual,fileStu);
        fo.saveText(stual,fileStu);
        returnLast();
    }
    public void findAll() throws Exception {
        if(stual==null){
            System.out.println("当前文本为空");
            returnLast();
        }
        try{
            for (Student aStual : stual) {
                System.out.println(aStual.toString());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("当前文本内容为空");
        }finally {
            if(stual.size()==0){        //若当前文本内容为空，则必定执行该语句
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
           System.out.println("请输入学生的id");
           long id = scanner.nextLong();
           for(int i =0;i<stual.size();i++){
               if(stual.get(i).getId()==id){
                   System.out.println(stual.get(i).toString());
                   f = true;
               }
           }
       }
       if(x==2){
           System.out.println("请输入学生的姓名:");
           String name = scanner.next();
           for(int j = 0;j<stual.size();j++){
               if(stual.get(j).getName().equals(name)){
                   System.out.println(stual.get(j).toString());
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
