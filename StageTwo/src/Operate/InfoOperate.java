package Operate;

import java.util.Scanner;

public class InfoOperate {

    Scanner scanner = new Scanner(System.in);
    //将文件操作类和学生、工人操作类及本身绑定
    private FileOperate fo;
    private StudentOperate stuo;
    private StaffOperate stao;
    public InfoOperate(FileOperate fo) throws Exception {
        this.fo = fo;
        stuo = new StudentOperate(this,this.fo);
        stao = new StaffOperate(this,this.fo);
    }
    public void show() throws Exception {
        boolean flag = true;
        while (flag){
            System.out.println("学校信息管理程序");
            System.out.println("       1、学生信息管理");
            System.out.println("       2、工人信息管理");
            System.out.println("       3、退出系统");
            int i = scanner.nextInt();
            switch (i){
                case 1 :{
                    stuo.show();
                }
                case 2 :{
                    stao.show();
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
