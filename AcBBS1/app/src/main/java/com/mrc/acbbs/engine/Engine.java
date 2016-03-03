package com.mrc.acbbs.engine;

import com.mrc.acbbs.entity.Forum;
import com.mrc.acbbs.entity.ForumObject;
import com.mrc.acbbs.entity.TitleFirst;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by CYF on 16/1/21.
 */
public interface Engine {

    @GET("getForumList")
    Call<List<TitleFirst>> loadIndex();

    @GET("getForumList")
    Observable<List<TitleFirst>> loadIndexObservable();

    @GET("showf/id/{id}/page/{page}")
    Call<List<ForumObject>> loadForums(@Path("id") String id,@Path("page") String page);


}
