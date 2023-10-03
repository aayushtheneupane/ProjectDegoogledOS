package com.android.messaging.p041ui.appsettings;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0913Z;
import java.util.ArrayList;

/* renamed from: com.android.messaging.ui.appsettings.s */
class C1103s extends ArrayAdapter {
    final /* synthetic */ C1104t this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1103s(C1104t tVar, Context context) {
        super(context, R.layout.settings_item_view, new ArrayList());
        this.this$0 = tVar;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.settings_item_view, viewGroup, false);
        }
        C0913Z z = (C0913Z) getItem(i);
        TextView textView = (TextView) view.findViewById(R.id.subtitle);
        String Dh = z.mo6390Dh();
        ((TextView) view.findViewById(R.id.title)).setText(z.getDisplayName());
        if (!TextUtils.isEmpty(Dh)) {
            textView.setText(Dh);
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        view.setOnClickListener(new C1102r(this, z));
        return view;
    }
}
