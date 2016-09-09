package scratchlab.com.ph.medicinereminder.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import scratchlab.com.ph.medicinereminder.MainActivity;
import scratchlab.com.ph.medicinereminder.fragments.MedicineBox;
import scratchlab.com.ph.medicinereminder.R;
import scratchlab.com.ph.medicinereminder.adapters.ApolAdapter;
import scratchlab.com.ph.medicinereminder.extras.Information;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment{

    private RecyclerView recyclerView;
    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";

    //reference for ActionBarDrawerToggle
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private ApolAdapter adapter;

    private String title;
    private AppCompatActivity activity;
    private ActionBar actionBar;

    //2 boolean variables to indicate whether the user is aware of the drawer being started
    //from the very first time or is coming back from a rotation
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;

    //variable for openDrawer
    private View containerView;
    private boolean isDrawerOpened=false;

    public static String[] titles = {"Medicine Box", "Medicines", "Appointments", "Measurements"};

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

//Created manual method to check user_learned_drawer
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if(savedInstanceState!=null){
            mFromSavedInstanceState=true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView)layout.findViewById(R.id.drawerList);
        adapter = new ApolAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //PART of STEP2 TECHNIQUE3
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                FragmentManager fm= getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                switch (position){
                    case 0:
                        title = titles[0];
                        loadFragment();
                        Toast.makeText(getActivity(), "MedicineBox ", Toast.LENGTH_SHORT).show();
                        ft.replace(R.id.body, new MedicineBox());
                        ft.commit();

                        mDrawerLayout.closeDrawers();
                        break;

                    case 1:
                        title = titles[1];
                        loadFragment();

                        Toast.makeText(getActivity(), "Medicines ", Toast.LENGTH_SHORT).show();
                        ft.replace(R.id.body, new Medicines());
                        ft.commit();

                        mDrawerLayout.closeDrawers();
                        break;
                    case 2:

                        title = titles[2];
                        loadFragment();

                        Toast.makeText(getActivity(), "Appointments ", Toast.LENGTH_SHORT).show();
                        ft.replace(R.id.body, new Appointments());
                        ft.commit();

                        mDrawerLayout.closeDrawers();
//                        if (getActivity() instanceof MainActivity) {
//                            ((MainActivity) getActivity()).hideFloatingActionButton();
//                        }
//                        ((MainActivity) getActivity()).hideFloatingActionButton();

                        break;
                    case 3:
                        title = titles[3];
                        loadFragment();
                        Toast.makeText(getActivity(), "Measurements ", Toast.LENGTH_SHORT).show();
                        ft.replace(R.id.body, new Measurements());
                        ft.commit();

                        mDrawerLayout.closeDrawers();
//                        if (getActivity() instanceof MainActivity) {
//                            ((MainActivity) getActivity()).hideFloatingActionButton();}
//                       ((MainActivity) getActivity()).hideFloatingActionButton();
                        break;
                }

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "onLongClick "+position, Toast.LENGTH_SHORT).show();
            }
        }));
        return layout;
    }

    public void loadFragment(){
        activity = (AppCompatActivity)getActivity();
        actionBar = activity.getSupportActionBar();
        actionBar.setTitle(title);
    }

    public static List<Information> getData(){
        List<Information> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_number1,R.drawable.ic_number2,
                R.drawable.ic_number3, R.drawable.ic_number4};

        for(int i=0;i<titles.length && i<icons.length;i++)
        {
            Information current = new Information();
            current.setIconId(icons[i]);
            current.setTitle(titles[i]);
            data.add(current);
        }
        return data;
    }

    //note that this method has been automatically created once you add the code drawerFragment.setUp() in the MainActivity.java
    //int fragmentId has been added too
    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        //below codes are for DrawerToggle
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close){


            //Invalidate the activity's options menu, if able.
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer=true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
//you will notice the darkening of the toolbar
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if(slideOffset<0.6){
                    toolbar.setAlpha(1-slideOffset);
                }
            }
        };
        //set the container view
        if(!mUserLearnedDrawer && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }
        //ActionBarDrawerToggle syncState
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

//Interface used for modifying values in a SharedPreferences object. Using apply() Commit your preferences changes back
// from this Editor to the SharedPreferences object it is editing. This automatically performs the requested modifications,
// replacing whatever is currently in the SharedPreferences.
    public static  void saveToPreferences (Context context, String preferenceName, String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();

    }
    public static String readFromPreferences (Context context, String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }
    //Start of technique3 for recyclerview itemclick
    //Step1 Create a class that EXTENDS RecyclerView.OnItemTouchListener
    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        //reference to gesturedetector for step3
        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener){
            Log.d("APOL", "constructor invoked ");
            this.clickListener=clickListener;

            //STEP3 of TECHNIQUE3 Instantiate your gesturedetector, override the 2 methods
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    Log.d("APOL", "onSingleTapUp "+e);
                    // 4 Return true from the singleTap to indicate your GestureDetector has consumed the event.
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if(child!=null && clickListener!=null)
                    {
                       clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                    Log.d("APOL", "onLongPress "+e);
                }
                });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if(child!=null && clickListener!=null && gestureDetector.onTouchEvent(e))
            {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            Log.d("APOL", "onTouchEvent "+e);


        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
    //Step2 Create an interface inside that class that supports click and long click and indicates the View
    // that was clicked and the position where it was clicked
    public static interface ClickListener{
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }
}
