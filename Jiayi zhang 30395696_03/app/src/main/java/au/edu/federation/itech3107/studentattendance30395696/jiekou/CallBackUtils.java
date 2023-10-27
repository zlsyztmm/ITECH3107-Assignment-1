package au.edu.federation.itech3107.studentattendance30395696.jiekou;

import au.edu.federation.itech3107.studentattendance30395696.entity.Course;

public class CallBackUtils {

    private static CallBack mCallBack;

    public static void setCallBack(CallBack callBack) {
        mCallBack = callBack;
    }

    public static void doCallBackMethod(Course msg){
        mCallBack.doSomeThing(msg);
    }
}
