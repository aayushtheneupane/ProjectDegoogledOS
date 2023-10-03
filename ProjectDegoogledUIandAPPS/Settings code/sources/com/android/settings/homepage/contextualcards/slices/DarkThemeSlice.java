package com.android.settings.homepage.contextualcards.slices;

import android.app.PendingIntent;
import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settings.slices.CustomSliceable;
import com.android.settings.slices.SliceBackgroundWorker;
import com.android.settingslib.Utils;
import com.havoc.config.center.C1715R;
import java.io.IOException;

public class DarkThemeSlice implements CustomSliceable {
    static long sActiveUiSession = -1000;
    static boolean sKeepSliceShow;
    private final Context mContext;
    private final PowerManager mPowerManager;
    private final UiModeManager mUiModeManager;

    public Intent getIntent() {
        return null;
    }

    public DarkThemeSlice(Context context) {
        this.mContext = context;
        this.mUiModeManager = (UiModeManager) context.getSystemService(UiModeManager.class);
        this.mPowerManager = (PowerManager) context.getSystemService(PowerManager.class);
    }

    public Slice getSlice() {
        long uiSessionToken = FeatureFactory.getFactory(this.mContext).getSlicesFeatureProvider().getUiSessionToken();
        if (uiSessionToken != sActiveUiSession) {
            sActiveUiSession = uiSessionToken;
            sKeepSliceShow = false;
        }
        if (this.mPowerManager.isPowerSaveMode() || (!sKeepSliceShow && !isAvailable(this.mContext))) {
            ListBuilder listBuilder = new ListBuilder(this.mContext, CustomSliceRegistry.DARK_THEME_SLICE_URI, -1);
            listBuilder.setIsError(true);
            return listBuilder.build();
        }
        sKeepSliceShow = true;
        PendingIntent broadcastIntent = getBroadcastIntent(this.mContext);
        int colorAccentDefaultColor = Utils.getColorAccentDefaultColor(this.mContext);
        IconCompat createWithResource = IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.dark_theme);
        ListBuilder listBuilder2 = new ListBuilder(this.mContext, CustomSliceRegistry.DARK_THEME_SLICE_URI, -1);
        listBuilder2.setAccentColor(colorAccentDefaultColor);
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitle(this.mContext.getText(C1715R.string.dark_theme_slice_title));
        rowBuilder.setTitleItem(createWithResource, 0);
        rowBuilder.setSubtitle(this.mContext.getText(C1715R.string.dark_theme_slice_subtitle));
        rowBuilder.setPrimaryAction(SliceAction.createToggle(broadcastIntent, (CharSequence) null, isDarkThemeMode(this.mContext)));
        listBuilder2.addRow(rowBuilder);
        return listBuilder2.build();
    }

    public Uri getUri() {
        return CustomSliceRegistry.DARK_THEME_SLICE_URI;
    }

    public void onNotifyChange(Intent intent) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable(intent.getBooleanExtra("android.app.slice.extra.TOGGLE_STATE", false)) {
            private final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                DarkThemeSlice.this.lambda$onNotifyChange$0$DarkThemeSlice(this.f$1);
            }
        }, 200);
    }

    public /* synthetic */ void lambda$onNotifyChange$0$DarkThemeSlice(boolean z) {
        this.mUiModeManager.setNightModeActivated(z);
    }

    public Class getBackgroundWorkerClass() {
        return DarkThemeWorker.class;
    }

    /* access modifiers changed from: package-private */
    public boolean isAvailable(Context context) {
        if (isDarkThemeMode(context)) {
            return false;
        }
        int intProperty = ((BatteryManager) context.getSystemService(BatteryManager.class)).getIntProperty(4);
        Log.d("DarkThemeSlice", "battery level=" + intProperty);
        if (intProperty <= 50) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isDarkThemeMode(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static class DarkThemeWorker extends SliceBackgroundWorker<Void> {
        private final ContentObserver mContentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
            public void onChange(boolean z) {
                if (((PowerManager) DarkThemeWorker.this.mContext.getSystemService(PowerManager.class)).isPowerSaveMode()) {
                    DarkThemeWorker.this.notifySliceChange();
                }
            }
        };
        /* access modifiers changed from: private */
        public final Context mContext;

        public void close() throws IOException {
        }

        public DarkThemeWorker(Context context, Uri uri) {
            super(context, uri);
            this.mContext = context;
        }

        /* access modifiers changed from: protected */
        public void onSlicePinned() {
            this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("low_power"), false, this.mContentObserver);
        }

        /* access modifiers changed from: protected */
        public void onSliceUnpinned() {
            this.mContext.getContentResolver().unregisterContentObserver(this.mContentObserver);
        }
    }
}
