package demo.test.moduleB.facadeImpl;

import android.app.Application;

import frame.subsystem.ISubsystemAppoint;
import frame.subsystem.ISubsystemInstall;

public class TestSubsystemAppoint implements ISubsystemAppoint {

    @Override
    public void onCreate(Application application) {

    }

    @Override
    public void onInstall(ISubsystemInstall installer) {
        installer.install(BTestAppSubsystemImpl.class);
    }
}
