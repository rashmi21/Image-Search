package assignment.outlook.com.outlookassignment.activity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import assignment.outlook.com.outlookassignment.R;
import assignment.outlook.com.outlookassignment.adapter.ImageAdapter;
import assignment.outlook.com.outlookassignment.base.BaseActivity;
import assignment.outlook.com.outlookassignment.pojo.ImageSearchRes;
import assignment.outlook.com.outlookassignment.pojo.Page;
import assignment.outlook.com.outlookassignment.pojo.Query;
import assignment.outlook.com.outlookassignment.pojo.Thumbnail;
import assignment.outlook.com.outlookassignment.service.SyncService;

public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private List<Page> mImgSearchList = new ArrayList<Page>();
    private ImageAdapter mAdapter;
    private SearchView mSVImage;
    private View mPbImageProgress;
    private SearchImages mSearchImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initView();
        setData();
        setListener();
    }

    private void setListener() {
        mSVImage.setOnQueryTextListener(this);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mSVImage = (SearchView) findViewById(R.id.sv_image);
        mPbImageProgress = findViewById(R.id.pb_image_search);
    }

    private void setData() {
        mAdapter = new ImageAdapter(mActivity, mImgSearchList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mSVImage.setIconified(false);
    }


    private void updateList(List<Page> list) {
        mImgSearchList.clear();
        mImgSearchList.addAll(list);
        Collections.sort(mImgSearchList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
//        Log.e("onQueryTextSubmit", query);
        searchImage(query);
        return true;
    }

    private void searchImage(String query) {
        hideKeyboard();
//        mSVImage.setIconified(true);
        mSearchImages = new SearchImages(mActivity);
        mSearchImages.execute(query);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mSVImage.getWindowToken(), 0);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("onQueryTextChange", newText);
        return true;
    }


    public class SearchImages extends AsyncTask<String, Void, List<Page>> {

        private static final String TAG = "SearchImages";
        private Activity activity;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mPbImageProgress.setVisibility(View.VISIBLE);
        }

        public SearchImages(Activity mActivity) {
            this.activity = mActivity;
        }


        @Override
        protected List<Page> doInBackground(String... params) {
            SyncService syncService = SyncService.getInstance(activity);
            ImageSearchRes imageSearchRes = syncService.searchImg(params[0]);
            List<Page> pagesList = new ArrayList<Page>();
            if (imageSearchRes != null) {
                Query query = imageSearchRes.getQuery();
                if (query != null) {
                    Object pages = query.getPages();
                    if (pages != null) {
                        // place your json format here in double Quotes with proper escapes .......
                        try {
                            JSONObject jObject = new JSONObject(new Gson().toJson(pages));
                            Iterator<?> keys = jObject.keys();
                            while (keys.hasNext()) {
                                String key = (String) keys.next();
                                if (jObject.get(key) instanceof JSONObject) {
                                    // do what ever you want with the JSONObject.....
                                    JSONObject jsonObject = (JSONObject) jObject.get(key);
                                    Page page = new Page();
                                    page.setTitle(jsonObject.getString("title"));
                                    page.setIndex((int) jsonObject.getDouble("index"));
                                    if (jsonObject.has("thumbnail")) {
                                        Thumbnail thumbnail = new Gson().fromJson(jsonObject.get("thumbnail").toString(),
                                                Thumbnail.class);
                                        page.setThumbnail(thumbnail);
                                    }
                                    pagesList.add(page);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return pagesList;
        }

        @Override
        protected void onPostExecute(List<Page> result) {
            super.onPostExecute(result);
            mPbImageProgress.setVisibility(View.GONE);
            updateList(result);
            if (result.size() == 0) {
                Toast.makeText(mActivity, getString(R.string.no_result_found), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSearchImages != null) {
            mSearchImages.cancel(true);
        }
    }
}