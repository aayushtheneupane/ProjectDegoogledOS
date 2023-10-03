package androidx.appcompat.mms;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import p026b.p027a.p030b.p031a.C0632a;

class DefaultUserAgentInfoLoader implements UserAgentInfoLoader {
    private static final String DEFAULT_UA_PROF_URL = "http://www.gstatic.com/android/sms/mms_ua_profile.xml";
    private static final String DEFAULT_USER_AGENT = "Android MmsLib/1.0";
    private Context mContext;
    private boolean mLoaded;
    private String mUAProfUrl;
    private String mUserAgent;

    DefaultUserAgentInfoLoader(Context context) {
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
                Log.i("MmsLib", Pa.toString());
            }
        }
    }

    private void loadLocked() {
        int i = Build.VERSION.SDK_INT;
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        this.mUserAgent = telephonyManager.getMmsUserAgent();
        this.mUAProfUrl = telephonyManager.getMmsUAProfUrl();
        if (TextUtils.isEmpty(this.mUserAgent)) {
            this.mUserAgent = DEFAULT_USER_AGENT;
        }
        if (TextUtils.isEmpty(this.mUAProfUrl)) {
            this.mUAProfUrl = DEFAULT_UA_PROF_URL;
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
