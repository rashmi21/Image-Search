package assignment.outlook.com.outlookassignment.base;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

public abstract class BaseViewHolder {

    public BaseActivity mActivity;
    public LayoutInflater mLayoutInflater;
    protected View mView;

    public BaseViewHolder(Activity activity) {
        this.mActivity = (BaseActivity) activity;
        this.mLayoutInflater = activity.getLayoutInflater();
    }

    public abstract void applyData();

    public abstract void initializeData(Object data);


}
