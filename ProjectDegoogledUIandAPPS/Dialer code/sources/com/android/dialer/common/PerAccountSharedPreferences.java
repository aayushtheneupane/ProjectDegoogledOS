package com.android.dialer.common;

import android.content.SharedPreferences;
import android.telecom.PhoneAccountHandle;

public class PerAccountSharedPreferences {
    private final PhoneAccountHandle phoneAccountHandle;
    /* access modifiers changed from: private */
    public final SharedPreferences preferences;
    private final String sharedPrefsKeyPrefix;

    public class Editor {
        private final SharedPreferences.Editor editor;

        /* synthetic */ Editor(C04461 r2) {
            this.editor = PerAccountSharedPreferences.this.preferences.edit();
        }

        public void apply() {
            this.editor.apply();
        }

        public Editor putBoolean(String str, boolean z) {
            this.editor.putBoolean(PerAccountSharedPreferences.this.getKey(str), z);
            return this;
        }

        public Editor putString(String str, String str2) {
            this.editor.putString(PerAccountSharedPreferences.this.getKey(str), str2);
            return this;
        }
    }

    public PerAccountSharedPreferences(PhoneAccountHandle phoneAccountHandle2, SharedPreferences sharedPreferences) {
        this.preferences = sharedPreferences;
        this.phoneAccountHandle = phoneAccountHandle2;
        this.sharedPrefsKeyPrefix = "phone_account_dependent_";
    }

    /* access modifiers changed from: private */
    public String getKey(String str) {
        return this.sharedPrefsKeyPrefix + str + "_" + this.phoneAccountHandle.getId();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        r2 = r2.preferences.getAll().get(getKey(r3));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <T> T getValue(java.lang.String r3, T r4) {
        /*
            r2 = this;
            android.content.SharedPreferences r0 = r2.preferences
            java.lang.String r1 = r2.getKey(r3)
            boolean r0 = r0.contains(r1)
            if (r0 != 0) goto L_0x000d
            return r4
        L_0x000d:
            android.content.SharedPreferences r0 = r2.preferences
            java.util.Map r0 = r0.getAll()
            java.lang.String r2 = r2.getKey(r3)
            java.lang.Object r2 = r0.get(r2)
            if (r2 != 0) goto L_0x001e
            return r4
        L_0x001e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.common.PerAccountSharedPreferences.getValue(java.lang.String, java.lang.Object):java.lang.Object");
    }

    public boolean contains(String str) {
        return this.preferences.contains(getKey(str));
    }

    public Editor edit() {
        return new Editor((C04461) null);
    }

    public boolean getBoolean(String str, boolean z) {
        return ((Boolean) getValue(str, Boolean.valueOf(z))).booleanValue();
    }

    public String getString(String str, String str2) {
        return (String) getValue(str, str2);
    }

    public String getString(String str) {
        return (String) getValue(str, (Object) null);
    }

    protected PerAccountSharedPreferences(PhoneAccountHandle phoneAccountHandle2, SharedPreferences sharedPreferences, String str) {
        Assert.checkArgument(str.equals("visual_voicemail_"));
        this.preferences = sharedPreferences;
        this.phoneAccountHandle = phoneAccountHandle2;
        this.sharedPrefsKeyPrefix = str;
    }
}
