package com.android.systemui.tuner;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import com.android.systemui.settings.CurrentUserTracker;
import com.android.systemui.statusbar.phone.StatusBarIconController;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.util.leak.LeakDetector;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TunerServiceImpl extends TunerService {
    private ContentResolver mContentResolver;
    private final Context mContext;
    /* access modifiers changed from: private */
    public int mCurrentUser;
    private final LeakDetector mLeakDetector;
    private final ArrayMap<Uri, String> mListeningUris = new ArrayMap<>();
    private final Observer mObserver = new Observer();
    private final HashMap<String, Set<TunerService.Tunable>> mTunableLookup = new HashMap<>();
    private final HashSet<TunerService.Tunable> mTunables;
    private CurrentUserTracker mUserTracker;

    public TunerServiceImpl(Context context, Handler handler, LeakDetector leakDetector) {
        this.mTunables = LeakDetector.ENABLED ? new HashSet<>() : null;
        this.mContext = context;
        this.mContentResolver = this.mContext.getContentResolver();
        this.mLeakDetector = leakDetector;
        for (UserInfo userHandle : UserManager.get(this.mContext).getUsers()) {
            this.mCurrentUser = userHandle.getUserHandle().getIdentifier();
            if (getValue("sysui_tuner_version", 0) != 5) {
                upgradeTuner(getValue("sysui_tuner_version", 0), 5, handler);
            }
        }
        this.mCurrentUser = ActivityManager.getCurrentUser();
        this.mUserTracker = new CurrentUserTracker(this.mContext) {
            public void onUserSwitched(int i) {
                int unused = TunerServiceImpl.this.mCurrentUser = i;
                TunerServiceImpl.this.reloadAll();
                TunerServiceImpl.this.reregisterAll();
            }
        };
        this.mUserTracker.startTracking();
        TunerService.setTunerEnabled(this.mContext, true);
    }

    private void upgradeTuner(int i, int i2, Handler handler) {
        String value;
        if (i < 1 && (value = getValue("icon_blacklist")) != null) {
            ArraySet<String> iconBlacklist = StatusBarIconController.getIconBlacklist(value);
            iconBlacklist.add("rotate");
            iconBlacklist.add("headset");
            Settings.Secure.putStringForUser(this.mContentResolver, "icon_blacklist", TextUtils.join(",", iconBlacklist), this.mCurrentUser);
        }
        if (i < 4) {
            handler.postDelayed(new Runnable(this.mCurrentUser) {
                private final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    TunerServiceImpl.this.lambda$upgradeTuner$0$TunerServiceImpl(this.f$1);
                }
            }, 5000);
        }
        setValue("sysui_tuner_version", i2);
    }

    private boolean isSystem(String str) {
        return str.startsWith("system:");
    }

    private String chomp(String str) {
        return str.replaceFirst("^(system):", "");
    }

    public String getValue(String str) {
        if (isSystem(str)) {
            return Settings.System.getStringForUser(this.mContentResolver, chomp(str), this.mCurrentUser);
        }
        return Settings.Secure.getStringForUser(this.mContentResolver, str, this.mCurrentUser);
    }

    public void setValue(String str, String str2) {
        if (isSystem(str)) {
            Settings.System.putStringForUser(this.mContentResolver, chomp(str), str2, this.mCurrentUser);
        } else {
            Settings.Secure.putStringForUser(this.mContentResolver, str, str2, this.mCurrentUser);
        }
    }

    public int getValue(String str, int i) {
        if (isSystem(str)) {
            return Settings.System.getIntForUser(this.mContentResolver, chomp(str), i, this.mCurrentUser);
        }
        return Settings.Secure.getIntForUser(this.mContentResolver, str, i, this.mCurrentUser);
    }

    public void setValue(String str, int i) {
        if (isSystem(str)) {
            Settings.System.putIntForUser(this.mContentResolver, chomp(str), i, this.mCurrentUser);
        } else {
            Settings.Secure.putIntForUser(this.mContentResolver, str, i, this.mCurrentUser);
        }
    }

    public void addTunable(TunerService.Tunable tunable, String... strArr) {
        for (String addTunable : strArr) {
            addTunable(tunable, addTunable);
        }
    }

    private void addTunable(TunerService.Tunable tunable, String str) {
        Uri uri;
        if (!this.mTunableLookup.containsKey(str)) {
            this.mTunableLookup.put(str, new ArraySet());
        }
        this.mTunableLookup.get(str).add(tunable);
        if (LeakDetector.ENABLED) {
            this.mTunables.add(tunable);
            this.mLeakDetector.trackCollection(this.mTunables, "TunerService.mTunables");
        }
        if (isSystem(str)) {
            uri = Settings.System.getUriFor(chomp(str));
        } else {
            uri = Settings.Secure.getUriFor(str);
        }
        if (!this.mListeningUris.containsKey(uri)) {
            this.mListeningUris.put(uri, str);
            this.mContentResolver.registerContentObserver(uri, false, this.mObserver, this.mCurrentUser);
        }
        tunable.onTuningChanged(str, getValue(str));
    }

    public void removeTunable(TunerService.Tunable tunable) {
        for (Set<TunerService.Tunable> remove : this.mTunableLookup.values()) {
            remove.remove(tunable);
        }
        if (LeakDetector.ENABLED) {
            this.mTunables.remove(tunable);
        }
    }

    /* access modifiers changed from: protected */
    public void reregisterAll() {
        if (this.mListeningUris.size() != 0) {
            this.mContentResolver.unregisterContentObserver(this.mObserver);
            for (Uri registerContentObserver : this.mListeningUris.keySet()) {
                this.mContentResolver.registerContentObserver(registerContentObserver, false, this.mObserver, this.mCurrentUser);
            }
        }
    }

    /* access modifiers changed from: private */
    public void reloadSetting(Uri uri) {
        String str = this.mListeningUris.get(uri);
        Set<TunerService.Tunable> set = this.mTunableLookup.get(str);
        if (set != null) {
            String value = getValue(str);
            for (TunerService.Tunable tunable : set) {
                if (tunable != null) {
                    tunable.onTuningChanged(str, value);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void reloadAll() {
        for (String next : this.mTunableLookup.keySet()) {
            String value = getValue(next);
            for (TunerService.Tunable tunable : this.mTunableLookup.get(next)) {
                if (tunable != null) {
                    tunable.onTuningChanged(next, value);
                }
            }
        }
    }

    public void clearAll() {
        lambda$upgradeTuner$0$TunerServiceImpl(this.mCurrentUser);
    }

    /* renamed from: clearAllFromUser */
    public void lambda$upgradeTuner$0$TunerServiceImpl(int i) {
        Settings.Global.putString(this.mContentResolver, "sysui_demo_allowed", (String) null);
        Intent intent = new Intent("com.android.systemui.demo");
        intent.putExtra("command", "exit");
        this.mContext.sendBroadcast(intent);
        for (String putStringForUser : this.mTunableLookup.keySet()) {
            Settings.Secure.putStringForUser(this.mContentResolver, putStringForUser, (String) null, i);
        }
    }

    private class Observer extends ContentObserver {
        public Observer() {
            super(new Handler(Looper.getMainLooper()));
        }

        public void onChange(boolean z, Uri uri, int i) {
            if (i == ActivityManager.getCurrentUser()) {
                TunerServiceImpl.this.reloadSetting(uri);
            }
        }
    }
}
