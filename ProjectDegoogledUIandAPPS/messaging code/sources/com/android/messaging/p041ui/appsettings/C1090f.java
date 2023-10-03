package com.android.messaging.p041ui.appsettings;

import android.database.Cursor;
import android.os.AsyncTask;
import android.preference.PreferenceGroup;
import com.android.messaging.R;
import com.android.messaging.sms.C1012h;

/* renamed from: com.android.messaging.ui.appsettings.f */
class C1090f extends AsyncTask {
    final /* synthetic */ C1094j this$0;
    final /* synthetic */ String val$mccMnc;

    C1090f(C1094j jVar, String str) {
        this.this$0 = jVar;
        this.val$mccMnc = str;
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        return this.this$0.f1740ha.query("apn", C1094j.APN_PROJECTION, "numeric =?", new String[]{this.val$mccMnc}, (String) null, (String) null, (String) null, (String) null);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        Cursor cursor = (Cursor) obj;
        if (cursor != null) {
            try {
                PreferenceGroup preferenceGroup = (PreferenceGroup) this.this$0.findPreference(this.this$0.getString(R.string.apn_list_pref_key));
                preferenceGroup.removeAll();
                String unused = this.this$0.f1744na = C1012h.m2364a(this.this$0.f1740ha, this.val$mccMnc);
                while (cursor.moveToNext()) {
                    String string = cursor.getString(1);
                    String string2 = cursor.getString(2);
                    String string3 = cursor.getString(0);
                    if (C1012h.isValidApnType(cursor.getString(3), DefaultApnSettingsLoader.APN_TYPE_MMS)) {
                        ApnPreference apnPreference = new ApnPreference(this.this$0.getActivity());
                        apnPreference.setKey(string3);
                        apnPreference.setTitle(string);
                        apnPreference.setSummary(string2);
                        apnPreference.setPersistent(false);
                        apnPreference.setOnPreferenceChangeListener(this.this$0);
                        apnPreference.setSelectable(true);
                        if (this.this$0.f1744na != null && this.this$0.f1744na.equals(string3)) {
                            apnPreference.mo7139xb();
                        }
                        preferenceGroup.addPreference(apnPreference);
                    }
                }
            } finally {
                cursor.close();
            }
        }
    }
}
