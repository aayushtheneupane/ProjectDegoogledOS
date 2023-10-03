package com.android.messaging.p041ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import com.android.messaging.R;
import com.android.messaging.datamodel.p038b.C0857U;
import com.android.messaging.datamodel.p038b.C0858V;
import java.util.List;

/* renamed from: com.android.messaging.ui.Fa */
public class C1042Fa extends BaseExpandableListAdapter {
    private final LayoutInflater mInflater;

    /* renamed from: zl */
    private final List f1647zl;

    public C1042Fa(Context context, List list) {
        this.f1647zl = list;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public Object getChild(int i, int i2) {
        return ((C0858V) this.f1647zl.get(i)).mo6136Yh().get(i2);
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        PersonItemView personItemView;
        if (view == null) {
            personItemView = (PersonItemView) this.mInflater.inflate(R.layout.people_list_item_view, viewGroup, false);
            personItemView.setClickable(false);
        } else {
            personItemView = (PersonItemView) view;
        }
        personItemView.mo7066d(((C0857U) ((C0858V) this.f1647zl.get(i)).mo6136Yh().get(i2)).mo6134Xh());
        return personItemView;
    }

    public int getChildrenCount(int i) {
        return ((C0858V) this.f1647zl.get(i)).mo6136Yh().size();
    }

    public Object getGroup(int i) {
        return this.f1647zl.get(i);
    }

    public int getGroupCount() {
        return this.f1647zl.size();
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        PersonItemView personItemView;
        if (view == null) {
            personItemView = (PersonItemView) this.mInflater.inflate(R.layout.people_list_item_view, viewGroup, false);
            personItemView.setClickable(false);
        } else {
            personItemView = (PersonItemView) view;
        }
        personItemView.mo7066d(((C0858V) this.f1647zl.get(i)).mo6135Xh());
        return personItemView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }
}
