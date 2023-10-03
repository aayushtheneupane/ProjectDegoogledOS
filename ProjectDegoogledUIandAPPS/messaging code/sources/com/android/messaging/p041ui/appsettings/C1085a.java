package com.android.messaging.p041ui.appsettings;

import android.database.Cursor;
import android.os.AsyncTask;

/* renamed from: com.android.messaging.ui.appsettings.a */
class C1085a extends AsyncTask {
    final /* synthetic */ C1088d this$0;

    C1085a(C1088d dVar) {
        this.this$0 = dVar;
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        if (this.this$0.f1732ga == null) {
            return null;
        }
        String[] strArr = {this.this$0.f1732ga};
        C1088d dVar = this.this$0;
        Cursor unused = dVar.mCursor = dVar.f1733ha.query("apn", C1088d.f1725ja, "_id =?", strArr, (String) null, (String) null, (String) null, (String) null);
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        Void voidR = (Void) obj;
        if (this.this$0.mCursor == null) {
            this.this$0.getActivity().finish();
            return;
        }
        this.this$0.mCursor.moveToFirst();
        this.this$0.m2707um();
    }
}
