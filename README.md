# subsystem
模块化管理运行  近期面试被问到模块化相关的问题  撸了一个DEMO 
在模块化的基础上 可以指定相关模块独立运行 


1： ModuleIntegration模块功能 ： 管理各个子模块的Facade  定义子系统抽象的接口  这些接口均有子模块主动注入模块接口实现

2：CheckoutAppS.properties：  文件配置 加载不同的Module 作为独立打包成APK的模块 

3:新模块 只需两部快速接入
    A： CheckoutAppS.properties：新增模块
    B： 只需要在该模块 AndroidManifest.xml文件内 在<application/> 标签下引入 如下配置
    
    
      <meta-data
            android:resource="@string/HH_CORE_subsystem_flag"
            android:name="***.SubsystemAdminAppoint">
       </meta-data>
//⚠️ ：SubsystemAdminAppoint implements ISubsystemAppoint{} 该类需要实现相关接口

注意⚠️ ： 该DEMO 没有考虑 混淆后找不到子系统的问题  如需混淆 请keep相关接口以及实现
ISubsystemAppoint
