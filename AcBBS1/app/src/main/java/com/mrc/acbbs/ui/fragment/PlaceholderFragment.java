package com.mrc.acbbs.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mrc.acbbs.App;
import com.mrc.acbbs.R;
import com.mrc.acbbs.Value;
import com.mrc.acbbs.adapter.FeedCardAdapter;
import com.mrc.acbbs.adapter.viewAdapter.NormalAdapterViewAdapter;
import com.mrc.acbbs.common.Constant;
import com.mrc.acbbs.common.util.FetchUtil;
import com.mrc.acbbs.engine.Engine;
import com.mrc.acbbs.entity.ForumObject;
import com.mrc.acbbs.entity.JsonEntity;
import com.mrc.acbbs.entity.TitleFirst;
import com.mrc.acbbs.ui.activity.MainActivity;
import com.mrc.acbbs.util.DataStatusChecker;
import com.mrc.acbbs.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildLongClickListener;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGAStickinessRefreshViewHolder;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;

public class PlaceholderFragment extends Fragment implements BGAOnItemChildClickListener, BGAOnItemChildLongClickListener,BGARefreshLayout.BGARefreshLayoutDelegate, Callback<List<ForumObject>> {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";
	private static final String TAG ="FEED";
    private BGARefreshLayout mRefreshLayout;
	Integer sectionNum;
	private NormalAdapterViewAdapter mAdapter;
    App mApp = App.getInstance();
    Engine engine = mApp.getEngine();
    ListView mListView;
    private SweetAlertDialog mLoadingDialog;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static PlaceholderFragment newInstance(int sectionNumber) {
		PlaceholderFragment fragment = new PlaceholderFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public PlaceholderFragment() {
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
        initRefreshLayout(mRefreshLayout, rootView);
        mListView = (ListView) rootView.findViewById(R.id.content_flow_list);
        getForum( 0 + "");
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		sectionNum = new Integer(getArguments().getInt(ARG_SECTION_NUMBER));
		((MainActivity) activity).onSectionAttached(sectionNum);
	}

    private void initRefreshLayout(BGARefreshLayout refreshLayout,View rootView) {
        mRefreshLayout = (BGARefreshLayout) rootView.findViewById(R.id.rl_main_fragmet_refresh);
        // 为BGARefreshLayout设置代理
        mRefreshLayout.setDelegate(this);

		mAdapter = new NormalAdapterViewAdapter(this.getActivity());
		mAdapter.setOnItemChildClickListener(this);
		mAdapter.setOnItemChildLongClickListener(this);

        BGAStickinessRefreshViewHolder stickinessRefreshViewHolder = new BGAStickinessRefreshViewHolder(mApp, true);
        stickinessRefreshViewHolder.setStickinessColor(R.color.colorPrimary);
        stickinessRefreshViewHolder.setRotateImage(R.mipmap.bga_refresh_stickiness);

        mRefreshLayout.setRefreshViewHolder(stickinessRefreshViewHolder);
    }



    private void getForum(final String page){
        if (Value.getTitleSecondsArray() == null) {
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
                    Log.i(TAG, "检查完成，开始加载串");
                    showLoadingDialog();
                    Call<List<ForumObject>> call = engine.loadForums(Value.getTitleSecondsArray().get(sectionNum - 1).getId() + "", page);
                    call.enqueue(PlaceholderFragment.this);
                }
            }));
        }else {
            Log.i(TAG, "板块列表已存在，开始加载串");
            showLoadingDialog();
            Call<List<ForumObject>> call = engine.loadForums(Value.getTitleSecondsArray().get(sectionNum - 1).getId() + "", page);
            call.enqueue(PlaceholderFragment.this);
        }
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        getForum(0 + "");
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


	@Override
	public void onItemChildClick(ViewGroup viewGroup, View view, int i) {

	}

	@Override
	public boolean onItemChildLongClick(ViewGroup viewGroup, View view, int i) {
		return false;
	}

    public void showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new SweetAlertDialog(this.getActivity(), SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimary));
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setTitleText("数据加载中...");
        }
        mLoadingDialog.show();
    }

    public void dismissLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void onResponse(Call<List<ForumObject>> call, Response<List<ForumObject>> response) {
        List<ForumObject> body = response.body();
        mListView.setAdapter(new FeedCardAdapter(getActivity(),new ArrayList (body)));
        Log.i(TAG, "获取串成功");
        mRefreshLayout.endRefreshing();
        dismissLoadingDialog();
    }

    @Override
    public void onFailure(Call<List<ForumObject>> call, Throwable t) {
        Log.i(TAG,"获取串失败");
        mRefreshLayout.endRefreshing();
        dismissLoadingDialog();
    }
}