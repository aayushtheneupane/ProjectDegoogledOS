package com.android.messaging.p041ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.android.messaging.util.C1424b;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.messaging.ui.pa */
public class C1371pa {

    /* renamed from: pG */
    private static final List f2184pG = new ArrayList();

    /* renamed from: jG */
    private final C1038Da f2185jG;
    /* access modifiers changed from: private */

    /* renamed from: kG */
    public String f2186kG;
    /* access modifiers changed from: private */

    /* renamed from: lG */
    public List f2187lG = f2184pG;
    /* access modifiers changed from: private */
    public C1369oa mAction;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public int mDuration = 5000;
    /* access modifiers changed from: private */

    /* renamed from: nG */
    public C1376qa f2188nG;
    /* access modifiers changed from: private */

    /* renamed from: oG */
    public View f2189oG;

    public C1371pa(C1038Da da, View view) {
        C1424b.m3594t(da);
        C1424b.m3594t(view);
        this.f2185jG = da;
        this.mContext = view.getContext();
        this.f2189oG = view;
    }

    /* renamed from: l */
    public C1371pa mo7989l(List list) {
        this.f2187lG = list;
        return this;
    }

    public C1371pa setText(String str) {
        C1424b.m3592ia(!TextUtils.isEmpty(str));
        this.f2186kG = str;
        return this;
    }

    public void show() {
        this.f2185jG.mo6946e(new C1380sa(this, (C1367na) null));
    }

    /* renamed from: a */
    public C1371pa mo7987a(C1369oa oaVar) {
        this.mAction = oaVar;
        return this;
    }

    /* renamed from: a */
    public C1371pa mo7988a(C1376qa qaVar) {
        C1424b.isNull(this.f2188nG);
        this.f2188nG = qaVar;
        return this;
    }
}
