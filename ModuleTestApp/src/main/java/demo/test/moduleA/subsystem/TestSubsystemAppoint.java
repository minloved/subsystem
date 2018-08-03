package demo.test.moduleA.subsystem;

import android.app.Application;

import frame.subsystem.ISubsystemInstall;
import frame.subsystem.ISubsystemAppoint;

public class TestSubsystemAppoint implements ISubsystemAppoint {

    @Override
    public void onCreate(Application application) {

    }

    @Override
    public void onInstall(ISubsystemInstall installer) {
        installer.install(TestAppSystemImpl.class);
    }
}
