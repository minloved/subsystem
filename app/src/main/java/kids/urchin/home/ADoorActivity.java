package kids.urchin.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import frame.activity.F_DEMO.ZDEMOActivity;
import frame.integration.subsystemAdmin.SubsystemAdminAppoint;
import frame.integration.SubsystemAliasConstant;
import frame.integration.subsystemA.ITestAppSystem;


public class ADoorActivity extends ZDEMOActivity {

    public static void startDoorActivity(Activity activity){
        Intent intent = new Intent(activity,ADoorActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Button view = findViewById(R.id.text);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ITestAppSystem testApp = SubsystemAdminAppoint.getSubsystem(SubsystemAliasConstant.TEST_APP);
                testApp.startLogin(ADoorActivity.this);
            }
        });
    }
}