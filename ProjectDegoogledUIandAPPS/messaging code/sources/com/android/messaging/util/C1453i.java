package com.android.messaging.util;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.i */
public class C1453i extends C1451h {
    private final int mSubId;

    public C1453i(Context context, int i) {
        super(context);
        this.mSubId = i;
    }

    /* renamed from: a */
    private void m3730a(C1451h hVar, String str, int i, int i2) {
        Resources resources = C0967f.get().getApplicationContext().getResources();
        boolean z = resources.getBoolean(i2);
        boolean z2 = hVar.getBoolean(str, z);
        if (z2 != z) {
            putBoolean(resources.getString(i), z2);
        }
    }

    /* renamed from: F */
    public void mo8164F(int i, int i2) {
        if (i == -1) {
            m3730a(C1451h.m3724Hd(), "delivery_reports", R.string.delivery_reports_pref_key, R.bool.delivery_reports_pref_default);
            m3730a(C1451h.m3724Hd(), "auto_retrieve_mms", R.string.auto_retrieve_mms_pref_key, R.bool.auto_retrieve_mms_pref_default);
            m3730a(C1451h.m3724Hd(), "auto_retrieve_mms_when_roaming", R.string.auto_retrieve_mms_when_roaming_pref_key, R.bool.auto_retrieve_mms_when_roaming_pref_default);
            m3730a(C1451h.m3724Hd(), "group_messaging", R.string.group_mms_pref_key, R.bool.group_mms_pref_default);
            if (C1474sa.getDefault().mo8205bk() == 1) {
                C1451h Hd = C1451h.m3724Hd();
                Resources resources = C0967f.get().getApplicationContext().getResources();
                String string = Hd.getString("mms_phone_number", (String) null);
                if (!TextUtils.equals(string, (CharSequence) null)) {
                    putString(resources.getString(R.string.mms_phone_number_pref_key), string);
                }
            }
        }
    }

    public String getSharedPreferencesName() {
        StringBuilder Pa = C0632a.m1011Pa("buglesub_");
        Pa.append(String.valueOf(this.mSubId));
        return Pa.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: ya */
    public void mo8166ya(String str) {
        C1424b.m3592ia(str.startsWith("buglesub_"));
    }
}
