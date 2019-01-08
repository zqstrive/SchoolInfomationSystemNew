package InfoSys.model;

import InfoSys.test.InfoSysException;
import InfoSys.view.InfoSysView;
import InfoSys.vo.Person;
import InfoSys.vo.Staff;
import InfoSys.vo.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InfoSysModelImpl implements InfoSysModel {

    ArrayList<Person> alPer = new ArrayList<>();
    ArrayList<Student> alStu = new ArrayList<>();
    ArrayList<Staff> alSta = new ArrayList<>();
    ArrayList<InfoSysView> changeListeners = new ArrayList<>(10);
    InfoSysDataBase isdb;
    public InfoSysModelImpl() throws SQLException, ClassNotFoundException {
        isdb = new InfoSysDataBase();
    }
    @Override
    public void addChangeListener(InfoSysView isv) throws InfoSysException {
            changeListeners.add(isv);
    }
    /** 当数据库中人员信息发生变化时，同步刷新所有的视图*/
    private void fireModelChangeEvent(Person person){
        InfoSysView v;
        for(int i=0;i<changeListeners.size();i++){
            try{
                v=changeListeners.get(i);
                v.handlePersonChange(person);
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
    }
    //判断ID是否存在
    public boolean idExists(Long id,int x) throws SQLException {
        if(x==4){
            try{
                ResultSet result= isdb.stmt.executeQuery("select ID from student where ID="+id+" ");
              //  isdb.stmt.close();
                return result.next();
            } catch (SQLException e) {
                return false;
            }
        }else if(x==5){
            try{
                ResultSet result= isdb.stmt.executeQuery("select ID from staff where ID="+id+" ");
                //isdb.stmt.close();
                return result.next();
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }
    @Override
    //添加人员
    public void addPerson(Person person) throws InfoSysException, SQLException {
        if(person instanceof Student){
            Student stu = (Student) person;
            try{
                //由于暂时没有找到将字符串加入SQL的语句，故使用PreparedStatement占位符来加入字符串，以下同理
                String sqlAddStu ="INSERT INTO student(id,name,age,grade) VALUES("+stu.getId()+",?,?,?)";
                PreparedStatement pstmt = isdb.conn.prepareStatement(sqlAddStu);
                pstmt.setString(1,stu.getName());
                pstmt.setInt(2,stu.getAge());
                pstmt.setDouble(3,stu.getGrade());
                int len = pstmt.executeUpdate();
                System.out.println("更新行数:"+len);
                pstmt.close();
                fireModelChangeEvent(stu);
            }catch (Exception e){
                throw new InfoSysException("InfoSysException.addStudent\n"+e);
            }finally {
               // isdb.stmt.close();
            }
        }else {
            Staff sta = (Staff)person;
            try{
                String sqlAddSta ="INSERT INTO staff(id,name,age,salary,jobs) VALUES("+sta.getId()+",?,?,?,?)";
                PreparedStatement pstmt = isdb.conn.prepareStatement(sqlAddSta);
                pstmt.setString(1,sta.getName());
                pstmt.setInt(2,sta.getAge());
                pstmt.setDouble(3,sta.getSalary());
                pstmt.setString(4,sta.getJobs());
                int len = pstmt.executeUpdate();
                System.out.println("更新行数:"+len);
                pstmt.close();
                fireModelChangeEvent(sta);
            }catch (Exception e){
                throw new InfoSysException("InfoSysException.addStaff\n"+e);
            }finally {
               // isdb.stmt.close();
            }
        }

    }

    @Override
    //删除人员
    public void deletePerson(Person person) throws InfoSysException, SQLException {
        if(person instanceof Student){
            Student stu = (Student)person;
            try{
                String sqlDelStu = "DELETE FROM student WHERE id=? ";
                PreparedStatement pstmt = isdb.conn.prepareStatement(sqlDelStu);
                pstmt.setLong(1,stu.getId());
                int len = pstmt.executeUpdate();
                System.out.println("更新行数"+len);
//                isdb.stmt.executeUpdate(sqlDelStu);
                pstmt.close();
                fireModelChangeEvent(stu);
            }catch (Exception e){
                throw new InfoSysException("InfoSysException.delStudent\n"+e);
            }finally {
               // isdb.stmt.close();
            }
        }else {
            Staff sta = (Staff)person;
            try{
                String sqlDelSta = "DELETE FROM staff WHERE id=? ";
                PreparedStatement pstmt = isdb.conn.prepareStatement(sqlDelSta);
                pstmt.setLong(1,sta.getId());
                int len = pstmt.executeUpdate();
                System.out.println("更新行数"+len);
//                isdb.stmt.executeUpdate(sqlDelSta);
                pstmt.close();
                fireModelChangeEvent(sta);
            }catch (Exception e){
                throw new InfoSysException("InfoSysException.delStaff\n"+e);
            }finally {
               // isdb.stmt.close();
            }
        }
    }

    @Override
    //更新人员
    public void updatePerson(Person person) throws InfoSysException, SQLException {
        try{
            if (person instanceof Student){
                Student stu = (Student)person;
                if(!idExists(stu.getId(),4)){
                     throw new InfoSysException("Student "+person.getId()+"Not Found");
                 }
                String sqlUpStu = "UPDATE student set name=?,age =?,grade=? where id = ? ";
                PreparedStatement pstmt = isdb.conn.prepareStatement(sqlUpStu);
                pstmt.setString(1,stu.getName());
                pstmt.setInt(2,stu.getAge());
                pstmt.setDouble(3,stu.getGrade());
                pstmt.setLong(4,stu.getId());
                int len = pstmt.executeUpdate();
                System.out.println("更新行数:"+len);
//                isdb.stmt.executeUpdate(sqlUpdaStu);
                pstmt.close();
                fireModelChangeEvent(stu);
            }else {
                Staff sta = (Staff)person;
                if(!idExists(sta.getId(),5)){
                    throw new InfoSysException("Staff "+person.getId()+"Not Found");
                }
                String sqlUpSta = "UPDATE staff set name=?,age =?,salary=?,jobs=? where id = ? ";
                PreparedStatement pstmt = isdb.conn.prepareStatement(sqlUpSta);
                pstmt.setString(1,sta.getName());
                pstmt.setInt(2,sta.getAge());
                pstmt.setDouble(3,sta.getSalary());
                pstmt.setString(4,sta.getJobs());
                pstmt.setLong(5,sta.getId());
                int len = pstmt.executeUpdate();
                System.out.println("更新行数:"+len);
//                isdb.stmt.executeUpdate(sqlUpdaSta);
                pstmt.close();
                fireModelChangeEvent(sta);
            }
        }catch (Exception e){
            throw new InfoSysException("InfoSysException.updatePerson\n"+e);
        }
    }

    @Override
    public <T extends Person> T getPerson(Long id,int x) throws InfoSysException, SQLException {
        if(x==4){
            try{
                if(!idExists(id,4)){
                    throw new InfoSysException("Student "+id+"Not Found");
                }
                ResultSet rs = isdb.stmt.executeQuery("select id,name,age,grade from student where id="+id);
                rs.next();
             //   isdb.stmt.close();
                return (T) new Student(rs.getLong(1),rs.getString(2),rs.getInt(3),rs.getDouble(4));
            }catch (Exception e){
                throw new InfoSysException("InfoSysModelImpl.getStudent\n"+e);
            }
        }else if(x==5){
            try{
                if(!idExists(id,5)){
                    throw new InfoSysException("Staff "+id+"Not Found");
                }
                ResultSet rs = isdb.stmt.executeQuery("select id,name,age,salary,jobs from staff where id="+id);
                rs.next();
               // isdb.stmt.close();
                return (T) new Staff(rs.getLong(1),rs.getString(2),rs.getInt(3),rs.getDouble(4),rs.getString(5));
            }catch (Exception e){
                throw new InfoSysException("InfoSysModelImpl.getStaff\n"+e);
            }
        }
        return null;
    }

    @Override
    public ArrayList<? extends Person> getPersons(int x) throws InfoSysException, SQLException {
        if(x==4){
            ArrayList<Student> tempStu = new ArrayList<>();
            try{
                ResultSet rs = isdb.stmt.executeQuery("select id,name,age,grade from student");
                Student stu;
                while(rs.next()){
                    stu = new Student(rs.getLong(1),rs.getString(2),rs.getInt(3),rs.getDouble(4));
//                    alStu.add(stu);
                    tempStu.add(stu);
                }
              //  isdb.stmt.close();
//                return alStu;
                return tempStu;
            }catch (Exception e){
                throw new InfoSysException("InfoSysModelImpl.getAllStudents\n"+e);
            }
        }
        else if (x==5){
            ArrayList<Staff> tempSta = new ArrayList<>();
            try{
                ResultSet rs = isdb.stmt.executeQuery("select id,name,age,salary,jobs from staff");
                Staff sta;
                while(rs.next()){
                    sta = new Staff(rs.getLong(1),rs.getString(2),rs.getInt(3),rs.getDouble(4),rs.getString(5));
                    tempSta.add(sta);
                }
              //  isdb.stmt.close();
//                return alSta;
                return tempSta;
            }catch (Exception e){
                throw new InfoSysException("InfoSysModelImpl.getAllStaffs\n"+e);
            }
        }
        return alPer;
    }
    public void closeDataBase() throws SQLException {
        try{
            isdb.stmt.close();
            isdb.conn.close();
        }catch (Exception e){
            System.out.println("数据库无法正常关闭");
        }
    }
}
