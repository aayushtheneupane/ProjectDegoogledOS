package com.android.messaging.p041ui.appsettings;

import android.os.AsyncTask;

/* renamed from: com.android.messaging.ui.appsettings.c */
class C1087c extends AsyncTask {
    final /* synthetic */ C1088d this$0;

    C1087c(C1088d dVar) {
        this.this$0 = dVar;
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        this.this$0.f1733ha.delete("apn", "_id =?", new String[]{this.this$0.f1732ga});
        return null;
    }
}
