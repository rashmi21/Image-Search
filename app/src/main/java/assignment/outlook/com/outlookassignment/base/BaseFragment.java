package assignment.outlook.com.outlookassignment.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

import assignment.outlook.com.outlookassignment.R;

public class BaseFragment extends Fragment {

    public BaseFragment() {
        super();
    }

    private static final String TAG = BaseFragment.class.getSimpleName();

    public BaseActivity mActivity;
    protected View mView;
    protected FragmentManager mFragmentManager;

    /**
     * Key for the title that will be shown on the action bar when this fragment
     * is displayed
     */
    public static final String FRAGMENT_TITLE = "fragment_title";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        mFragmentManager = getChildFragmentManager();

    }


    public void showSnackBar(String msg) {
        mActivity.showSnackBar(msg);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getChildFragmentManager()
                .beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.container_framelayout, fragment);
        transaction.commit();
        getChildFragmentManager().executePendingTransactions();
    }

    public boolean popChildFragment() {
        boolean isPop = false;
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            isPop = true;
            getChildFragmentManager().popBackStack();
        }
        return isPop;
    }

    public boolean popFragment() {
        boolean isPop = false;
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            isPop = true;
            getFragmentManager().popBackStack();
        }
        return isPop;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mFragmentCallbacks = (FragmentCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FragmentCallbacks");
        }
    }

    /**
     * Contain methods(called from fragments) that are to be executed inside the
     * parent activity. <br>
     * Any {@link Activity} that wants to listen for callbacks from the fragment
     * should implement this interface.
     */
    public interface FragmentCallbacks {
        /**
         * Shows the fragment with given name inside the activity.
         *
         * @param className fully qualified class name of the fragment you want to
         *                  show
         * @param title     title to be displayed on the action bar
         * @param args      arguments to be passed to the fragment
         * @return the fragment being shown
         */
        Fragment showFragemnt(String className, String title, Bundle args);
    }

    private FragmentCallbacks mFragmentCallbacks = null;

    /**
     * Returns an instance of the {@link FragmentCallbacks} interface,
     * <code>page_tour</code> if the fragment is not attached to the activity
     */
    protected FragmentCallbacks getFragmentCallbacks() {
        return this.mFragmentCallbacks;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // Workaround for java.lang.IllegalStateException: No activity(support
        // library fragment class)
        // Basically, the child FragmentManager ends up with a broken internal
        // state when it is detached from the activity. A short-term workaround
        // that fixed it for me is to add the following to onDetach() of every
        // Fragment
        // which you call getChildFragmentManager() on
        try {
            Field childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public void setFragmentTitle(String title) {
        mActivity.setTitle(title);
    }

}