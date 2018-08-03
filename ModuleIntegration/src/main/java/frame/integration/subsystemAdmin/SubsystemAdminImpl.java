package frame.integration.subsystemAdmin;


import frame.subsystem.ISubsystem;
import frame.logger.OLogger;
import frame.subsystem.ISubsystemManager;

class SubsystemAdminImpl implements SubsystemAdmin {

    private ISubsystemManager subsystemManager;

    public <T extends ISubsystem> T getSubsystem(String subsystemAlias){

        return this.subsystemManager.getSubsystem(subsystemAlias);
    }

     @Override
     public void print() {

         OLogger.logE("SubsystemAdmin callPrint");
     }

    @Override
    public  <E extends ISubsystem> E getSubsystem(Class<? extends ISubsystem> subsystem) {

        return this.subsystemManager.getSubsystem(subsystem);
    }

    void setSubsystemManager(ISubsystemManager subsystemManager) {

        this.subsystemManager = subsystemManager;
    }
}