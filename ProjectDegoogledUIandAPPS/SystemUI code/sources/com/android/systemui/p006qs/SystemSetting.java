package com.android.systemui.p006qs;

import android.app.ActivityManager;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import com.android.systemui.statusbar.policy.Listenable;

/* renamed from: com.android.systemui.qs.SystemSetting */
public abstract class SystemSetting extends ContentObserver implements Listenable {
    private final Context mContext;
    private int mObservedValue = 0;
    private final String mSettingName;
    private int mUserId;

    /* access modifiers changed from: protected */
    public abstract void handleValueChanged(int i, boolean z);

    public SystemSetting(Context context, Handler handler, String str) {
        super(handler);
        this.mContext = context;
        this.mSettingName = str;
        this.mUserId = ActivityManager.getCurrentUser();
    }

    public int getValue() {
        return Settings.System.getIntForUser(this.mContext.getContentResolver(), this.mSettingName, 0, this.mUserId);
    }

    public void setValue(int i) {
        Settings.System.putIntForUser(this.mContext.getContentResolver(), this.mSettingName, i, this.mUserId);
    }

    public void onChange(boolean z) {
        int value = getValue();
        handleValueChanged(value, value != this.mObservedValue);
        this.mObservedValue = value;
    }
}
