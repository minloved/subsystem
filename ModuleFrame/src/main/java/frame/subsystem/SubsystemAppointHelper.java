
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

package frame.subsystem;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import frame.library.R;

public final class SubsystemAppointHelper {

    public final void install(Application application) {

        SubsystemManagerImpl subsystemManager = new SubsystemManagerImpl();

        SubsystemAppointParser subsystemAppointParser = new SubsystemAppointParser();

        try {

            ISubsystemAppoint[] subsystemAppoints = subsystemAppointParser.parse(application);

            for (ISubsystemAppoint appoint: subsystemAppoints){

                appoint.onCreate(application);

                if (appoint instanceof ISubsystemAdmin){

                    appoint.onInstall(subsystemManager);

                }else{

                    appoint.onInstall(new SubsystemInstallProxy(subsystemManager));
                }
            }

        }catch (Throwable throwable) {

            throw new SubsystemInstallerError(throwable);
        }

        subsystemManager.markSubsystemInstallCompleted();
    }



    private static class SubsystemInstallProxy implements ISubsystemInstall {

        private ISubsystemInstall installer;

        public SubsystemInstallProxy(ISubsystemInstall installer) {
            this.installer = installer;
        }

        @Override
        public void install(Class<? extends ISubsystem> subsystem) {
            installer.install(subsystem);
        }

    }


    public static class SubsystemAppointParser {

        public ISubsystemAppoint[] parse(Application application) throws Throwable {

            ApplicationInfo appInfo = application.getPackageManager().getApplicationInfo(application.getPackageName(), PackageManager.GET_META_DATA);

            List<String> appointsList = new ArrayList<>();

            if (appInfo.metaData != null){

                Bundle bundle = appInfo.metaData;

                Set<String> keySet = bundle.keySet();
                Iterator<String> iterator = keySet.iterator();

                while (iterator.hasNext()){

                    String appointClassPath = iterator.next();
                    if (R.string.HH_CORE_subsystem_flag != bundle.getInt(appointClassPath)){
                        continue;
                    }

                    appointsList.add(appointClassPath);
                }
            }

            ISubsystemAppoint[] result = new ISubsystemAppoint[appointsList.size()];

            int index = 0;

            for (String appointClassPath: appointsList){

                result[index++] = SubsystemReflectTools.newInstance(null,appointClassPath);
            }

            return result;
        }
    }

}
