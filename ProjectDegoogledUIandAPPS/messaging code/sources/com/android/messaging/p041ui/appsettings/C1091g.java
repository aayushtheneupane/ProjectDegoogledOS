package com.android.messaging.p041ui.appsettings;

import android.os.AsyncTask;

/* renamed from: com.android.messaging.ui.appsettings.g */
class C1091g extends AsyncTask {

    /* renamed from: Bd */
    final /* synthetic */ String f1734Bd;
    final /* synthetic */ C1094j this$0;

    C1091g(C1094j jVar, String str) {
        this.this$0 = jVar;
        this.f1734Bd = str;
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        this.this$0.f1740ha.update("apn", C1094j.f1737qa, "current =?", C1094j.f1739ta);
        this.this$0.f1740ha.update("apn", C1094j.f1738ra, "_id =?", new String[]{this.f1734Bd});
        return null;
    }
}
