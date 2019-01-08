package InfoSys.vo;

import java.io.Serializable;

//工人类
public class Staff extends Person implements Serializable {
    private double salary;
    private String jobs;

    public Staff(long id, String name, int age, double salary, String jobs) {
        super(id, name, age);
        this.salary = salary;
        this.jobs = jobs;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    @Override
    public String toString() {
        /*
        return "Staff{" +
                "salary=" + salary +
                ", jobs='" + jobs + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
         */
        return id+","+name+","+age+","+salary+","+jobs;
    }
}
