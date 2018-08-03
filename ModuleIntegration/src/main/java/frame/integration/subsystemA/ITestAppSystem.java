package frame.integration.subsystemA;

import frame.activity.GeneralActivity;
import frame.subsystem.ISubsystem;
import frame.integration.SubsystemAliasConstant;
import frame.subsystem.annotation.SubsystemAlias;

@SubsystemAlias(alias = SubsystemAliasConstant.TEST_APP)

public interface ITestAppSystem extends ISubsystem {

    void startLogin(GeneralActivity activity);
}