package kids.urchin.home.app;

import frame.application.AppCallbackFactory;
import frame.application.OApplication;

public final class LiveApplication extends OApplication {


    @Override
    protected AppCallbackFactory getAppCallbackFactory() {

        return new AppCallbackFactoryImpl();
    }


    @Override
    public void onCreate() {
        super.onCreate();

        //进程共享资源初始化

    }


    @Override
    public void onTerminate() {
        super.onTerminate();


    }
}
