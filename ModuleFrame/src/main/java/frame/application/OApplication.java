
/*
 * Copyright 2017 Gary YUZHANG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package frame.application;

import android.app.Application;
import android.content.res.Configuration;

import frame.subsystem.SubsystemAppointHelper;

public abstract class OApplication extends Application {


    private static Application sRealApp;
    private AppCallback appCallback;

    public static final Application getRealApp(){
        return sRealApp;
    }

    /**
     * 如果启动配置的Application 不是继承该类 需要主动触发
     */

    void initialize(Application realApp){

        if (sRealApp != null)return;
        sRealApp = realApp;

        new SubsystemAppointHelper().install(sRealApp);
    }


    @Override
    public void onCreate() {

        super.onCreate();

        initialize(this);

        AppCallbackFactory factory = getAppCallbackFactory();
        appCallback = factory.factoryAppCallback();

        appCallback.onCreate(sRealApp);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        appCallback.onLowMemory(sRealApp);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        appCallback.onConfigurationChanged(sRealApp,newConfig);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        appCallback.onTerminate(sRealApp);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

        appCallback.onTrimMemory(sRealApp,level);
    }

    protected abstract AppCallbackFactory getAppCallbackFactory();

}
