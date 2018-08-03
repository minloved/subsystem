package demo.test.moduleB.facadeImpl;

import android.content.Intent;

import demo.test.moduleB.TestActivityB;
import frame.activity.GeneralActivity;
import frame.integration.subsystemB.ITestBApp;

public class BTestAppSubsystemImpl implements ITestBApp {

    @Override
    public void startLogin(GeneralActivity activity) {
        Intent intent = new Intent(activity,TestActivityB.class);
        activity.startActivity(intent);
    }
}
