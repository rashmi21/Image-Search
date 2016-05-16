package assignment.outlook.com.outlookassignment.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import assignment.outlook.com.outlookassignment.customview.CustomImage;


/**
 * Created by Rashmi Deokar on 1/15/2016.
 */
public class DownloadImageTask implements Runnable {
    private static final int BITMAP_READY = 0;
    private boolean cancelled = false;
    private DownloadImageTask.OnCompleteHandler onCompleteHandler;
    private CustomImage image;
    private Context context;

    public DownloadImageTask(Context var1, CustomImage var2) {
        this.image = var2;
        this.context = var1;
    }

    public void run() {
        if(this.image != null) {
            this.complete(this.image.getBitmap(this.context));
            this.context = null;
        }

    }

    public void setOnCompleteHandler(DownloadImageTask.OnCompleteHandler var1) {
        this.onCompleteHandler = var1;
    }

    public void cancel() {
        this.cancelled = true;
    }

    public void complete(Bitmap var1) {
        if(this.onCompleteHandler != null && !this.cancelled) {
            this.onCompleteHandler.sendMessage(this.onCompleteHandler.obtainMessage(0, var1));
        }

    }

    public static class OnCompleteHandler extends Handler {
        public OnCompleteHandler() {
        }

        public void handleMessage(Message var1) {
            Bitmap var2 = (Bitmap)var1.obj;
            this.onComplete(var2);
        }

        public void onComplete(Bitmap var1) {
        }
    }
}
