package InfoSys.model;

import InfoSys.test.InfoSysException;
import InfoSys.view.InfoSysView;
import InfoSys.vo.Person;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InfoSysModel {
    /** 注册视图，以便当模型修改了数据库中的人员信息时，可以回调视图的刷新界面的方法 */
    public void addChangeListener(InfoSysView isv) throws InfoSysException;
    /** 向数据库中添加一个新的人员 */
    public void addPerson(Person person) throws InfoSysException, SQLException;
    /** 从数据库中删除一个人员 */
    public void deletePerson(Person person) throws InfoSysException, SQLException;
    /** 更新数据库中的人员 */
    public void updatePerson(Person person) throws InfoSysException, SQLException;
    /** 根据参数 id 检索人员 */
    public <T extends Person>T getPerson(Long id,int x) throws InfoSysException, SQLException;
//    /** 根据参数 id 检索职工 */
//    public worker getworomer(String id) throws InfoSysException;
    /** 返回数据库中所有的学生清单 */
    public ArrayList<? extends Person> getPersons(int i) throws InfoSysException, SQLException;
//    /** 返回数据库中所有的职工清单 */
//    public ArrayList<worker> getworker() throws InfoSysException;\
    /**     关闭数据库   */
    public void closeDataBase()throws InfoSysException,SQLException;
}