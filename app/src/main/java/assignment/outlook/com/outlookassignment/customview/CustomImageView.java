package assignment.outlook.com.outlookassignment.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import assignment.outlook.com.outlookassignment.service.DownloadImageTask;

/**
 * Created by Rashmi Deokar on 1/15/2016.
 */
public class CustomImageView extends ImageView {
    private static final int LOADING_THREADS = 4;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(4);
    private DownloadImageTask currentTask;

    public CustomImageView(Context var1) {
        super(var1);
    }

    public CustomImageView(Context var1, AttributeSet var2) {
        super(var1, var2);
    }

    public CustomImageView(Context var1, AttributeSet var2, int var3) {
        super(var1, var2, var3);
    }

    public void setImageUrl(String var1) {
        this.setImage(new WebImage(var1));
    }

    public void setImageUrl(String var1, Integer var2) {
        this.setImage(new WebImage(var1), var2);
    }

    public void setImageUrl(String var1, Integer var2, Integer var3) {
        this.setImage(new WebImage(var1), var2, var3);
    }

   /* public void setImageContact(long var1) {
        this.setImage(new ContactImage(var1));
    }*/

   /* public void setImageContact(long var1, Integer var3) {
        this.setImage(new ContactImage(var1), var3);
    }
*/
    /*public void setImageContact(long var1, Integer var3, Integer var4) {
        this.setImage(new ContactImage(var1), var3, var3);
    }
*/
    public void setImage(CustomImage var1) {
        this.setImage(var1, (Integer) null, (Integer) null);
    }

    public void setImage(CustomImage var1, Integer var2) {
        this.setImage(var1, var2, var2);
    }

    public void setImage(CustomImage var1, final Integer var2, Integer var3) {
        if (var3 != null) {
            this.setImageResource(var3.intValue());
        }

        if (this.currentTask != null) {
            this.currentTask.cancel();
            this.currentTask = null;
        }

        this.currentTask = new DownloadImageTask(this.getContext(), var1);
        this.currentTask.setOnCompleteHandler(new DownloadImageTask.OnCompleteHandler() {
            public void onComplete(Bitmap var1) {
                if (var1 != null) {
                    CustomImageView.this.setImageBitmap(var1);
                } else if (var2 != null) {
                    CustomImageView.this.setImageResource(var2.intValue());
                }

            }
        });
        threadPool.execute(this.currentTask);
    }

    public static void cancelAllTasks() {
        threadPool.shutdownNow();
        threadPool = Executors.newFixedThreadPool(4);
    }
}