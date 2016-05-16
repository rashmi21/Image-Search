package assignment.outlook.com.outlookassignment.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import assignment.outlook.com.outlookassignment.R;
import assignment.outlook.com.outlookassignment.pojo.ImageSearchRes;
import assignment.outlook.com.outlookassignment.utils.HttpUtils;
import assignment.outlook.com.outlookassignment.utils.OAConstants;


/**
 * This class contains all the calling & retrieval from server. this is a
 * singleton class so that same data can be access from everywhere.
 *
 * @author Rashmi Deokar
 */
public class SyncService {
    private static final String TAG = SyncService.class.getName();
    private static SyncService singleInstance = null;
    private Context mContext;
    private HttpUtils mHttpUtil;


    public static SyncService getInstance(Context context) {
        if (singleInstance == null)
            singleInstance = new SyncService(context);
        singleInstance.mContext = context;
        singleInstance.mHttpUtil.updateContext(context);
        return singleInstance;
    }

    private SyncService() {
    }

    private SyncService(Context context) {
        mContext = context;
        mHttpUtil = new HttpUtils(mContext);
    }

    public ImageSearchRes searchImg(
            String key) {
        int size = (int) mContext.getResources().getDimension(R.dimen.image_size);
        String URL = OAConstants.WIKI_SEARCH_IMG_FIRST_URL + size + OAConstants.WIKI_SEARCH_IMG_SECOND_URL + key;
        String result = mHttpUtil.doGetRaw(URL);
        if (!TextUtils.isEmpty(result)) {
            Log.e(TAG, result);
            ImageSearchRes resultObj = new Gson().fromJson(result,
                    ImageSearchRes.class);
            return resultObj;
        }
        return null;
    }

}
