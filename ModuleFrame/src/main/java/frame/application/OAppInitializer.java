
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

/**
 * 如果App指定Application 不是继承OApplication  则需要使用 OAppInitializer 初始化
 */
public final class OAppInitializer implements AppCallback{

    public static void initialize(Application realApp){

        OApplication oApp = new OApplication() {
            @Override
            protected AppCallbackFactory getAppCallbackFactory() {

                return new AppCallbackFactory() {
                    @Override
                    public AppCallback factoryAppCallback() {
                        return new OAppInitializer();
                    }
                };
            }
        };

        oApp.initialize(realApp);
    }


    private OAppInitializer(){

    }

    @Override
    public void onCreate(Application application) {

        throw new UnsupportedOperationException("onCreate");
    }

    @Override
    public void onLowMemory(Application application) {

        throw new UnsupportedOperationException("onLowMemory");
    }

    @Override
    public void onConfigurationChanged(Application application, Configuration newConfig) {

        throw new UnsupportedOperationException("onConfigurationChanged");
    }

    @Override
    public void onTerminate(Application application) {

        throw new UnsupportedOperationException("onTerminate");
    }

    @Override
    public void onTrimMemory(Application application, int level) {

        throw new UnsupportedOperationException("onTrimMemory level"  + level);
    }
}
