// Generated code from Butter Knife. Do not modify!
package com.mrc.acbbs.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FeedCardAdapter$ViewHolder$$ViewBinder<T extends com.mrc.acbbs.adapter.FeedCardAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492970, "field 'feedId'");
    target.feedId = finder.castView(view, 2131492970, "field 'feedId'");
    view = finder.findRequiredView(source, 2131492971, "field 'userId'");
    target.userId = finder.castView(view, 2131492971, "field 'userId'");
    view = finder.findRequiredView(source, 2131492972, "field 'feedDate'");
    target.feedDate = finder.castView(view, 2131492972, "field 'feedDate'");
    view = finder.findRequiredView(source, 2131492973, "field 'feedContent'");
    target.feedContent = finder.castView(view, 2131492973, "field 'feedContent'");
  }

  @Override public void unbind(T target) {
    target.feedId = null;
    target.userId = null;
    target.feedDate = null;
    target.feedContent = null;
  }
}
