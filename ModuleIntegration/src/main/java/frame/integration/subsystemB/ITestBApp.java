package frame.integration.subsystemB;

import frame.activity.GeneralActivity;
import frame.subsystem.ISubsystem;
import frame.integration.SubsystemAliasConstant;
import frame.subsystem.annotation.SubsystemAlias;

@SubsystemAlias(alias = SubsystemAliasConstant.TEST_APP_B)

public interface ITestBApp extends ISubsystem {

    void startLogin(GeneralActivity activity);
}