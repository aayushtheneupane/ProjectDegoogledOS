package com.android.messaging.p041ui.conversation;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0917ba;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.conversation.SimSelectorItemView */
public class SimSelectorItemView extends LinearLayout {

    /* renamed from: Qh */
    private SimIconView f1844Qh;
    /* access modifiers changed from: private */
    public C0917ba mData;
    /* access modifiers changed from: private */
    public C1206ya mHost;

    /* renamed from: mh */
    private TextView f1845mh;

    /* renamed from: rh */
    private TextView f1846rh;

    public SimSelectorItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: c */
    public void mo7443c(C0917ba baVar) {
        C1424b.m3594t(baVar);
        this.mData = baVar;
        C1424b.m3594t(this.mData);
        String str = this.mData.displayName;
        if (TextUtils.isEmpty(str)) {
            this.f1845mh.setVisibility(8);
        } else {
            this.f1845mh.setVisibility(0);
            this.f1845mh.setText(str);
        }
        String str2 = this.mData.f1218pC;
        if (TextUtils.isEmpty(str2)) {
            this.f1846rh.setVisibility(8);
        } else {
            this.f1846rh.setVisibility(0);
            this.f1846rh.setText(str2);
        }
        this.f1844Qh.mo6930f(this.mData.f1215mC);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.f1845mh = (TextView) findViewById(R.id.name);
        this.f1846rh = (TextView) findViewById(R.id.details);
        this.f1844Qh = (SimIconView) findViewById(R.id.sim_icon);
        setOnClickListener(new C1204xa(this));
    }

    /* renamed from: a */
    public void mo7442a(C1206ya yaVar) {
        this.mHost = yaVar;
    }
}
