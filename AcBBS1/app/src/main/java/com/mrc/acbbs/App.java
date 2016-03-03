package com.mrc.acbbs;

import android.app.Application;
import android.util.Log;

import com.mrc.acbbs.common.Constant;
import com.mrc.acbbs.engine.Engine;
import com.mrc.acbbs.entity.TitleFirst;
import com.mrc.acbbs.entity.TitleSecond;
import com.mrc.acbbs.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by CYF on 16/1/21.
 */
public class App extends Application {
    private static final String TAG ="APP";
    private static App sInstance;
    private static Engine mEngine;
    public static final String BASE_URL = "http://h.nimingban.com/Api/";
    private static Retrofit retrofit;
    private static Observable forumListObservable;



    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mEngine = retrofit.create(Engine.class);

        forumListObservable = mEngine.loadIndexObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        forumListObservable
                .subscribe(new Subscriber<List<TitleFirst>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "板块列表加载失败！");
                    }

                    @Override
                    public void onNext(List<TitleFirst> titleFirsts) {
                        Log.i(TAG, "板块列表加载完成");
                        ArrayList<TitleSecond> body2 = new ArrayList<TitleSecond>();
                        for (TitleFirst entity : titleFirsts) {
                            for (TitleSecond entity2 : entity.getForums()) {
                                body2.add(entity2);
                            }
                        }
                        Value.setTitleSecondsArray(body2);
                    }
                });
    }
    public static Retrofit getRetrofit() {
        return retrofit;
    }
    public static App getInstance() {
        return sInstance;
    }

    public static Engine getEngine() {
        return mEngine;
    }

    public static Observable getForumListObservable() {
        return forumListObservable;
    }



}
