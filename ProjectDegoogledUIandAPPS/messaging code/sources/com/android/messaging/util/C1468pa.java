package com.android.messaging.util;

import android.content.Intent;
import android.database.Cursor;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.messaging.C0967f;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.pa */
public class C1468pa extends C1474sa implements C1466oa {

    /* renamed from: VK */
    private final SubscriptionManager f2335VK = SubscriptionManager.from(C0967f.get().getApplicationContext());

    public C1468pa(int i) {
        super(i);
    }

    /* renamed from: Jb */
    private int m3768Jb(int i) {
        if (i >= 0) {
            return i;
        }
        if (this.f2335VK.getActiveSubscriptionInfoCount() > 1) {
            return -1;
        }
        return getDefaultSmsSubscriptionId();
    }

    /* renamed from: Na */
    public int mo8201Na(int i) {
        return i == -1 ? getDefaultSmsSubscriptionId() : i;
    }

    /* renamed from: a */
    public void mo8204a(SubscriptionManager.OnSubscriptionsChangedListener onSubscriptionsChangedListener) {
        this.f2335VK.addOnSubscriptionsChangedListener(onSubscriptionsChangedListener);
    }

    /* renamed from: bk */
    public int mo8205bk() {
        return this.f2335VK.getActiveSubscriptionInfoCount();
    }

    /* renamed from: ek */
    public boolean mo8206ek() {
        return getDefaultSmsSubscriptionId() != -1;
    }

    /* renamed from: fk */
    public int[] mo8207fk() {
        int i;
        int i2;
        SubscriptionInfo nk = mo8219nk();
        if (nk != null) {
            i2 = nk.getMcc();
            i = nk.getMnc();
        } else {
            i = 0;
            i2 = 0;
        }
        return new int[]{i2, i};
    }

    public List getActiveSubscriptionInfoList() {
        List<SubscriptionInfo> activeSubscriptionInfoList = this.f2335VK.getActiveSubscriptionInfoList();
        if (activeSubscriptionInfoList != null) {
            return activeSubscriptionInfoList;
        }
        return C1474sa.f2339SK;
    }

    public String getCarrierName() {
        SubscriptionInfo nk = mo8219nk();
        if (nk == null) {
            return null;
        }
        CharSequence displayName = nk.getDisplayName();
        if (!TextUtils.isEmpty(displayName)) {
            return displayName.toString();
        }
        CharSequence carrierName = nk.getCarrierName();
        if (carrierName != null) {
            return carrierName.toString();
        }
        return null;
    }

    public int getDefaultSmsSubscriptionId() {
        int defaultSmsSubscriptionId = SmsManager.getDefaultSmsSubscriptionId();
        if (defaultSmsSubscriptionId < 0) {
            return -1;
        }
        return defaultSmsSubscriptionId;
    }

    public String getSimOperatorNumeric() {
        int i;
        int i2;
        SubscriptionInfo nk = mo8219nk();
        if (nk != null) {
            i2 = nk.getMcc();
            i = nk.getMnc();
        } else {
            i = 0;
            i2 = 0;
        }
        return C1474sa.m3795b(new int[]{i2, i});
    }

    /* renamed from: gk */
    public HashSet mo8212gk() {
        HashSet hashSet = new HashSet();
        for (SubscriptionInfo subscriptionId : getActiveSubscriptionInfoList()) {
            hashSet.add(C1474sa.get(subscriptionId.getSubscriptionId()).mo8229la(true));
        }
        return hashSet;
    }

    /* renamed from: hk */
    public String mo8213hk() {
        SubscriptionInfo nk = mo8219nk();
        if (nk == null) {
            return null;
        }
        String countryIso = nk.getCountryIso();
        if (TextUtils.isEmpty(countryIso)) {
            return null;
        }
        return countryIso.toUpperCase();
    }

    /* renamed from: ik */
    public SmsManager mo8214ik() {
        return SmsManager.getSmsManagerForSubscriptionId(this.mSubId);
    }

    public boolean isDataRoamingEnabled() {
        SubscriptionInfo nk = mo8219nk();
        if (nk == null) {
            StringBuilder Pa = C0632a.m1011Pa("PhoneUtils.isDataRoamingEnabled: system return empty sub info for ");
            Pa.append(this.mSubId);
            C1430e.m3622e("MessagingApp", Pa.toString());
            return false;
        } else if (nk.getDataRoaming() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isMobileDataEnabled() {
        try {
            Method declaredMethod = this.mTelephonyManager.getClass().getDeclaredMethod("getDataEnabled", new Class[]{Integer.TYPE});
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(this.mTelephonyManager, new Object[]{Integer.valueOf(this.mSubId)})).booleanValue();
        } catch (Exception e) {
            C1430e.m3623e("MessagingApp", "PhoneUtil.isMobileDataEnabled: system api not found", e);
            return false;
        }
    }

    public boolean isRoaming() {
        return this.f2335VK.isNetworkRoaming(this.mSubId);
    }

    /* renamed from: ma */
    public String mo8218ma(boolean z) {
        if (z) {
            String h = C1474sa.m3798h(this.mContext, this.mSubId);
            if (!TextUtils.isEmpty(h)) {
                return h;
            }
        }
        SubscriptionInfo nk = mo8219nk();
        if (nk != null) {
            String number = nk.getNumber();
            if (TextUtils.isEmpty(number) && Log.isLoggable("MessagingApp", 3)) {
                C1430e.m3620d("MessagingApp", "SubscriptionInfo phone number for self is empty!");
            }
            return number;
        }
        StringBuilder Pa = C0632a.m1011Pa("PhoneUtils.getSelfRawNumber: subInfo is null for ");
        Pa.append(this.mSubId);
        C1430e.m3630w("MessagingApp", Pa.toString());
        throw new IllegalStateException("No active subscription");
    }

    /* renamed from: nk */
    public SubscriptionInfo mo8219nk() {
        try {
            SubscriptionInfo activeSubscriptionInfo = this.f2335VK.getActiveSubscriptionInfo(this.mSubId);
            if (activeSubscriptionInfo == null && Log.isLoggable("MessagingApp", 3)) {
                C1430e.m3620d("MessagingApp", "PhoneUtils.getActiveSubscriptionInfo(): empty sub info for " + this.mSubId);
            }
            return activeSubscriptionInfo;
        } catch (Exception e) {
            StringBuilder Pa = C0632a.m1011Pa("PhoneUtils.getActiveSubscriptionInfo: system exception for ");
            Pa.append(this.mSubId);
            C1430e.m3623e("MessagingApp", Pa.toString(), e);
            return null;
        }
    }

    /* renamed from: a */
    public int mo8202a(Intent intent, String str) {
        return m3768Jb(intent.getIntExtra(str, -1));
    }

    /* renamed from: a */
    public int mo8203a(Cursor cursor, int i) {
        return m3768Jb(cursor.getInt(i));
    }
}
