package com.android.settings.custom.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.ArrayMap;
import android.util.ArraySet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SettingsHelper {
    private static final String SETTINGS_GLOBAL = Settings.Global.CONTENT_URI.toString();
    private static final String SETTINGS_SECURE = Settings.Secure.CONTENT_URI.toString();
    private static final String SETTINGS_SYSTEM = Settings.System.CONTENT_URI.toString();
    private static SettingsHelper sInstance;
    private final Context mContext;
    private final Observatory mObservatory;

    public interface OnSettingsChangeListener {
        void onSettingsChanged(Uri uri);
    }

    private SettingsHelper(Context context) {
        this.mContext = context;
        this.mObservatory = new Observatory(context, new Handler());
    }

    public static synchronized SettingsHelper get(Context context) {
        SettingsHelper settingsHelper;
        synchronized (SettingsHelper.class) {
            if (sInstance == null) {
                sInstance = new SettingsHelper(context);
            }
            settingsHelper = sInstance;
        }
        return settingsHelper;
    }

    public void startWatching(OnSettingsChangeListener onSettingsChangeListener, Uri... uriArr) {
        this.mObservatory.register(onSettingsChangeListener, uriArr);
    }

    public void stopWatching(OnSettingsChangeListener onSettingsChangeListener) {
        this.mObservatory.unregister(onSettingsChangeListener);
    }

    private static class Observatory extends ContentObserver {
        private final Context mContext;
        private final List<Uri> mRefs = new ArrayList();
        private final ContentResolver mResolver;
        private final Map<OnSettingsChangeListener, Set<Uri>> mTriggers = new ArrayMap();

        public Observatory(Context context, Handler handler) {
            super(handler);
            this.mContext = context;
            this.mResolver = this.mContext.getContentResolver();
        }

        public void register(OnSettingsChangeListener onSettingsChangeListener, Uri... uriArr) {
            synchronized (this.mRefs) {
                Set set = this.mTriggers.get(onSettingsChangeListener);
                if (set == null) {
                    set = new ArraySet();
                    this.mTriggers.put(onSettingsChangeListener, set);
                }
                for (Uri uri : uriArr) {
                    set.add(uri);
                    if (!this.mRefs.contains(uri)) {
                        this.mResolver.registerContentObserver(uri, false, this);
                        onSettingsChangeListener.onSettingsChanged((Uri) null);
                    }
                    this.mRefs.add(uri);
                }
            }
        }

        public void unregister(OnSettingsChangeListener onSettingsChangeListener) {
            synchronized (this.mRefs) {
                Set<Uri> remove = this.mTriggers.remove(onSettingsChangeListener);
                if (remove != null) {
                    for (Uri remove2 : remove) {
                        this.mRefs.remove(remove2);
                    }
                }
                if (this.mRefs.size() == 0) {
                    this.mResolver.unregisterContentObserver(this);
                }
            }
        }

        public void onChange(boolean z, Uri uri) {
            synchronized (this.mRefs) {
                super.onChange(z, uri);
                ArraySet<OnSettingsChangeListener> arraySet = new ArraySet<>();
                for (Map.Entry next : this.mTriggers.entrySet()) {
                    if (((Set) next.getValue()).contains(uri)) {
                        arraySet.add((OnSettingsChangeListener) next.getKey());
                    }
                }
                for (OnSettingsChangeListener onSettingsChanged : arraySet) {
                    onSettingsChanged.onSettingsChanged(uri);
                }
            }
        }
    }
}
