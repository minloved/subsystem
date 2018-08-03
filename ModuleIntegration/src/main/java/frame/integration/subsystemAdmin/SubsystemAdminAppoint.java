package frame.integration.subsystemAdmin;

import android.app.Application;

import frame.subsystem.ISubsystem;
import frame.logger.OLogger;
import frame.subsystem.ISubsystemAdmin;
import frame.subsystem.ISubsystemInstall;
import frame.subsystem.ISubsystemManager;
import frame.subsystem.ISubsystemAppoint;

public final class SubsystemAdminAppoint implements ISubsystemAppoint,ISubsystemAdmin{

    private static SubsystemAdminAppoint sSubsystemAdminAppoint = null;
    private ISubsystemManager mSubsystemManager;

    @Override
    public void onCreate(Application application) {
        sSubsystemAdminAppoint = this;
    }

    @Override
    public void onInstall(ISubsystemInstall installer) {

        OLogger.logE("SubsystemAdminAppoint onInstall");

        installer.install(SubsystemAdminImpl.class);

        ISubsystemManager subsystemManager = (ISubsystemManager) installer;
        SubsystemAdminImpl adminImpl = subsystemManager.getSubsystem(SubsystemAdminImpl.class);

        adminImpl.setSubsystemManager(subsystemManager);

        mSubsystemManager = subsystemManager;
    }


    public final static <T extends ISubsystem> T getSubsystem(String subsystemAlias){

        return sSubsystemAdminAppoint.mSubsystemManager.getSubsystem(subsystemAlias);
    }


    public final static <T extends ISubsystem> T getSubsystem(Class<? extends ISubsystem> subsystem){

        return sSubsystemAdminAppoint.mSubsystemManager.getSubsystem(subsystem);
    }
}
