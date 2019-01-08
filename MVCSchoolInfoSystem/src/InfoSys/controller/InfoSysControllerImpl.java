package InfoSys.controller;

import InfoSys.model.InfoSysModel;
import InfoSys.test.InfoSysException;
import InfoSys.view.InfoSysView;
import InfoSys.vo.Person;
import InfoSys.vo.Staff;
import InfoSys.vo.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class InfoSysControllerImpl implements InfoSysController {
    private InfoSysView isv;
    private InfoSysModel ism;

    public InfoSysControllerImpl(InfoSysView isv,InfoSysModel ism){
        try{
            this.isv = isv;
            this.ism = ism;
            isv.addUserGestureListener(this);
        }catch (Exception e){
            reportException(e);
        }
    }
    //报告异常信息
    public void reportException(Object o){
        try{
            isv.showDisplay(o);
        }catch (Exception e){
            System.out.println("InfoSysControllerImpl reportException"+e);
        }
    }
    //处理根据ID查询人员的操作
    public void handleGetPersonGesture(Long id,int i) throws InfoSysException, SQLException {
        if(i==4) {
            Student stu = null;
            try {
                stu = ism.getPerson(id,4);
                isv.showDisplay(stu);
            }catch (InfoSysException e){
                e.printStackTrace();
            }finally {
                if(stu==null){
                    isv.hanleNullException(4);
                }
            }
        }else if(i==5){
            Staff sta = null;
            try{
                sta = ism.getPerson(id,5);
                isv.showDisplay(sta);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(sta==null){
                    isv.hanleNullException(5);
                }
            }
        }
    }
    //处理添加人员的操作
    public void handleAddPersonGesture(Person person) throws SQLException {
        try{
            ism.addPerson(person);
        } catch (InfoSysException e) {
            e.printStackTrace();
        }
    }
    //处理删除人员的操作
    @Override
    public void handleDeletePersonGesture(Person person) throws SQLException {
        try{
            ism.deletePerson(person);
        }catch (InfoSysException e) {
            e.printStackTrace();
        }
    }
    //处理更新人员的操作
    @Override
    public void handleUpdatePersonGesture(Person person) throws SQLException {
        try{
            ism.updatePerson(person);
        } catch (InfoSysException e) {
            e.printStackTrace();
        }
    }
    //处理显示所有人员的操作
    @Override
    public void handleGetAllPersonGesture(int i) throws SQLException {
        if(i==4){
            try{
                ArrayList<Student> alTempStu;
                alTempStu = (ArrayList<Student>) ism.getPersons(4);
                isv.showDisplay(alTempStu);
            } catch (InfoSysException e) {
                e.printStackTrace();
            }
        }else if(i==5){
            try{
                ArrayList<Staff> alTempSta;
                alTempSta = (ArrayList<Staff>) ism.getPersons(5);
                isv.showDisplay(alTempSta);
            } catch (InfoSysException e) {
                e.printStackTrace();
            }
        }
    }
}

