package frame.activity.F_DEMO;

import android.view.View;

import java.lang.reflect.Field;

import frame.logger.OLogger;

public class InjectViewTools {

    public static void injectTo(View rootView, Object objTarget, Class untilSupperClass){

        Class<?> cls = objTarget.getClass();


        InjectView tempInjectView;
        View tempFindView;

        while (cls != untilSupperClass){

            Field[] allChildFields = cls.getDeclaredFields();
            for (Field childField : allChildFields){

                if (!View.class.isAssignableFrom(childField.getType())){
                    OLogger.logI("TAG",
                                "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz",
                            "continue \ncontinue 22222","continue continue \ncontinue 3333","continue continue 444444");
                    continue;
                }

                OLogger.logI("go on");

                tempInjectView = childField.getAnnotation(InjectView.class);
                if (tempInjectView == null || tempInjectView.viewId() <= 0)continue;

                tempFindView = rootView.findViewById(tempInjectView.viewId());

                boolean accessible = childField.isAccessible();
                childField.setAccessible(true);
                try {
                    childField.set(objTarget,tempFindView);
                } catch (IllegalAccessException e) {
                    OLogger.logE(e.toString());
                }
                childField.setAccessible(accessible);
            }
            cls = cls.getSuperclass();
        }
    }
}
