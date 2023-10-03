package com.android.messaging.util;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import com.android.messaging.p041ui.debug.C1254g;

/* renamed from: com.android.messaging.util.M */
class C1409M extends C1478ua {
    /* access modifiers changed from: private */
    public final String mAction;
    private final Activity mHost;

    public C1409M(Activity activity, String str) {
        this.mHost = activity;
        this.mAction = str;
    }

    /* JADX WARNING: type inference failed for: r5v1, types: [java.lang.String[], java.io.Serializable] */
    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        ? r5 = (String[]) obj;
        if (r5 != 0 && r5.length >= 1) {
            FragmentManager fragmentManager = this.mHost.getFragmentManager();
            fragmentManager.beginTransaction();
            String str = this.mAction;
            C1254g gVar = new C1254g();
            Bundle bundle = new Bundle();
            bundle.putSerializable("dump_files", r5);
            bundle.putString("action", str);
            gVar.setArguments(bundle);
            gVar.show(fragmentManager, "");
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Object mo6323a(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        return C1410N.m3546Mj().list(new C1408L(this));
    }
}
