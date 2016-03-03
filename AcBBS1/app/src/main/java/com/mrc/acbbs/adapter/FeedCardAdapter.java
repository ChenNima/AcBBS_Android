package com.mrc.acbbs.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mrc.acbbs.R;
import com.mrc.acbbs.entity.ForumObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CYF on 16/2/1.
 */
public class FeedCardAdapter extends ArrayAdapter<ForumObject> {

    Activity activity;
    ViewHolder holder;

    public FeedCardAdapter(Activity activity, ArrayList<ForumObject> items) {
        super(activity, 0, items);
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null && activity != null) {
            convertView = activity.getLayoutInflater().inflate(
                    R.layout.forum_card, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ForumObject item = getItem(position);
        if(null!=item){
            holder.feedContent.setText(item.getContent().replace("<br>", "\n").replace("<br />", "").replace("&nbsp;", " ").replace("&quot;", "'"));
            holder.feedDate.setText(item.getNow());
            holder.feedId.setText(item.getId());
            holder.userId.setText(item.getUserid());
        }
        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'forum_card.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.feedId)
        TextView feedId;
        @Bind(R.id.userId)
        TextView userId;
        @Bind(R.id.feedDate)
        TextView feedDate;
        @Bind(R.id.feedContent)
        TextView feedContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}