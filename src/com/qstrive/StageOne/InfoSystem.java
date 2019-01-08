package com.qstrive.StageOne;

import java.util.Scanner;

public class InfoSystem {
    Scanner scanner = new Scanner(System.in);
    //绑定两个子菜单
    private StuInfoSystem stuis = new StuInfoSystem(this);
    private StaffInfoSystem stais = new StaffInfoSystem(this);
    public  void show(){

        boolean flag = true;
        while(flag){
            System.out.println("学校信息管理程序");
            System.out.println("       1、学生信息管理");
            System.out.println("       2、工人信息管理");
            System.out.println("       3、退出系统");
            int i = scanner.nextInt();
            switch (i){
                case 1 :{
                    stuis.show();
                }
                case 2 :{
                    stais.show();
                }
                case 3 :{
                    flag = false;
                    scanner.close();
                    System.exit(0);
                }
            }
        }
    }
}

