package Operate;

import ValueObject.Person;
import ValueObject.Staff;
import ValueObject.Student;

import java.io.*;
import java.util.ArrayList;

public class FileOperate {

    //对学生信息进行保存
    public void saveStuText(ArrayList<Student> arrayList,File file)throws Exception{
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for(int i=0;i<arrayList.size();i++){
            bw.write(arrayList.get(i).toString());
            bw.newLine();
        }
        bw.close();
    }
    //对学生信息进行读取
    public ArrayList<Student> loadStuText(File file) throws Exception{
        if(!file.exists()){
            file.createNewFile();
            return null;
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<Student>  arrayStuList = new ArrayList<>();
        String str;
        String[] tempString = null;
        while((str=br.readLine())!= null){
            tempString = str.split(",");
            long id = Long.parseLong(tempString[0]);
            String name = tempString[1];
            int age = Integer.parseInt((tempString[2]));
            double grade = Double.parseDouble((tempString[3]));
            Student newStu = new Student(id,name,age,grade);
            arrayStuList.add(newStu);
        }
        br.close();
        return arrayStuList;
    }
    //利用泛型方法通配符将Student和Staff利用同一个方法进行信息保存
    public void saveText(ArrayList<? extends Person> arrayList,File file)throws  Exception{
        FileWriter fw = new FileWriter(file);
        //缓冲流进行文件写入
        BufferedWriter bw = new BufferedWriter(fw);
        for(int i=0;i<arrayList.size();i++){
            bw.write(arrayList.get(i).toString());
            //写入新行
            bw.newLine();
        }
        bw.close();
    }
    //利用泛型方法通配符将文本文档中的信息读取出来
    public ArrayList<? extends Person> loadText(File file) throws  Exception{
        if(!file.exists()){
            file.createNewFile();
            return null;
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<Student>  arrayStuList = new ArrayList<>();
        ArrayList<Staff> arrayStaList = new ArrayList<>();
        String str;
        String[] tempString = null;
        boolean flag = false;           //判断文本文档中的信息是学生还是工人
        while((str=br.readLine())!= null){
            //将字符串拆分为字符串数组用于接下来的判断与赋值实例化
            tempString = str.split(",");
            //如果是学生信息就读取到学生集合中
            if(tempString.length==4){
                flag = true;
                long id = Long.parseLong(tempString[0]);
                String name = tempString[1];
                int age = Integer.parseInt((tempString[2]));
                double grade = Double.parseDouble((tempString[3]));
                Student newStu = new Student(id,name,age,grade);
                arrayStuList.add(newStu);
            }else{      //工人信息读取到工人集合中
                long id = Long.parseLong(tempString[0]);
                String name = tempString[1];
                int age = Integer.parseInt(tempString[2]);
                double salary = Double.parseDouble(tempString[3]);
                String jobs = tempString[4];
                Staff newSta = new Staff(id,name,age,salary,jobs);
                arrayStaList.add(newSta);
            }

        }
        br.close();
        //如果是学生信息，返回学生集合，反之工人集合
        if(flag){
            return arrayStuList;
        }else{
            return arrayStaList;
        }
    }
    //同理，将saveText拆分为专门对工人跟信息进行保存
    public void saveStaText(ArrayList<Staff> arrayList, File file)throws Exception{
        FileWriter fw = new FileWriter(file);
        //缓冲流进行文件写入
        BufferedWriter bw = new BufferedWriter(fw);
        for(int i=0;i<arrayList.size();i++){
            bw.write(arrayList.get(i).toString());
            //写入新行
            bw.newLine();
        }
        bw.close();
    }
    //同理，将loadText拆分为专门对工人信息进行加载
    public ArrayList<Staff> loadStaText(File file) throws Exception{
        if(!file.exists()){
            file.createNewFile();
            return null;
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<Staff>  arrayStaList = new ArrayList<>();
        String str;
        String[] tempString = null;
        while((str=br.readLine())!= null){
            tempString = str.split(",");
            long id = Long.parseLong(tempString[0]);
            String name = tempString[1];
            int age = Integer.parseInt(tempString[2]);
            double salary = Double.parseDouble(tempString[3]);
            String jobs = tempString[4];
            Staff newSta = new Staff(id,name,age,salary,jobs);
            arrayStaList.add(newSta);
        }
        br.close();
        return arrayStaList;
    }
}
