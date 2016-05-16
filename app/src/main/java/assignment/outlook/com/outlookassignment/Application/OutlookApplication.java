package assignment.outlook.com.outlookassignment.Application;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class OutlookApplication extends Application {

    private DisplayMetrics displayMetrics;

    public OutlookApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        displayMetrics = getDisplayMetrics();
    }

    private DisplayMetrics getDisplayMetrics() {
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return metrics;
    }

    public int deviceWidth() {
        return displayMetrics.widthPixels;
    }

    public int deviceHeight() {
        return displayMetrics.heightPixels;
    }

}