package frame.activity.F_DEMO;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import frame.activity.GeneralActivity;


public abstract class ZDEMOActivity extends GeneralActivity {

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);

        try {
            autoExecute();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public final void setContentView(View view) {
        super.setContentView(view);
        autoInjectView();
    }

    @Override
    public final void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        autoInjectView();
    }


    @Override
    public final void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        autoInjectView();
    }


    private void autoInjectView(){
        InjectViewTools.injectTo(getWindow().getDecorView(),this,GeneralActivity.class);
        onInjectDone();
    }


    protected void onInjectDone(){

    }


    protected void autoExecute() throws InvocationTargetException, IllegalAccessException {

        Method[] methods = this.getClass().getDeclaredMethods();

        for (Method m: methods){

            if (!m.isAnnotationPresent(Demo$Autowired.class)) continue;

            if(m.getParameterTypes().length > 0 ) new Exception(m.getName() +" is Demo$Autowired method and Can't has any parameter");

            m.invoke(this);

        }
    }
}
