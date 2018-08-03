package demo.test.moduleA.subsystem;

import android.content.Intent;

import demo.test.moduleA.TestActivity;
import frame.activity.GeneralActivity;
import frame.integration.subsystemA.ITestAppSystem;

public class TestAppSystemImpl implements ITestAppSystem {

    @Override
    public void startLogin(GeneralActivity activity) {
        Intent intent = new Intent(activity,TestActivity.class);
        activity.startActivity(intent);
    }
}
