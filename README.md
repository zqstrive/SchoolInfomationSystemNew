# SchoolInfomationSystemNew
这是人员信息管理系统的阶段一，用数组实现
设计要求：
        设计一个简单的学校人员信息管理系统，可以管理学生与职工。ID、姓名、年龄为公共信息，而学生有成绩，职工有工资和工作。
        定义一个抽象类Person(ID,姓名,年龄),学生是其子类，有成绩，职工是其子类有工资和工作。
功能要求：
1、可以对学生和职工信息进行增删改查
    手工输入ID
    根据ID进行精确查询
    可以更新ID以外的信息
    根据ID进行删除
2、以命令行菜单形式供用户使用。如下所示：
     学校信息管理系统
            1、学生信息管理 
            2、工人信息管理
            3、退出系统
          
     学生信息管理
            1、增加学生信息
            2、列出全部学生信息
            3、查询学生信息
            4、删除学生信息
            5、修改学生信息
            6、返回上一级菜单
            
 设计思路：
    1、由于假设学生与职工的人数都不超过10，故使用数组可以直接定义最大人数10，同时定义静态变量count 用于计数当前人数
    2、定义三个菜单，主菜单，两个分菜单，并且绑定他们，可以自如的返回上一级菜单并且数据不丢失
    3、增加人数判断id是否重复
    4、删除人数定义一个新数组来进行转移，比较方便
 更多细节见代码
