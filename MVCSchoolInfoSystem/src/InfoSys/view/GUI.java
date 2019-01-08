package InfoSys.view;

import InfoSys.vo.Staff;
import InfoSys.vo.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class GUI {

    JFrame jframe;
    Container container;
    //设置GUI界面布局管理
    CardLayout cardLayout = new CardLayout();

    //主界面
    JPanel totalPanel = new JPanel();
    JPanel mainPanel = new JPanel();
    JPanel stuPanel = new JPanel();
    JPanel staPanel = new JPanel();
    JLabel selLb = new JLabel("选择人员信息");
    JButton stuBt = new JButton("学生");
    JButton staBt = new JButton("工人");

    //返回界面
    //JPanel stuRePanel = new JPanel();
    //JButton stuReBt = new JButton("返回");
    //JPanel staRePanel = new JPanel();
   // JButton staReBt = new JButton("返回");

    /*
        日志面板
     */
    protected JPanel logPanel=new JPanel();
    protected JLabel logLb=new JLabel("操作日志",SwingConstants.CENTER);
    protected JTextArea logTa=new JTextArea(9,50);
    protected JScrollPane logSp=new JScrollPane(logTa);

    /*
        学生信息界面
     */
    JPanel stuShowPanel = new JPanel();
    JPanel stuSelPanel = new JPanel();
    JButton stuEachBt = new JButton("学生详细信息");
    JButton stuAllBt = new JButton("所有学生信息");
    JButton stuReBt = new JButton("返回");


    JPanel stuEachPanel = new JPanel();
    JLabel stuIdLb = new JLabel("学生Id");
    JLabel stuNameLb = new JLabel("姓名");
    JLabel stuAgeLb = new JLabel("年龄");
    JLabel stuGradeLb = new JLabel("成绩");

    protected JTextField stuIdTf = new JTextField(25);
    protected JTextField stuNameTf = new JTextField(25);
    protected JTextField stuAgeTf = new JTextField(25);
    protected JTextField stuGradeTf = new JTextField(25);

    protected JButton stuSeaBt = new JButton("查询学生");
    protected JButton stuUpdaBt = new JButton("更新学生");
    protected JButton stuAddBt = new JButton("新增学生");
    protected JButton stuDelBt = new JButton("删除学生");

    protected JPanel stuAllPanel = new JPanel();
    protected JLabel stuAllLb = new JLabel("所有学生清单",SwingConstants.CENTER);
    protected  JTextArea stuAllTa = new JTextArea();
    protected JScrollPane stuAllSp = new JScrollPane(stuAllTa);
    String[] stuTableHeaders = {"ID","姓名","年龄","成绩"};
    JTable stuTable;
    JScrollPane stuTablePanel;
    DefaultTableModel stuTableModel;

    /*
        工人信息界面
     */
    JPanel staShowPanel = new JPanel();
    JPanel staSelPanel = new JPanel();
    JButton staEachBt = new JButton("工人详细信息");
    JButton staAllBt = new JButton("所有工人信息");
    JButton staReBt = new JButton("返回");

    JPanel staEachPanel = new JPanel();
    JLabel staIdLb = new JLabel("Id");
    JLabel staNameLb = new JLabel("姓名");
    JLabel staAgeLb = new JLabel("年龄");
    JLabel staSalaryLb = new JLabel("薪水");
    JLabel staJobsLb = new JLabel("工作");

    protected JTextField staIdTf = new JTextField(25);
    protected JTextField staNameTf = new JTextField(25);
    protected JTextField staAgeTf = new JTextField(25);
    protected JTextField staSalaryTf = new JTextField(25);
    protected JTextField staJobsTf = new JTextField(25);

    protected JButton staSeaBt = new JButton("查询工人");
    protected JButton staUpdaBt = new JButton("更新工人");
    protected JButton staAddBt = new JButton("新增工人");
    protected JButton staDelBt = new JButton("删除工人");

    protected JPanel staAllPanel = new JPanel();
    protected JLabel staAllLb = new JLabel("所有工人清单",SwingConstants.CENTER);
    protected  JTextArea staAllTa = new JTextArea();
    protected JScrollPane staAllSp = new JScrollPane(stuAllTa);
    String[] staTableHeaders = {"ID","姓名","年龄","薪水","工作"};
    JTable staTable;
    JScrollPane staTablePanel;
    DefaultTableModel staTableModel;

    public GUI(){
        buildDisplay();
    }
    //创建GUI框架
    public void buildDisplay(){
        buildMainPanel();
        buildStuPanel();
        buildStaPanel();
        buildLogPanel();
        jframe = new JFrame("学校信息管理系统");
        container = jframe.getContentPane();
        container.setLayout(cardLayout);
        container.add(totalPanel);
        totalPanel.setLayout(cardLayout);
        totalPanel.add(mainPanel,"mainPanel");
        totalPanel.add(stuPanel,"stuPanel");
        totalPanel.add(staPanel,"staPanel");

        jframe.setSize(500, 500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = jframe.getSize();

        if (frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        if (frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;

        jframe.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

//        jframe.setLocation(500,300);
        jframe.pack();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
    }
    //创建主界面
    public void buildMainPanel(){
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER,80,80));
        mainPanel.add(selLb);
        mainPanel.add(stuBt);
        mainPanel.add(staBt);
    }
    //设置CardLayout为主界面
    public void showMain(String str){
        cardLayout.show(totalPanel,str);
    }
    //设置CardLayout为学生界面
    public void showStu(String str){
        cardLayout.show(stuShowPanel,str);
    }
    //设置CardLayout为工人界面
    public void showSta(String str){
        cardLayout.show(staShowPanel,str);
    }
    //为主界面添加事件监听
    public void addMainPanelListeners(ActionListener a[]){
        stuBt.addActionListener(a[0]);
        staBt.addActionListener(a[1]);
    }
    //显示学生界面
    public void refreshStuPanel(){
        showMain("stuPanel");
    }
    //创建学生界面
    public void buildStuPanel(){
        buildStuSelPanel();
        buildStuShowPanel();
        stuPanel.setLayout(new BorderLayout());
        stuPanel.add(stuSelPanel,BorderLayout.NORTH);
        stuPanel.add(stuShowPanel,BorderLayout.CENTER);
        stuPanel.add(logPanel,BorderLayout.SOUTH);
    }
    //显示返回的界面即为主界面
    public void refreshStuRePanel(){
        showMain("mainPanel");
    }
    //创建学生界面选择界面
    public void buildStuSelPanel(){
        stuSelPanel.setLayout(new GridLayout(1,3));
        stuSelPanel.add(stuEachBt);
        stuSelPanel.add(stuAllBt);
        stuSelPanel.add(stuReBt);
    }
    //添加学生选择界面事件监听器
    public void addStuSelPanelListener(ActionListener a[]){
        stuEachBt.addActionListener(a[0]);
        stuAllBt.addActionListener(a[1]);
        stuReBt.addActionListener(a[2]);
    }
    //创建学生信息展示界面
    public void buildStuShowPanel(){
        buildStuEachPanel();
        buildStuAllPanel();
        stuShowPanel.setLayout(cardLayout);
        stuShowPanel.add(stuEachPanel,"stuEachPanel");
        stuShowPanel.add(stuAllPanel,"stuAllPanel");
    }
    //单个学生信息
    public void buildStuEachPanel(){
        stuEachPanel.setLayout(new GridLayout(6,2));
        stuEachPanel.add(stuIdLb);
        stuEachPanel.add(stuIdTf);
        stuEachPanel.add(stuNameLb);
        stuEachPanel.add(stuNameTf);
        stuEachPanel.add(stuAgeLb);
        stuEachPanel.add(stuAgeTf);
        stuEachPanel.add(stuGradeLb);
        stuEachPanel.add(stuGradeTf);

        stuEachPanel.add(stuSeaBt);
        stuEachPanel.add(stuUpdaBt);
        stuEachPanel.add(stuAddBt);
        stuEachPanel.add(stuDelBt);
    }
    //添加操作监听器
    public void addStuEachPanelListener(ActionListener a[]){
        stuSeaBt.addActionListener(a[0]);
        stuAddBt.addActionListener(a[1]);
        stuDelBt.addActionListener(a[2]);
        stuUpdaBt.addActionListener(a[3]);
    }
    //所有学生信息
    public void buildStuAllPanel(){
        stuAllPanel.setLayout(new BorderLayout());
        stuAllPanel.add(stuAllLb,BorderLayout.NORTH);
        stuAllTa.setText("所有的学生信息");
        stuTableModel = new DefaultTableModel(stuTableHeaders,10);
        stuTable = new JTable(stuTableModel);
        stuTablePanel = new JScrollPane(stuTable);
        stuAllPanel.add(stuTablePanel,BorderLayout.CENTER);
        Dimension dimension = new Dimension(500,150);
        stuTable.setPreferredScrollableViewportSize(dimension);
    }
    //展示单个学生
    public void refreshEachStuPanel(Student stu){
        showStu("stuEachPanel");
        if(stu==null){
            stuIdTf.setText(null);
            stuNameTf.setText(null);
            stuAgeTf.setText(null);
            stuGradeTf.setText(null);
            return;
        }
        stuIdTf.setText(Long.toString(stu.getId()));
        stuNameTf.setText(stu.getName().trim());
        stuAgeTf.setText(Integer.toString(stu.getAge()));
        stuGradeTf.setText(Double.toString(stu.getGrade()));
    }
    //展示所有学生
    public void refreshAllStuPanel(ArrayList<Student> alStu){
        showStu("stuAllPanel");
        String newData[][];
        newData =  new String[alStu.size()][4];
        Iterator it = alStu.iterator();
        int i = 0;
        while (it.hasNext()){
            Student stu = (Student) it.next();
            newData[i][0] = Long.toString(stu.getId());
            newData[i][1] = stu.getName();
            newData[i][2] = Integer.toString(stu.getAge());
            newData[i][3] = Double.toString(stu.getGrade());
            i++;
            stuTableModel.setDataVector(newData,stuTableHeaders);
        }
    }

    //  获得学生面板上用户输入的ID
    public Long getStuIdOnEachPanel(){
        //若当前id上没有文本则返回-111，使得重新刷新界面
        if(stuIdTf.getText().length()==0){
            return (long) -111;
        }
        try{
            return Long.parseLong(stuIdTf.getText().trim());
        }catch (Exception e){
            updateLog(e.getMessage());
            return (long) -1;
        }
    }
    //获得单个学生面板上用户输入的客户信息
    public Student getStuOnEachPanel(){
        try{
            return new Student(Long.parseLong(stuIdTf.getText().trim()),
                    stuNameTf.getText().trim(),Integer.parseInt(stuAgeTf.getText().trim()),
                    Double.parseDouble(stuGradeTf.getText().trim()));
        }catch (Exception e){
            updateLog(e.getMessage());
            return null;
        }
    }


    //显示工人界面
    public void refreshStaPanel(){
        showMain("staPanel");
    }
    //创建工人界面
    public void buildStaPanel(){
        buildStaSelPanel();
        buildStaShowPanel();
        staPanel.setLayout(new BorderLayout());
        staPanel.add(staSelPanel,BorderLayout.NORTH);
        staPanel.add(staShowPanel,BorderLayout.CENTER);
        staPanel.add(logPanel,BorderLayout.SOUTH);
    }
    //显示返回的界面即为主界面
    public void refreshStaRePanel(){
        showMain("mainPanel");
    }
    //创建工人选择界面
    public void buildStaSelPanel(){
        staSelPanel.setLayout(new GridLayout(1,3));
        staSelPanel.add(staEachBt);
        staSelPanel.add(staAllBt);
        staSelPanel.add(staReBt);
    }
    //添加工人选择界面事件监听器
    public void addStaSelPanelListener(ActionListener a[]){
        staEachBt.addActionListener(a[0]);
        staAllBt.addActionListener(a[1]);
        staReBt.addActionListener(a[2]);
    }
    //创建工人信息展示界面
    public void buildStaShowPanel(){
        buildStaEachPanel();
        buildStaAllPanel();
        staShowPanel.setLayout(cardLayout);
        staShowPanel.add(staEachPanel,"staEachPanel");
        staShowPanel.add(staAllPanel,"staAllPanel");
    }
    //单个工人信息
    public void buildStaEachPanel(){
        staEachPanel.setLayout(new GridLayout(8,2));
        staEachPanel.add(staIdLb);
        staEachPanel.add(staIdTf);
        staEachPanel.add(staNameLb);
        staEachPanel.add(staNameTf);
        staEachPanel.add(staAgeLb);
        staEachPanel.add(staAgeTf);
        staEachPanel.add(staSalaryLb);
        staEachPanel.add(staSalaryTf);
        staEachPanel.add(staJobsLb);
        staEachPanel.add(staJobsTf);

        staEachPanel.add(staSeaBt);
        staEachPanel.add(staUpdaBt);
        staEachPanel.add(staAddBt);
        staEachPanel.add(staDelBt);
    }
    //添加操作监听器
    public void addStaEachPanelListener(ActionListener a[]){
        staSeaBt.addActionListener(a[0]);
        staAddBt.addActionListener(a[1]);
        staDelBt.addActionListener(a[2]);
        staUpdaBt.addActionListener(a[3]);
    }
    //所有工人信息
    public void buildStaAllPanel(){
        staAllPanel.setLayout(new BorderLayout());
        staAllPanel.add(staAllLb,BorderLayout.NORTH);
        staAllTa.setText("所有的工人信息");
        staTableModel = new DefaultTableModel(staTableHeaders,10);
        staTable = new JTable(staTableModel);
        staTablePanel = new JScrollPane(staTable);
        staAllPanel.add(staTablePanel,BorderLayout.CENTER);
        Dimension dimension = new Dimension(500,150);
        staTable.setPreferredScrollableViewportSize(dimension);
    }
    //展示单个工人
    public void refreshEachStaPanel(Staff sta){
        showSta("staEachPanel");
        if(sta==null){
            staIdTf.setText(null);
            staNameTf.setText(null);
            staAgeTf.setText(null);
            staSalaryTf.setText(null);
            staJobsTf.setText(null);
            return;
        }
        staIdTf.setText(Long.toString(sta.getId()));
        staNameTf.setText(sta.getName().trim());
        staAgeTf.setText(Integer.toString(sta.getAge()));
        staSalaryTf.setText(Double.toString(sta.getSalary()));
        staJobsTf.setText(sta.getJobs());
    }
    //展示所有工人
    public void refreshAllStaPanel(ArrayList<Staff> alSta){
        showSta("staAllPanel");
        String newData[][];
        newData =  new String[alSta.size()][5];
        Iterator it = alSta.iterator();
        int i = 0;
        while (it.hasNext()) {
            Staff sta = (Staff) it.next();
            newData[i][0] = Long.toString(sta.getId());
            newData[i][1] = sta.getName();
            newData[i][2] = Integer.toString(sta.getAge());
            newData[i][3] = Double.toString(sta.getSalary());
            newData[i][4] = sta.getJobs();
            i++;
            staTableModel.setDataVector(newData, staTableHeaders);
        }
    }

    // 获得工人面板上用户输入的ID
    public Long getStaIdOnEachPanel(){
        //若当前id上没有文本则返回-111，使得重新刷新界面
        if(staIdTf.getText().length()==0){
            return (long) -111;
        }
        try{
            return Long.parseLong(staIdTf.getText().trim());
        }catch (Exception e){
            updateLog(e.getMessage());
            return (long) -1;
        }
    }
    // 获得单个工人面板上用户输入的客户信息
    public Staff getStaOnEachPanel(){
        try{
            return new Staff(Long.parseLong(staIdTf.getText().trim()),
                    staNameTf.getText().trim(),Integer.parseInt(staAgeTf.getText().trim()),
                    Double.parseDouble(staSalaryTf.getText().trim()),staJobsTf.getText().trim());
        }catch (Exception e){
            updateLog(e.getMessage());
            return null;
        }
    }
    //创建日志面板
   public void buildLogPanel(){
        logPanel.setLayout(new BorderLayout());
        logSp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        logSp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        logPanel.add(logLb,BorderLayout.NORTH);
        this.logPanel.add(logSp,BorderLayout.CENTER);
    }
    //在日志面板中添加日志信息
    public void updateLog(String msg)
    {
        logTa.append(msg+"\n");
    }

}
