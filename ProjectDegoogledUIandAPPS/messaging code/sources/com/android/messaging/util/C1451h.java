package com.android.messaging.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.messaging.C0967f;

/* renamed from: com.android.messaging.util.h */
public abstract class C1451h {
    private final Context mContext;

    public C1451h(Context context) {
        this.mContext = context;
    }

    /* renamed from: Hd */
    public static C1451h m3724Hd() {
        return C0967f.get().mo6645Hd();
    }

    /* renamed from: ha */
    public static C1451h m3725ha(int i) {
        return C0967f.get().mo6661ha(i);
    }

    /* renamed from: F */
    public abstract void mo8164F(int i, int i2);

    public boolean getBoolean(String str, boolean z) {
        mo8166ya(str);
        return this.mContext.getSharedPreferences(getSharedPreferencesName(), 0).getBoolean(str, z);
    }

    public int getInt(String str, int i) {
        mo8166ya(str);
        return this.mContext.getSharedPreferences(getSharedPreferencesName(), 0).getInt(str, i);
    }

    public long getLong(String str, long j) {
        mo8166ya(str);
        return this.mContext.getSharedPreferences(getSharedPreferencesName(), 0).getLong(str, j);
    }

    public abstract String getSharedPreferencesName();

    public String getString(String str, String str2) {
        mo8166ya(str);
        return this.mContext.getSharedPreferences(getSharedPreferencesName(), 0).getString(str, str2);
    }

    public void putBoolean(String str, boolean z) {
        mo8166ya(str);
        SharedPreferences.Editor edit = this.mContext.getSharedPreferences(getSharedPreferencesName(), 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public void putInt(String str, int i) {
        mo8166ya(str);
        SharedPreferences.Editor edit = this.mContext.getSharedPreferences(getSharedPreferencesName(), 0).edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public void putLong(String str, long j) {
        mo8166ya(str);
        SharedPreferences.Editor edit = this.mContext.getSharedPreferences(getSharedPreferencesName(), 0).edit();
        edit.putLong(str, j);
        edit.apply();
    }

    public void putString(String str, String str2) {
        mo8166ya(str);
        SharedPreferences.Editor edit = this.mContext.getSharedPreferences(getSharedPreferencesName(), 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public void remove(String str) {
        mo8166ya(str);
        SharedPreferences.Editor edit = this.mContext.getSharedPreferences(getSharedPreferencesName(), 0).edit();
        edit.remove(str);
        edit.apply();
    }

    /* access modifiers changed from: protected */
    /* renamed from: ya */
    public void mo8166ya(String str) {
    }
}
