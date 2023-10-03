package com.android.messaging.sms;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.appcompat.mms.UserAgentInfoLoader;
import com.android.messaging.util.C1396Aa;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1449g;
import com.android.messaging.util.C1464na;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.sms.k */
public class C1015k implements UserAgentInfoLoader {
    private Context mContext;
    private boolean mLoaded;
    private String mUAProfUrl;
    private String mUserAgent;

    public C1015k(Context context) {
        this.mContext = context;
    }

    private void load() {
        if (!this.mLoaded) {
            boolean z = false;
            synchronized (this) {
                if (!this.mLoaded) {
                    loadLocked();
                    this.mLoaded = true;
                    z = true;
                }
            }
            if (z) {
                StringBuilder Pa = C0632a.m1011Pa("Loaded user agent info: UA=");
                Pa.append(this.mUserAgent);
                Pa.append(", UAProfUrl=");
                Pa.append(this.mUAProfUrl);
                C1430e.m3625i("MessagingApp", Pa.toString());
            }
        }
    }

    private void loadLocked() {
        if (C1464na.m3757Xj()) {
            TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
            this.mUserAgent = telephonyManager.getMmsUserAgent();
            this.mUAProfUrl = telephonyManager.getMmsUAProfUrl();
        }
        if (TextUtils.isEmpty(this.mUserAgent)) {
            this.mUserAgent = C0632a.m1025k("Bugle/", C1396Aa.getInstance(this.mContext).getSimpleName());
        }
        if (TextUtils.isEmpty(this.mUAProfUrl)) {
            C1449g.get().getString("bugle_mms_uaprofurl", "http://www.gstatic.com/android/sms/mms_ua_profile.xml");
            this.mUAProfUrl = "http://www.gstatic.com/android/sms/mms_ua_profile.xml";
        }
    }

    public String getUAProfUrl() {
        load();
        return this.mUAProfUrl;
    }

    public String getUserAgent() {
        load();
        return this.mUserAgent;
    }
}
