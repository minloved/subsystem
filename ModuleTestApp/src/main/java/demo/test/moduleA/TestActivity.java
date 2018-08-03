package demo.test.moduleA;

import android.os.Bundle;
import android.support.annotation.Nullable;

import demo.test.moduletestapp.R;
import frame.activity.GeneralActivity;

public class TestActivity extends GeneralActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ta_activity_layout);
    }
}
