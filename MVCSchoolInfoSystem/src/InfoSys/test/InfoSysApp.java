package InfoSys.test;

import InfoSys.controller.InfoSysController;
import InfoSys.controller.InfoSysControllerImpl;
import InfoSys.model.InfoSysModel;
import InfoSys.model.InfoSysModelImpl;
import InfoSys.view.InfoSysView;
import InfoSys.view.InfoSysViewImpl;

public class InfoSysApp {
    public static void main(String args[]) throws Exception {

        InfoSysModel ism = new InfoSysModelImpl();
        InfoSysView isv = new InfoSysViewImpl(ism);
        InfoSysController iscon = new InfoSysControllerImpl(isv,ism);

    }
}
