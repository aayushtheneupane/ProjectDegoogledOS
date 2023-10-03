package com.android.messaging.util;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.text.TextUtils;
import java.lang.reflect.Method;
import java.util.HashSet;

/* renamed from: com.android.messaging.util.qa */
public class C1470qa extends C1474sa {
    private final ConnectivityManager mConnectivityManager = ((ConnectivityManager) this.mContext.getSystemService("connectivity"));

    public C1470qa() {
        super(-1);
    }

    /* renamed from: Na */
    public int mo8201Na(int i) {
        C1424b.equals(-1, i);
        return -1;
    }

    /* renamed from: a */
    public int mo8202a(Intent intent, String str) {
        return -1;
    }

    /* renamed from: a */
    public int mo8203a(Cursor cursor, int i) {
        return -1;
    }

    /* renamed from: bk */
    public int mo8205bk() {
        return this.mTelephonyManager.getSimState() != 1 ? 1 : 0;
    }

    /* renamed from: ek */
    public boolean mo8206ek() {
        return true;
    }

    /* renamed from: fk */
    public int[] mo8207fk() {
        int i;
        int i2;
        String simOperator = this.mTelephonyManager.getSimOperator();
        try {
            i2 = Integer.parseInt(simOperator.substring(0, 3));
            try {
                i = Integer.parseInt(simOperator.substring(3));
            } catch (Exception e) {
                e = e;
                C1430e.m3631w("MessagingApp", "PhoneUtils.getMccMnc: invalid string " + simOperator, e);
                i = 0;
                return new int[]{i2, i};
            }
        } catch (Exception e2) {
            e = e2;
            i2 = 0;
            C1430e.m3631w("MessagingApp", "PhoneUtils.getMccMnc: invalid string " + simOperator, e);
            i = 0;
            return new int[]{i2, i};
        }
        return new int[]{i2, i};
    }

    public String getCarrierName() {
        return this.mTelephonyManager.getNetworkOperatorName();
    }

    public int getDefaultSmsSubscriptionId() {
        C1424b.fail("PhoneUtils.getDefaultSmsSubscriptionId(): not supported before L MR1");
        return -1;
    }

    public String getSimOperatorNumeric() {
        return this.mTelephonyManager.getSimOperator();
    }

    /* renamed from: gk */
    public HashSet mo8212gk() {
        HashSet hashSet = new HashSet();
        hashSet.add(mo8229la(true));
        return hashSet;
    }

    /* renamed from: hk */
    public String mo8213hk() {
        String simCountryIso = this.mTelephonyManager.getSimCountryIso();
        if (TextUtils.isEmpty(simCountryIso)) {
            return null;
        }
        return simCountryIso.toUpperCase();
    }

    /* renamed from: ik */
    public SmsManager mo8214ik() {
        return SmsManager.getDefault();
    }

    public boolean isDataRoamingEnabled() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        if (C1464na.m3755Vj()) {
            if (Settings.Global.getInt(contentResolver, "data_roaming", 0) != 0) {
                return true;
            }
        } else if (Settings.System.getInt(contentResolver, "data_roaming", 0) != 0) {
            return true;
        }
        return false;
    }

    public boolean isMobileDataEnabled() {
        try {
            Method declaredMethod = this.mConnectivityManager.getClass().getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(this.mConnectivityManager, new Object[0])).booleanValue();
        } catch (Exception e) {
            C1430e.m3623e("MessagingApp", "PhoneUtil.isMobileDataEnabled: system api not found", e);
            return false;
        }
    }

    public boolean isRoaming() {
        return this.mTelephonyManager.isNetworkRoaming();
    }

    /* renamed from: ma */
    public String mo8218ma(boolean z) {
        if (z) {
            String h = C1474sa.m3798h(this.mContext, -1);
            if (!TextUtils.isEmpty(h)) {
                return h;
            }
        }
        return this.mTelephonyManager.getLine1Number();
    }
}
