# subsystem
模块化管理运行  近期面试被问到模块化相关的问题   撸了一个模块化的基础上  增加独立编译运行的 DEMO 


ModuleIntegration模块功能 ： 管理各个子模块的Facade  定义子系统抽象的接口  这些接口均有子模块主动注入模块接口实现

CheckoutAppS.properties 文件配置 加载不同的Module 作为独立打包成APK的模块 

该DEMO 没有考虑 混淆后找不到子系统的问题  如需混淆 请keep相关接口以及实现
