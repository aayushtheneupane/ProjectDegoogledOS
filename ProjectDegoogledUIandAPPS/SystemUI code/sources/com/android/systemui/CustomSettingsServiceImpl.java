package com.android.systemui;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.ArrayMap;
import android.util.ArraySet;
import com.android.systemui.CustomSettingsService;
import com.android.systemui.settings.CurrentUserTracker;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CustomSettingsServiceImpl extends CustomSettingsService {
    private ContentResolver mContentResolver;
    private final Context mContext;
    /* access modifiers changed from: private */
    public int mCurrentUser;
    private final HashSet<String> mIntSettings = new HashSet<>();
    private final ArrayMap<Uri, String> mListeningUris = new ArrayMap<>();
    private final Observer mObserver = new Observer();
    private final HashMap<String, Set<CustomSettingsService.CustomSettingsObserver>> mObserverLookup = new HashMap<>();
    private final HashSet<String> mStringSettings = new HashSet<>();
    private CurrentUserTracker mUserTracker;

    public CustomSettingsServiceImpl(Context context, Handler handler) {
        this.mContext = context;
        this.mContentResolver = this.mContext.getContentResolver();
        this.mCurrentUser = ActivityManager.getCurrentUser();
        this.mUserTracker = new CurrentUserTracker(this.mContext) {
            public void onUserSwitched(int i) {
                int unused = CustomSettingsServiceImpl.this.mCurrentUser = i;
                CustomSettingsServiceImpl.this.reloadAll();
                CustomSettingsServiceImpl.this.reregisterAll();
            }
        };
        this.mUserTracker.startTracking();
    }

    public void addIntObserver(CustomSettingsService.CustomSettingsObserver customSettingsObserver, String... strArr) {
        for (String addIntObserver : strArr) {
            addIntObserver(customSettingsObserver, addIntObserver);
        }
    }

    private void addObserver(CustomSettingsService.CustomSettingsObserver customSettingsObserver, String str) {
        if (!this.mObserverLookup.containsKey(str)) {
            this.mObserverLookup.put(str, new ArraySet());
        }
        if (!this.mObserverLookup.get(str).contains(customSettingsObserver)) {
            this.mObserverLookup.get(str).add(customSettingsObserver);
        }
        Uri uriFor = Settings.System.getUriFor(str);
        if (!this.mListeningUris.containsKey(uriFor)) {
            this.mListeningUris.put(uriFor, str);
            this.mContentResolver.registerContentObserver(uriFor, false, this.mObserver, this.mCurrentUser);
        }
    }

    private void addIntObserver(CustomSettingsService.CustomSettingsObserver customSettingsObserver, String str) {
        this.mIntSettings.add(str);
        addObserver(customSettingsObserver, str);
        try {
            customSettingsObserver.onIntSettingChanged(str, Integer.valueOf(Settings.System.getIntForUser(this.mContentResolver, str, this.mCurrentUser)));
        } catch (Settings.SettingNotFoundException unused) {
        }
    }

    public void removeObserver(CustomSettingsService.CustomSettingsObserver customSettingsObserver) {
        for (Set<CustomSettingsService.CustomSettingsObserver> remove : this.mObserverLookup.values()) {
            remove.remove(customSettingsObserver);
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
        Set<CustomSettingsService.CustomSettingsObserver> set = this.mObserverLookup.get(str);
        if (set != null) {
            if (this.mStringSettings.contains(str)) {
                String stringForUser = Settings.System.getStringForUser(this.mContentResolver, str, this.mCurrentUser);
                for (CustomSettingsService.CustomSettingsObserver onStringSettingChanged : set) {
                    onStringSettingChanged.onStringSettingChanged(str, stringForUser);
                }
            }
            if (this.mIntSettings.contains(str)) {
                try {
                    Integer valueOf = Integer.valueOf(Settings.System.getIntForUser(this.mContentResolver, str, this.mCurrentUser));
                    for (CustomSettingsService.CustomSettingsObserver onIntSettingChanged : set) {
                        onIntSettingChanged.onIntSettingChanged(str, valueOf);
                    }
                } catch (Settings.SettingNotFoundException unused) {
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void reloadAll() {
        for (String next : this.mObserverLookup.keySet()) {
            if (this.mStringSettings.contains(next)) {
                String stringForUser = Settings.System.getStringForUser(this.mContentResolver, next, this.mCurrentUser);
                for (CustomSettingsService.CustomSettingsObserver onStringSettingChanged : this.mObserverLookup.get(next)) {
                    onStringSettingChanged.onStringSettingChanged(next, stringForUser);
                }
            }
            if (this.mIntSettings.contains(next)) {
                try {
                    Integer valueOf = Integer.valueOf(Settings.System.getIntForUser(this.mContentResolver, next, this.mCurrentUser));
                    for (CustomSettingsService.CustomSettingsObserver onIntSettingChanged : this.mObserverLookup.get(next)) {
                        onIntSettingChanged.onIntSettingChanged(next, valueOf);
                    }
                } catch (Settings.SettingNotFoundException unused) {
                }
            }
        }
    }

    private class Observer extends ContentObserver {
        public Observer() {
            super(new Handler(Looper.getMainLooper()));
        }

        public void onChange(boolean z, Uri uri, int i) {
            if (i == ActivityManager.getCurrentUser()) {
                CustomSettingsServiceImpl.this.reloadSetting(uri);
            }
        }
    }
}
