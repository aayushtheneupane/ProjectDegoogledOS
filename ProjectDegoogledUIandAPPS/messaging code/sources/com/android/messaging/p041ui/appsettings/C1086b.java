package com.android.messaging.p041ui.appsettings;

import android.content.ContentValues;
import android.os.AsyncTask;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.appsettings.b */
class C1086b extends AsyncTask {

    /* renamed from: Ad */
    final /* synthetic */ String f1721Ad;
    final /* synthetic */ C1088d this$0;

    /* renamed from: yd */
    final /* synthetic */ String f1722yd;

    /* renamed from: zd */
    final /* synthetic */ String f1723zd;

    C1086b(C1088d dVar, String str, String str2, String str3) {
        this.this$0 = dVar;
        this.f1722yd = str;
        this.f1723zd = str2;
        this.f1721Ad = str3;
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", this.f1722yd.length() < 1 ? this.this$0.getResources().getString(R.string.untitled_apn) : this.f1722yd);
        C1088d dVar = this.this$0;
        contentValues.put("mmsproxy", dVar.m2690Ya(dVar.mMmsProxy.getText()));
        C1088d dVar2 = this.this$0;
        contentValues.put("mmsport", dVar2.m2690Ya(dVar2.f1726aa.getText()));
        C1088d dVar3 = this.this$0;
        contentValues.put("mmsc", dVar3.m2690Ya(dVar3.mMmsc.getText()));
        contentValues.put("type", DefaultApnSettingsLoader.APN_TYPE_MMS);
        contentValues.put("mcc", this.f1723zd);
        contentValues.put("mnc", this.f1721Ad);
        contentValues.put("numeric", this.f1723zd + this.f1721Ad);
        if (this.this$0.f1729da != null && this.this$0.f1730ea != null && this.this$0.f1729da.equals(this.f1721Ad) && this.this$0.f1730ea.equals(this.f1723zd)) {
            contentValues.put("current", 1);
        }
        if (this.this$0.f1731fa) {
            this.this$0.f1733ha.insert("apn", (String) null, contentValues);
        } else {
            this.this$0.f1733ha.update("apn", contentValues, "_id =?", new String[]{this.this$0.f1732ga});
        }
        return null;
    }
}
