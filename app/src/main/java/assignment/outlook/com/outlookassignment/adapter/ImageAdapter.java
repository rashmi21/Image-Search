package assignment.outlook.com.outlookassignment.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import assignment.outlook.com.outlookassignment.R;
import assignment.outlook.com.outlookassignment.holder.ImageSearchHolder;
import assignment.outlook.com.outlookassignment.pojo.Page;

/**
 * Created by Rashmi Deokar on 16-05-2016.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageSearchHolder> {

    private final Activity mActivity;
    private List<Page> moviesList;

    public ImageAdapter(Activity activity, List<Page> moviesList) {
        mActivity = activity;
        this.moviesList = moviesList;
    }


    public View getConvertView(Activity activity) {
        View inflate = activity.getLayoutInflater().inflate(R.layout.item_image_search, null);
        return inflate;
    }

    @Override
    public ImageSearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       /* View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);*/

        return new ImageSearchHolder(mActivity,getConvertView(mActivity));
    }

    @Override
    public void onBindViewHolder(ImageSearchHolder holder, int position) {
        Page page = moviesList.get(position);
        holder.initializeData(page);
        holder.applyData();
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}