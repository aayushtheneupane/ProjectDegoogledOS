package com.android.settings.display.darkmode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;

public class DarkModeObserver {
    private final BroadcastReceiver mBatterySaverReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            DarkModeObserver.this.mCallback.run();
        }
    };
    /* access modifiers changed from: private */
    public Runnable mCallback;
    private ContentObserver mContentObserver;
    private Context mContext;

    public DarkModeObserver(Context context) {
        this.mContext = context;
        this.mContentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
            public void onChange(boolean z, Uri uri) {
                String str;
                super.onChange(z, uri);
                if (uri == null) {
                    str = null;
                } else {
                    str = uri.getLastPathSegment();
                }
                if (str != null && DarkModeObserver.this.mCallback != null) {
                    char c = 65535;
                    if (str.hashCode() == 1186889717 && str.equals("ui_night_mode")) {
                        c = 0;
                    }
                    if (c == 0) {
                        DarkModeObserver.this.mCallback.run();
                    }
                }
            }
        };
    }

    public void subscribe(Runnable runnable) {
        runnable.run();
        this.mCallback = runnable;
        this.mContext.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("ui_night_mode"), false, this.mContentObserver);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
        this.mContext.registerReceiver(this.mBatterySaverReceiver, intentFilter);
    }

    public void unsubscribe() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mContentObserver);
        try {
            this.mContext.unregisterReceiver(this.mBatterySaverReceiver);
        } catch (IllegalArgumentException e) {
            Log.w("DarkModeObserver", e.getMessage());
        }
        this.mCallback = null;
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public void setContentObserver(ContentObserver contentObserver) {
        this.mContentObserver = contentObserver;
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public ContentObserver getContentObserver() {
        return this.mContentObserver;
    }
}
