package assignment.outlook.com.outlookassignment.holder;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import assignment.outlook.com.outlookassignment.R;
import assignment.outlook.com.outlookassignment.customview.CustomImageView;
import assignment.outlook.com.outlookassignment.pojo.Page;
import assignment.outlook.com.outlookassignment.pojo.Thumbnail;

/**
 * Created by Rashmi Deokar on 16-05-2016.
 */
public class ImageSearchHolder extends RecyclerView.ViewHolder {

    private Page item;
    private CustomImageView ivSearch;
    private TextView tvTitle;

    public ImageSearchHolder(Activity activity, View view) {
        super(view);
        ivSearch = (CustomImageView) view.findViewById(R.id.iv_search);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
    }

    public void applyData() {
        tvTitle.setText(item.getTitle());
        Thumbnail thumbnail = item.getThumbnail();
        if (thumbnail != null) {
            String url = thumbnail.getSource();
            if (!TextUtils.isEmpty(url)) {
                ivSearch.setImageUrl(url);
            } else {
                ivSearch.setImageResource(R.drawable.image_holder);
            }
        }
//        ivSearch.setImageResource();
    }

    public void initializeData(Object data) {
        item = (Page) data;
    }

}
