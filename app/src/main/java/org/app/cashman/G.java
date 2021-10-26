package org.app.cashman;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

public class G extends Application {
    public static LayoutInflater inflater;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
}
