package InfoSys.vo;

import java.io.Serializable;

//学生类
public class Student extends Person implements Serializable {
    private double grade;

    public Student(long id, String name, int age, double grade) {
        super(id, name, age);
        this.grade = grade;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        /*
        return "Student{" +
                "grade=" + grade +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
          */
        return id+","+name+","+age+","+grade;
    }
}
