package com.android.messaging.p041ui.debug;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.android.messaging.R;
import com.android.messaging.sms.C1024t;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.android.messaging.ui.debug.b */
class C1249b extends BaseAdapter implements C1251d {
    private final LayoutInflater mInflater;
    private final List mKeys = new ArrayList(this.f1976yl.keySet());

    /* renamed from: yl */
    private final C1024t f1976yl;

    public C1249b(DebugMmsConfigFragment debugMmsConfigFragment, Context context, int i) {
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f1976yl = C1024t.get(i);
        Iterator it = this.mKeys.iterator();
        while (it.hasNext()) {
            if (C1024t.m2369ra((String) it.next()) == null) {
                it.remove();
            }
        }
        Collections.sort(this.mKeys);
    }

    /* renamed from: a */
    public void mo7628a(String str, String str2, String str3) {
        this.f1976yl.mo6834c(str2, str, str3);
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.mKeys.size();
    }

    public Object getItem(int i) {
        return this.mKeys.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        DebugMmsConfigItemView debugMmsConfigItemView;
        if (view == null || !(view instanceof DebugMmsConfigItemView)) {
            debugMmsConfigItemView = (DebugMmsConfigItemView) this.mInflater.inflate(R.layout.debug_mmsconfig_item_view, viewGroup, false);
        } else {
            debugMmsConfigItemView = (DebugMmsConfigItemView) view;
        }
        String str = (String) this.mKeys.get(i);
        debugMmsConfigItemView.mo7621a(str, C1024t.m2369ra(str), String.valueOf(this.f1976yl.getValue(str)), this);
        return debugMmsConfigItemView;
    }
}
