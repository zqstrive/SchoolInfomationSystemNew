package Operate;

//人员信息操作接口
public interface PersonOperate {
    public void add() throws Exception;
    public void delete() throws Exception;
    public void update() throws Exception;
    public void findAll() throws Exception;
    public void findByLike() throws Exception;
}
