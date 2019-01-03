package Test;

import Operate.FileOperate;
import Operate.InfoOperate;

public class SchoolInfoSystem {
    public static void main(String args[]) throws Exception {
        //测试类
        FileOperate fo = new FileOperate();
        InfoOperate infoo = new InfoOperate(fo);
        infoo.show();
    }
}
