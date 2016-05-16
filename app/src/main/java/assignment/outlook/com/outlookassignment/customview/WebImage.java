package assignment.outlook.com.outlookassignment.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Rashmi Deokar on 1/15/2016.
 */
public class WebImage implements CustomImage {
    private static final int CONNECT_TIMEOUT = 5000;
    private static final int READ_TIMEOUT = 10000;
    private static WebImageCache webImageCache;
    private String url;

    public WebImage(String var1) {
        this.url = var1;
    }

    public Bitmap getBitmap(Context var1) {
        if (webImageCache == null) {
            webImageCache = new WebImageCache(var1);
        }

        Bitmap var2 = null;
        if (this.url != null) {
            var2 = webImageCache.get(this.url);
            if (var2 == null) {
                var2 = this.getBitmapFromUrl(this.url);
                if (var2 != null) {
                    webImageCache.put(this.url, var2);
                }
            }
        }

        return var2;
    }

    private Bitmap getBitmapFromUrl(String var1) {
        Bitmap var2 = null;

        try {
            URLConnection var3 = (new URL(var1)).openConnection();
            var3.setConnectTimeout(5000);
            var3.setReadTimeout(10000);
            var2 = BitmapFactory.decodeStream((InputStream) var3.getContent());
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return var2;
    }

    public static void removeFromCache(String var0) {
        if (webImageCache != null) {
            webImageCache.remove(var0);
        }

    }
}
