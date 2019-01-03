package Operate;

import ValueObject.Staff;
import ValueObject.Student;

import java.io.*;
import java.util.ArrayList;

public class FileOperate {

    public void saveStuText(ArrayList<Student> arrayList,File file)throws Exception{
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for(int i=0;i<arrayList.size();i++){
            bw.write(arrayList.get(i).toString());
            bw.newLine();
        }
        bw.close();
    }
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
    public void saveStaText(ArrayList<Staff> arrayList, File file)throws Exception{
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for(int i=0;i<arrayList.size();i++){
            bw.write(arrayList.get(i).toString());
            bw.newLine();
        }
        bw.close();
    }
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
