package demo.test.moduleB;

import android.os.Bundle;
import android.support.annotation.Nullable;

import frame.activity.GeneralActivity;

public class TestActivityB extends GeneralActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ta_activity_b_layout);
    }
}
