# subsystem
模块化管理运行  近期面试被问到模块化相关的问题  撸了一个DEMO 
在模块化的基础上 可以指定相关模块独立运行 


1：ModuleIntegration模块功能 ： 管理各个子模块的Facade  定义子系统抽象的接口  这些接口均有子模块主动注入模块接口实现

2：CheckoutAppS.properties：文件配置加载不同的Module作为独立打包成APK的模块，并且在相应的模块gradle文件配置可运行的AndroidManifest即可

3：新模块 只需两部快速接入
    A： CheckoutAppS.properties：新增模块
    B： 只需要在该模块 AndroidManifest.xml文件内 在<application/> 标签下引入 如下配置
    
    
      <meta-data
            android:resource="@string/HH_CORE_subsystem_flag"
            android:name="***.SubsystemAdminAppoint">
       </meta-data>


注意⚠️ ： 
  可以独立运营的APP 在配置成独立运营时 XXXApplication 需要继承OApplicaition 或者 调用OAppInitializer.initialize（）
  SubsystemAdminAppoint implements ISubsystemAppoint{} 模块接入类需要实现相关接口
  该DEMO 没有考虑 混淆后找不到子系统的问题  如需混淆 请keep相关接口以及实现
