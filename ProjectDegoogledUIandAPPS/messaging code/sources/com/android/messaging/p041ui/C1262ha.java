package com.android.messaging.p041ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.android.messaging.R;
import java.util.List;

/* renamed from: com.android.messaging.ui.ha */
class C1262ha extends ArrayAdapter {
    final /* synthetic */ C1264ia this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1262ha(C1264ia iaVar, Context context, List list) {
        super(context, R.layout.sms_free_storage_action_item_view, list);
        this.this$0 = iaVar;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView;
        if (view == null || !(view instanceof TextView)) {
            textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.sms_free_storage_action_item_view, viewGroup, false);
        } else {
            textView = (TextView) view;
        }
        textView.setText((String) getItem(i));
        textView.setOnClickListener(new C1260ga(this, i));
        return textView;
    }
}
