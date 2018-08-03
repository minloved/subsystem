package frame.integration.subsystemAdmin;


import frame.subsystem.ISubsystem;
import frame.subsystem.annotation.SubsystemAlias;
import frame.integration.SubsystemAliasConstant;

@SubsystemAlias(alias = SubsystemAliasConstant.INTEGRATION)
public interface SubsystemAdmin extends ISubsystem {

    <T extends ISubsystem> T getSubsystem(String subsystemAlias);

    void print();

    <E extends ISubsystem> E getSubsystem(Class<? extends ISubsystem> subsystem);
    
}