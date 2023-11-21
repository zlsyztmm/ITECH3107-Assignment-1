package au.edu.federation.itech3107.studentattendance30395696;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
