package InfoSys.view;

import InfoSys.controller.InfoSysController;
import InfoSys.model.InfoSysModel;
import InfoSys.test.InfoSysException;
import InfoSys.vo.Person;
import InfoSys.vo.Staff;
import InfoSys.vo.Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class InfoSysViewImpl implements InfoSysView {

        GUI gui;
        InfoSysModel ism;
        Object display;
        ArrayList<? extends Person> alPer = new ArrayList<>();
        ArrayList<InfoSysController> iscon = new ArrayList<>(10);

        public InfoSysViewImpl(InfoSysModel ism) throws Exception{
            try{
                this.ism = ism;
                ism.addChangeListener(this);    //向model注册本身
            }catch (Exception e){
                e.printStackTrace();
                e.getMessage();
            }
            gui = new GUI();

            //注册事件监听器
            gui.addMainPanelListeners(mainPanelListeners);
            gui.addStuEachPanelListener(stuEachPanelListeners);
            gui.addStaEachPanelListener(staEachPanelListeners);
            gui.addStuSelPanelListener(stuSelPanelListeners);
            gui.addStaSelPanelListener(staSelPanelListeners);
            gui.jframe.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        ism.closeDataBase();
                        System.out.println("数据库已关闭");
                    } catch (InfoSysException e1) {
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }finally {
                        System.exit(1);
                    }
                }
            });
        }

        //实现接口方法
        public void addUserGestureListener(InfoSysController ctrl)throws InfoSysException{
            iscon.add(ctrl);
        }
        public void showDisplay(Object display)throws InfoSysException{
            if(!(display instanceof Exception)){
                this.display = display;
            }
            if(display instanceof Student){
                gui.refreshEachStuPanel((Student) display);
            }
            if(display instanceof Staff){
                gui.refreshEachStaPanel((Staff) display);
            }
            if(display instanceof ArrayList){
                if(((ArrayList) display).get(0) instanceof Student){
                    gui.refreshAllStuPanel((ArrayList<Student>) display);
                }else {
                    gui.refreshAllStaPanel((ArrayList<Staff>) display);
                }
            }
            if(display instanceof Exception){
                //gui.updateLog(((Exception) display).getMessage());
                ((Exception) display).getMessage();
            }
        }
        public void hanleNullException(int x){
            if(x==4){
                System.out.println("查无此人，重新输入");
                gui.refreshEachStuPanel(null);
            }else if(x==5){
                System.out.println("查无此人，重新输入");
                gui.refreshEachStaPanel(null);
            }
        }
        public void handlePersonChange(Person person)throws InfoSysException{
            Long idOnPanel = (long) -1;
            try{
                if(display instanceof ArrayList){
                    if(((ArrayList) display).get(0) instanceof Student){
                        gui.refreshAllStuPanel((ArrayList<Student>) ism.getPersons(4));
                        return;
                    }else {
                        gui.refreshAllStaPanel((ArrayList<Staff>) ism.getPersons(5));
                        return;
                    }
                }
                if(display instanceof Student){
                    Student stu = (Student) person;
                    idOnPanel = gui.getStuIdOnEachPanel();
                    if(idOnPanel!=stu.getId())return;
                    gui.refreshEachStuPanel(stu);
                }
                if(display instanceof Staff){
                    Staff sta = (Staff) person;
                    idOnPanel = gui.getStaIdOnEachPanel();
                    if(idOnPanel!=sta.getId())return;
                    gui.refreshEachStaPanel(sta);
                }
            }catch (Exception e){
                e.printStackTrace();

                //gui.updateLog(e.getMessage());
            }
        }
        //添加事件监听器
        ActionListener stuBtAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.refreshStuPanel();
            }
        };
        ActionListener staBtAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.refreshStaPanel();
            }
        };
        ActionListener stuReAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.refreshStuRePanel();
            }
        };
        ActionListener staReAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.refreshStaRePanel();
            }
        };
        ActionListener stuSeaAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoSysController ctrl;
                Long id = gui.getStuIdOnEachPanel();
                for(int x = 0;x<iscon.size();x++){
                    ctrl = iscon.get(x);
                    try {
                        ctrl.handleGetPersonGesture(id,4);
                    } catch (InfoSysException | SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        ActionListener stuAddAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoSysController ctrl;
                Student stu = gui.getStuOnEachPanel();
                for(int x =0;x<iscon.size();x++){
                    ctrl= iscon.get(x);
                    try {
                        ctrl.handleAddPersonGesture(stu);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        ActionListener stuDelAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoSysController ctrl;
                Student stu = gui.getStuOnEachPanel();
                for(int x = 0;x<iscon.size();x++){
                    ctrl = iscon.get(x);
                    try {
                        ctrl.handleDeletePersonGesture(stu);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        ActionListener stuUpdaAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoSysController ctrl;
                Student stu = gui.getStuOnEachPanel();
                for(int x = 0;x<iscon.size();x++){
                    ctrl = iscon.get(x);
                    try {
                        ctrl.handleUpdatePersonGesture(stu);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        ActionListener stuEachAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoSysController ctrl;
                Long id = gui.getStuIdOnEachPanel();
                if(id==-1){
                    try {
                        showDisplay(null);
                    } catch (Exception ea) {
                        ea.printStackTrace();
                    }
                }else if(id==-111){
                    try{
                        gui.refreshEachStuPanel(null);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                } else {
                    for(int x= 0;x<iscon.size();x++){
                        ctrl = iscon.get(x);
                        try {
                            ctrl.handleGetPersonGesture(id,4);
                        } catch (InfoSysException | SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        };
        ActionListener stuAllAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoSysController ctrl;
                for(int x = 0;x<iscon.size();x++){
                    ctrl = iscon.get(x);
                    try {
                        ctrl.handleGetAllPersonGesture(4);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        ActionListener staSeaAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoSysController ctrl;
                Long id = gui.getStaIdOnEachPanel();
                for(int x = 0;x<iscon.size();x++){
                    ctrl = iscon.get(x);
                    try {
                        ctrl.handleGetPersonGesture(id,5);
                    } catch (InfoSysException | SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        ActionListener staAddAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoSysController ctrl;
                Staff sta = gui.getStaOnEachPanel();
                for(int x =0;x<iscon.size();x++){
                    ctrl= iscon.get(x);
                    try {
                        ctrl.handleAddPersonGesture(sta);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        ActionListener staDelAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoSysController ctrl;
                Staff sta = gui.getStaOnEachPanel();
                for(int x = 0;x<iscon.size();x++){
                    ctrl = iscon.get(x);
                    try {
                        ctrl.handleDeletePersonGesture(sta);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        ActionListener staUpdaAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoSysController ctrl;
                Staff sta = gui.getStaOnEachPanel();
                for(int x = 0;x<iscon.size();x++){
                    ctrl = iscon.get(x);
                    try {
                        ctrl.handleUpdatePersonGesture(sta);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        ActionListener staEachAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoSysController ctrl;
                Long id = gui.getStaIdOnEachPanel();
                if(id==-1){
                    try {
                        showDisplay(null);
                    } catch (Exception ea) {
                        ea.printStackTrace();
                    }
                }else if(id==-111){
                    try{
                        gui.refreshEachStaPanel(null);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                } else {
                    for(int x= 0;x<iscon.size();x++){
                        ctrl = iscon.get(x);
                        try {
                            ctrl.handleGetPersonGesture(id,5);
                        } catch (InfoSysException | SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        };
        ActionListener staAllAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoSysController ctrl;
                for(int x = 0;x<iscon.size();x++){
                    ctrl = iscon.get(x);
                    try {
                        ctrl.handleGetAllPersonGesture(5);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };


        ActionListener mainPanelListeners[] = {stuBtAL,staBtAL};
        ActionListener stuEachPanelListeners[] = {stuSeaAL,stuAddAL,stuDelAL,stuUpdaAL};
        ActionListener staEachPanelListeners[] = {staSeaAL,staAddAL,staDelAL,staUpdaAL};
        ActionListener stuSelPanelListeners[] = {stuEachAL,stuAllAL,stuReAL};
        ActionListener staSelPanelListeners[] = {staEachAL,staAllAL,staReAL};
}
