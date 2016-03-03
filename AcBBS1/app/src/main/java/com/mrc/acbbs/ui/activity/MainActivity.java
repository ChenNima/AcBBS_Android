package com.mrc.acbbs.ui.activity;


import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mrc.acbbs.App;
import com.mrc.acbbs.R;
import com.mrc.acbbs.Value;
import com.mrc.acbbs.engine.Engine;
import com.mrc.acbbs.entity.TitleFirst;
import com.mrc.acbbs.entity.TitleSecond;
import com.mrc.acbbs.ui.fragment.NavigationDrawerFragment;
import com.mrc.acbbs.ui.fragment.PlaceholderFragment;
import com.mrc.acbbs.util.DataStatusChecker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;

public class MainActivity extends Activity implements
	NavigationDrawerFragment.NavigationDrawerCallbacks{

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
    protected static int retryTime=10;
	private CharSequence mTitle;

	private static final String TAG ="MAIN_ACTIVITY";
	
	int sectionNum;
	Engine engine = App.getEngine();
    Call<List<TitleFirst>> call;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number) {
		sectionNum = number;
		if(Value.getTitleSecondsArray()==null){
		App.getForumListObservable().subscribe((new Subscriber<List<TitleFirst>>() {

			@Override
			public void onCompleted() {
			}

			@Override
			public void onError(Throwable e) {
				Log.i(TAG, "检查失败");
			}

			@Override
			public void onNext(List<TitleFirst> titleFirsts) {
				mTitle = Value.getTitleSecondsArray().get(sectionNum-1).getName();
				Log.i(TAG, "检查完成，加载标题"+mTitle);
			}
		}));
		}else {
			mTitle = Value.getTitleSecondsArray().get(sectionNum-1).getName();
			Log.i(TAG, "板块列表已存在，加载标题"+mTitle);
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
