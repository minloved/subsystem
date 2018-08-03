
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

import java.util.HashMap;
import java.util.Map;

import frame.logger.OLogger;
import frame.subsystem.annotation.Singleton;
import frame.subsystem.annotation.SubsystemAlias;

final class SubsystemManagerImpl implements ISubsystemManager {

    //别名 ---- >> 接口 映射
    private Map<String,Class<? extends ISubsystem>> mSubsystemAliasMapping = new HashMap<>();
    //接口 ---- >> 实现类 映射
    private Map<Class<? extends ISubsystem>,Class<? extends ISubsystem>> mSubsystemIMPLMapping = new HashMap<>();
    // 实现类实例
    private Map<Class<? extends ISubsystem>,ISubsystem> mSubsystemInstance = new HashMap<>();

    private boolean markInstallCompleted = false;

    SubsystemManagerImpl(){
    }

    void markSubsystemInstallCompleted(){
        markInstallCompleted = true;
    }

    /**
     *
     * 该类的直接实现接口 只能一个 否者抛出异常
     * 添加实现类，向上查找注解作为Key值
     * 若没有找到注解 默认为该类实现的唯一接口全名
     */
    @Override
    public void install(Class<? extends ISubsystem> subsystemIMPL) {

        if (markInstallCompleted){
            throw new RuntimeException("子系统安装已经结束，不能再继续安装");
        }

        checkSubsystemRule(subsystemIMPL);

        Class<? extends ISubsystem> subsystem = (Class<? extends ISubsystem>) subsystemIMPL.getInterfaces()[0];
        SubsystemAlias alias = subsystem.getAnnotation(SubsystemAlias.class);

        String subsystemAlias;
        if (alias != null){
            subsystemAlias = alias.alias();
        }else{
            OLogger.logW(subsystem.getName()+ " is not AnnotationPresent SubsystemAlias");
            subsystemAlias = subsystem.getName();
        }

        if (mSubsystemAliasMapping.containsKey(subsystemAlias)){
            throw new RuntimeException("repeat subsystem " + subsystemIMPL.getName());
        }

        mSubsystemAliasMapping.put(subsystemAlias, subsystem);

        mSubsystemIMPLMapping.put(subsystem, subsystemIMPL);
    }

    @Override
    public void uninstall(Class<? extends ISubsystem> subsystem) {
        if (markInstallCompleted){
            throw new RuntimeException("子系统安装没有结束，暂时不能卸载");
        }
        throw new RuntimeException("虽然很简单，暂时不提供卸载功能");
    }

    @Override
    public <T extends ISubsystem> T getSubsystem(String subsystemAlias) {

        Class<? extends ISubsystem> subsystemII = mSubsystemAliasMapping.get(subsystemAlias);

        return getSubsystem(subsystemII);
    }

    @Override
    public <E extends ISubsystem> E getSubsystem(Class<? extends ISubsystem> subsystem) {

        ISubsystem subsystemInstance = mSubsystemInstance.get(subsystem);

        if (subsystemInstance != null){
            return (E)subsystemInstance;
        }
        
        Class<? extends ISubsystem> subsystemIMPL = mSubsystemIMPLMapping.get(subsystem);
        if (subsystemIMPL == null
                || subsystemIMPL.getInterfaces()[0] != subsystem){
            throw new RuntimeException(subsystem.getName() + "不支持类型");
        }

        subsystemInstance = SubsystemReflectTools.newInstance(subsystemIMPL);

        if (subsystem.isAssignableFrom(Singleton.class)
                || subsystemIMPL.isAssignableFrom(Singleton.class)) {
            mSubsystemInstance.put(subsystem,subsystemInstance);
        }

        return (E) subsystemInstance;

    }

    private static final void checkSubsystemRule(Class<? extends ISubsystem> subsystemIMPL){

        Class<?> superClass = subsystemIMPL.getSuperclass();
        if (superClass != Object.class){
            new RuntimeException(subsystemIMPL.getName() + " 只能直接继承Object类");
        }

        Class[] superInterfaces = subsystemIMPL.getInterfaces();
        if (superInterfaces.length != 1){
            throw new RuntimeException(subsystemIMPL.getName() + " 只能直接实现一个接口");
        }

        if (superInterfaces[0] == ISubsystem.class){
            throw new RuntimeException(subsystemIMPL.getName() + " 没有提供任何功能");
        }
    }

//    private static class IMPL{
//
//        Class<? extends ISubsystem> subsystem;
//
//        List<? extends ISubsystem> subsystemIMPL;
//    }

}
