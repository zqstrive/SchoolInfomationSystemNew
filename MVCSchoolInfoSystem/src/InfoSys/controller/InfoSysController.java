package InfoSys.controller;

import InfoSys.test.InfoSysException;
import InfoSys.vo.Person;

import java.sql.SQLException;

public interface InfoSysController {
    /** 处理根据 ID 查询的动作 */        //i==4表示学生，i==5表示工人
    public void handleGetPersonGesture(Long id,int i) throws InfoSysException, SQLException;
    /** 处理添加的动作 */
    public void handleAddPersonGesture(Person person) throws SQLException;
    /** 处理删除的动作 */
    public void handleDeletePersonGesture(Person person) throws SQLException;
    /** 处理更新的动作 */
    public void handleUpdatePersonGesture(Person person) throws SQLException;
    /** 处理列出所有清单的动作 */         //i==4表示学生，i==5表示工人
    public void handleGetAllPersonGesture(int i) throws SQLException;
}
