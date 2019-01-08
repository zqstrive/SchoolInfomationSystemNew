package InfoSys.view;

import InfoSys.controller.InfoSysController;
import InfoSys.test.InfoSysException;
import InfoSys.vo.Person;

public interface InfoSysView {
    /** 注册处理用户动作的监听器，即 InfoSysController 控制器 */
    public void addUserGestureListener(InfoSysController ctrl)throws InfoSysException;
    /** 在图形界面上显示数据，参数 display 表示待显示的数据 */
    public void showDisplay(Object display)throws InfoSysException;
    /** 当模型层修改了数据库中某个人员的信息时，同步刷新视图层的图形界面 */
    public void handlePersonChange(Person person)throws InfoSysException;
    /** 当查询人员的详细信息时，没有在数据库中查询到此人，便刷新重新输入 */
    public void hanleNullException(int x)throws InfoSysException;
}
