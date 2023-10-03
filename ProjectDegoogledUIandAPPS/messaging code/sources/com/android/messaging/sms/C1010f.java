package com.android.messaging.sms;

import android.text.TextUtils;
import androidx.appcompat.mms.ApnSettingsLoader;
import java.net.URI;
import java.net.URISyntaxException;

/* renamed from: com.android.messaging.sms.f */
class C1010f implements ApnSettingsLoader.Apn {
    private final String mMmsProxy;
    private final int mMmsProxyPort;
    private final String mMmsc;

    public C1010f(String str, String str2, int i) {
        this.mMmsc = str;
        this.mMmsProxy = str2;
        this.mMmsProxyPort = i;
    }

    public static C1010f from(String str, String str2, String str3, String str4) {
        if (!C1012h.isValidApnType(C1012h.access$000(str), DefaultApnSettingsLoader.APN_TYPE_MMS)) {
            return null;
        }
        String access$000 = C1012h.access$000(str2);
        if (TextUtils.isEmpty(access$000)) {
            return null;
        }
        String access$100 = C1012h.access$100(access$000);
        try {
            new URI(access$100);
            String access$0002 = C1012h.access$000(str3);
            int i = 80;
            if (!TextUtils.isEmpty(access$0002)) {
                access$0002 = C1012h.access$100(access$0002);
                String access$0003 = C1012h.access$000(str4);
                if (access$0003 != null) {
                    try {
                        i = Integer.parseInt(access$0003);
                    } catch (NumberFormatException unused) {
                    }
                }
            }
            return new C1010f(access$100, access$0002, i);
        } catch (URISyntaxException unused2) {
            return null;
        }
    }

    /* renamed from: a */
    public boolean mo6818a(C1010f fVar) {
        return TextUtils.equals(this.mMmsc, fVar.getMmsc()) && TextUtils.equals(this.mMmsProxy, fVar.getMmsProxy()) && this.mMmsProxyPort == fVar.getMmsProxyPort();
    }

    public String getMmsProxy() {
        return this.mMmsProxy;
    }

    public int getMmsProxyPort() {
        return this.mMmsProxyPort;
    }

    public String getMmsc() {
        return this.mMmsc;
    }

    public void setSuccess() {
    }
}
