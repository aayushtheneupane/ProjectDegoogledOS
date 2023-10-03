package com.android.messaging.p041ui.debug;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.debug.f */
class C1253f extends ArrayAdapter {
    final /* synthetic */ C1254g this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1253f(C1254g gVar, Context context, String[] strArr) {
        super(context, R.layout.sms_mms_dump_file_list_item, strArr);
        this.this$0 = gVar;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView;
        if (view == null || !(view instanceof TextView)) {
            textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.sms_mms_dump_file_list_item, viewGroup, false);
        } else {
            textView = (TextView) view;
        }
        String str = (String) getItem(i);
        textView.setText(str);
        textView.setOnClickListener(new C1252e(this, str));
        return textView;
    }
}
