
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

/*
 *  配置子系统接入点 必须实现这个接口协议 已完成子系统安装流程
 */
public interface ISubsystemAppoint {

    void onCreate(Application application);

    void onInstall(ISubsystemInstall installer);
}