package kids.urchin.home.app;

import frame.application.AppCallback;
import frame.application.AppCallbackFactory;

public class AppCallbackFactoryImpl implements AppCallbackFactory {
    @Override
    public AppCallback factoryAppCallback() {

        return new KidsAppCallback();
    }
}
