package com.android.systemui.havoc.header;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatusBarHeaderMachine {
    private boolean mAttached;
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("com.android.systemui.havoc.header.STATUS_BAR_HEADER_UPDATE".equals(intent.getAction())) {
                if (StatusBarHeaderMachine.this.mScreenOn) {
                    StatusBarHeaderMachine.this.doUpdateStatusHeaderObservers(false);
                }
            } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                boolean unused = StatusBarHeaderMachine.this.mScreenOn = false;
            } else if ("android.intent.action.SCREEN_ON".equals(intent.getAction()) && !StatusBarHeaderMachine.this.mScreenOn) {
                boolean unused2 = StatusBarHeaderMachine.this.mScreenOn = true;
                StatusBarHeaderMachine.this.doUpdateStatusHeaderObservers(true);
            }
        }
    };
    /* access modifiers changed from: private */
    public Context mContext;
    private String mCurrentProviderName;
    private String mDefaultProviderName;
    private Handler mHandler = new Handler();
    private List<IStatusBarHeaderMachineObserver> mObservers = new ArrayList();
    private Map<String, IStatusBarHeaderProvider> mProviders = new HashMap();
    /* access modifiers changed from: private */
    public boolean mScreenOn = true;
    private SettingsObserver mSettingsObserver = new SettingsObserver(this.mHandler);

    public interface IStatusBarHeaderMachineObserver {
        void disableHeader();

        void refreshHeader();

        void updateHeader(Drawable drawable, boolean z);
    }

    public interface IStatusBarHeaderProvider {
        void disableProvider();

        void enableProvider();

        Drawable getCurrent(Calendar calendar);

        String getName();

        void settingsChanged(Uri uri);
    }

    private class SettingsObserver extends ContentObserver {
        SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            StatusBarHeaderMachine.this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("status_bar_custom_header"), false, this, -1);
            StatusBarHeaderMachine.this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("status_bar_daylight_header_pack"), false, this, -1);
            StatusBarHeaderMachine.this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("status_bar_custom_header_image"), false, this, -1);
            StatusBarHeaderMachine.this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("status_bar_custom_header_provider"), false, this, -1);
            StatusBarHeaderMachine.this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("status_bar_custom_header_shadow"), false, this, -1);
            StatusBarHeaderMachine.this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("status_bar_file_header_image"), false, this, -1);
        }

        public void onChange(boolean z, Uri uri) {
            boolean z2 = false;
            if (Settings.System.getIntForUser(StatusBarHeaderMachine.this.mContext.getContentResolver(), "status_bar_custom_header", 0, -2) == 1) {
                z2 = true;
            }
            if (!z2 || !uri.equals(Settings.System.getUriFor("status_bar_custom_header_shadow"))) {
                IStatusBarHeaderProvider access$400 = StatusBarHeaderMachine.this.getCurrentProvider();
                if (access$400 != null) {
                    try {
                        access$400.settingsChanged(uri);
                    } catch (Exception unused) {
                    }
                }
                StatusBarHeaderMachine.this.updateEnablement();
                return;
            }
            StatusBarHeaderMachine.this.doRefreshStatusHeaderObservers();
        }
    }

    public StatusBarHeaderMachine(Context context) {
        this.mContext = context;
        DaylightHeaderProvider daylightHeaderProvider = new DaylightHeaderProvider(context);
        addProvider(daylightHeaderProvider);
        this.mDefaultProviderName = daylightHeaderProvider.getName();
        this.mCurrentProviderName = this.mDefaultProviderName;
        addProvider(new StaticHeaderProvider(context));
        addProvider(new FileHeaderProvider(context));
        this.mSettingsObserver.observe();
    }

    public Drawable getCurrent() {
        Calendar instance = Calendar.getInstance();
        IStatusBarHeaderProvider currentProvider = getCurrentProvider();
        if (currentProvider == null) {
            return null;
        }
        try {
            return currentProvider.getCurrent(instance);
        } catch (Exception unused) {
            return null;
        }
    }

    public void addProvider(IStatusBarHeaderProvider iStatusBarHeaderProvider) {
        if (!this.mProviders.containsKey(iStatusBarHeaderProvider.getName())) {
            this.mProviders.put(iStatusBarHeaderProvider.getName(), iStatusBarHeaderProvider);
        }
    }

    public void addObserver(IStatusBarHeaderMachineObserver iStatusBarHeaderMachineObserver) {
        if (!this.mObservers.contains(iStatusBarHeaderMachineObserver)) {
            this.mObservers.add(iStatusBarHeaderMachineObserver);
        }
    }

    public void removeObserver(IStatusBarHeaderMachineObserver iStatusBarHeaderMachineObserver) {
        this.mObservers.remove(iStatusBarHeaderMachineObserver);
    }

    /* access modifiers changed from: private */
    public void doUpdateStatusHeaderObservers(boolean z) {
        for (IStatusBarHeaderMachineObserver updateHeader : this.mObservers) {
            try {
                updateHeader.updateHeader(getCurrent(), z);
            } catch (Exception unused) {
            }
        }
    }

    private void doDisableStatusHeaderObservers() {
        for (IStatusBarHeaderMachineObserver disableHeader : this.mObservers) {
            try {
                disableHeader.disableHeader();
            } catch (Exception unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void doRefreshStatusHeaderObservers() {
        for (IStatusBarHeaderMachineObserver refreshHeader : this.mObservers) {
            try {
                refreshHeader.refreshHeader();
            } catch (Exception unused) {
            }
        }
    }

    public void updateEnablement() {
        boolean z = Settings.System.getIntForUser(this.mContext.getContentResolver(), "status_bar_custom_header", 0, -2) == 1;
        String stringForUser = Settings.System.getStringForUser(this.mContext.getContentResolver(), "status_bar_custom_header_provider", -2);
        if (stringForUser != null && !stringForUser.equals(this.mCurrentProviderName)) {
            for (IStatusBarHeaderProvider next : this.mProviders.values()) {
                if (!next.getName().equals(stringForUser)) {
                    next.disableProvider();
                }
            }
            this.mCurrentProviderName = stringForUser;
        }
        IStatusBarHeaderProvider currentProvider = getCurrentProvider();
        if (currentProvider == null) {
            Log.w("StatusBarHeaderMachine", "updateEnablement: no active provider");
        } else if (z) {
            currentProvider.enableProvider();
            if (!this.mAttached) {
                doUpdateStatusHeaderObservers(true);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                intentFilter.addAction("com.android.systemui.havoc.header.STATUS_BAR_HEADER_UPDATE");
                this.mContext.registerReceiver(this.mBroadcastReceiver, intentFilter);
                this.mAttached = true;
                return;
            }
            doUpdateStatusHeaderObservers(true);
        } else {
            currentProvider.disableProvider();
            if (this.mAttached) {
                this.mContext.unregisterReceiver(this.mBroadcastReceiver);
                doDisableStatusHeaderObservers();
                this.mAttached = false;
            }
        }
    }

    /* access modifiers changed from: private */
    public IStatusBarHeaderProvider getCurrentProvider() {
        if (this.mProviders.size() == 1) {
            return this.mProviders.get(this.mDefaultProviderName);
        }
        String str = this.mCurrentProviderName;
        if (str == null || !this.mProviders.containsKey(str)) {
            return this.mProviders.get(this.mDefaultProviderName);
        }
        return this.mProviders.get(this.mCurrentProviderName);
    }
}
