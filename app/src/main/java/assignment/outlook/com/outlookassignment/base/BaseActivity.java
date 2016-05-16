package assignment.outlook.com.outlookassignment.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
    protected Activity mActivity = this;
    protected FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        fragmentManager = getSupportFragmentManager();
    }

    //This method for adding the fragment
    public void addFragment(Fragment fragement, Bundle args, String title) {

    }

    public void showSnackBar(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
