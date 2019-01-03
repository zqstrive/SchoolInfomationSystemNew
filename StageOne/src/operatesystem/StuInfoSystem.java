    package operatesystem;

    import valueobject.Student;

    import java.util.Scanner;

    public class StuInfoSystem {
        private Student[] stuArray = new Student[10];
        //由总人数暂定不超过10人，定义count计数总人数
        public static int count = 0;
        Scanner scanner = new Scanner(System.in);
        //绑定父菜单
        private InfoSystem is;
        public StuInfoSystem(InfoSystem is){
            this.is = is;
        }
        //学生菜单界面
        public void show(){

            System.out.println("学生信息管理");
            System.out.println("       1、增加学生信息");
            System.out.println("       2、列出全部学生信息");
            System.out.println("       3、查询学生信息");
            System.out.println("       4、删除学生信息");
            System.out.println("       5、修改学生信息");
            System.out.println("       6、返回上一级菜单");
            while(true){
                int i = scanner.nextInt();
                switch (i){
                    case 1 :{
                        addStuInfo();
                        break;
                    }
                    case 2 :{
                        showDisplay();
                        break;
                    }
                    case 3 :{
                        searchInfo();
                        break;
                    }
                    case 4 :{
                        deleteInfo();
                        break;
                    }
                    case 5 :{
                        modifyInfo();
                        break;
                    }
                    case 6:{
                        returnMenu();
                        break;
                    }
                    default:{
                        System.out.println("未知错误");
                        break;
                    }
                }
            }
        }
        //返回学生菜单界面
        public void returnLast(){
            Scanner scan = new Scanner(System.in);
            System.out.println("返回上一级菜单？（1/0）");
            int x = scan.nextInt();
            if(x==1) show();
            if(x==0)
            {
                scan.close();
                System.exit(0);
            }
        }
        //增加学生
        public void addStuInfo(){
            long id;
            String name;
            int age ;
            double grade;
            System.out.println("输入学生id:");
            id = scanner.nextLong();
            System.out.println("输入学生姓名:");
            name = scanner.next();
            System.out.println("输入学生年龄:");
            age = scanner.nextInt();
            System.out.println("输入学生成绩:");
            grade = scanner.nextDouble();
            Student newStu = new Student(id,name,age,grade);
            if(stuArray[0]==null){
                stuArray[0] = newStu;
                count++;
                System.out.println("添加成功");
            }else{
                for (int i = 0;i<count;i++){
                    if(newStu.getId()!=stuArray[i].getId()){
                        stuArray[count++] = newStu;
                        System.out.println("增加成功");
                        break;
                    }else {
                        System.out.println("你输入的学生id已存在，检查无误后再次提交");
                    }
                }
            }

            returnLast();
        }
        //遍历打印所有学生信息
        public void showDisplay(){
            for(int i = 0;i<count;i++){
                System.out.println(stuArray[i].toString());
            }
            returnLast();
        }
        //按照id查询学生
        public void searchInfo(){
            boolean f = false;
            System.out.println("输入学生的id:");
            long id = scanner.nextLong();
            for(int i =0;i<count;i++){
                if(id==stuArray[i].getId()){
                    System.out.println(stuArray[i].toString());
                    f =true;
                }
            }
            if(!f){
                System.out.println("查无此人");
            }
            returnLast();
        }
        //按照id删除学生
        public void deleteInfo(){
            System.out.println("输入要删除学生的id");
            long id = scanner.nextLong();
            Student[] tempStu = new Student[10];
            for(int i=0;i<count;i++){
                if(stuArray[i].getId()==id){
                    stuArray[i] = null;
                    count--;
                }
            }
            for(int j = 0,x=0;j<count;j++){
                if(stuArray[j]!=null)
                {
                    tempStu[x++] = stuArray[j];
                }
                count = x;
            }
            stuArray = tempStu;
            returnLast();
        }
        //按照id修改学生信息
        public void modifyInfo(){
            System.out.println("请输入要修改学生的id");
            long id = scanner.nextLong();
            int y =0;
            for(int i = 0;i<count;i++){
                if(id == stuArray[i].getId()){
                    y = i;
                }
            }
            System.out.println("请输入要修改的信息:");
            boolean flag = true;
            while(flag){
                System.out.println("1、学生姓名");
                System.out.println("2、学生年龄");
                System.out.println("3、学生成绩");
                System.out.println("4、修改完成");
                int x = scanner.nextInt();
                switch (x){
                    case 1 :{
                        System.out.println("输入要修改的姓名");
                        String newName = scanner.next();
                        stuArray[y].setName(newName);
                        break;
                    }
                    case 2:{
                        System.out.println("输入要修改的年龄");
                        int newAge = scanner.nextInt();
                        stuArray[y].setAge(newAge);
                        break;
                    }
                    case 3 :{
                        System.out.println("输入要修改的成绩");
                        double newGrade = scanner.nextDouble();
                        stuArray[y].setGrade(newGrade);
                        break;
                    }
                    case 4:{
                        flag = false;
                    }
                }
            }
            System.out.println("学生信息修改完成");
            returnLast();
        }
        //返回父菜单
        public void returnMenu(){
            is.show();
        }
    }
