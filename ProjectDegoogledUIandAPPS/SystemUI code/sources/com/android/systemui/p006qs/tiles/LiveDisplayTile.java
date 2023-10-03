package com.android.systemui.p006qs.tiles;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.hardware.display.ColorDisplayManager;
import android.os.Handler;
import android.provider.Settings;
import com.android.internal.custom.hardware.LiveDisplayManager;
import com.android.internal.util.ArrayUtils;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.LiveDisplayTile */
public class LiveDisplayTile extends QSTileImpl<QSTile.LiveDisplayState> {
    private static final Intent LIVEDISPLAY_SETTINGS = new Intent("com.android.settings.LIVEDISPLAY_SETTINGS");
    private String[] mAnnouncementEntries;
    /* access modifiers changed from: private */
    public int mDayTemperature;
    private String[] mDescriptionEntries;
    private String[] mEntries;
    private final int[] mEntryIconRes;
    private boolean mListening;
    /* access modifiers changed from: private */
    public final LiveDisplayManager mLiveDisplay;
    private final boolean mNightDisplayAvailable = ColorDisplayManager.isNightDisplayAvailable(this.mContext);
    private final LiveDisplayObserver mObserver;
    private boolean mOutdoorModeAvailable;
    private String mTitle;
    private String[] mValues;

    public int getMetricsCategory() {
        return 1999;
    }

    public LiveDisplayTile(QSHost qSHost) {
        super(qSHost);
        TypedArray obtainTypedArray = this.mContext.getResources().obtainTypedArray(17236111);
        this.mEntryIconRes = new int[obtainTypedArray.length()];
        boolean z = false;
        int i = 0;
        while (true) {
            int[] iArr = this.mEntryIconRes;
            if (i >= iArr.length) {
                break;
            }
            iArr[i] = obtainTypedArray.getResourceId(i, 0);
            i++;
        }
        obtainTypedArray.recycle();
        updateEntries();
        this.mLiveDisplay = LiveDisplayManager.getInstance(this.mContext);
        if (this.mLiveDisplay.getConfig() != null) {
            if (this.mLiveDisplay.getConfig().hasFeature(3) && !this.mLiveDisplay.getConfig().hasFeature(14)) {
                z = true;
            }
            this.mOutdoorModeAvailable = z;
            this.mDayTemperature = this.mLiveDisplay.getDayColorTemperature();
        } else {
            this.mOutdoorModeAvailable = false;
            this.mDayTemperature = -1;
        }
        this.mObserver = new LiveDisplayObserver(this.mHandler);
        this.mObserver.startObserving();
    }

    private void updateEntries() {
        Resources resources = this.mContext.getResources();
        this.mTitle = resources.getString(17040296);
        this.mEntries = resources.getStringArray(17236112);
        this.mDescriptionEntries = resources.getStringArray(17236110);
        this.mAnnouncementEntries = resources.getStringArray(17236109);
        this.mValues = resources.getStringArray(17236114);
    }

    public boolean isAvailable() {
        return !this.mNightDisplayAvailable || this.mOutdoorModeAvailable;
    }

    public QSTile.LiveDisplayState newTileState() {
        return new QSTile.LiveDisplayState();
    }

    public void handleSetListening(boolean z) {
        if (this.mListening != z) {
            this.mListening = z;
            if (z) {
                this.mObserver.startObserving();
            } else {
                this.mObserver.endObserving();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        changeToNextMode();
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.LiveDisplayState liveDisplayState, Object obj) {
        updateEntries();
        liveDisplayState.mode = obj == null ? getCurrentModeIndex() : ((Integer) obj).intValue();
        liveDisplayState.label = this.mTitle;
        String[] strArr = this.mEntries;
        int i = liveDisplayState.mode;
        liveDisplayState.secondaryLabel = strArr[i];
        liveDisplayState.icon = QSTileImpl.ResourceIcon.get(this.mEntryIconRes[i]);
        liveDisplayState.contentDescription = this.mDescriptionEntries[liveDisplayState.mode];
        liveDisplayState.state = this.mLiveDisplay.getMode() != 0 ? 2 : 1;
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(17040296);
    }

    public Intent getLongClickIntent() {
        return LIVEDISPLAY_SETTINGS;
    }

    /* access modifiers changed from: protected */
    public String composeChangeAnnouncement() {
        return this.mAnnouncementEntries[getCurrentModeIndex()];
    }

    /* access modifiers changed from: private */
    public int getCurrentModeIndex() {
        String[] strArr;
        String str;
        try {
            str = String.valueOf(this.mLiveDisplay.getMode());
        } catch (NullPointerException unused) {
            str = String.valueOf(2);
        } catch (Throwable unused2) {
            strArr = this.mValues;
            str = null;
        }
        strArr = this.mValues;
        return ArrayUtils.indexOf(strArr, str);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0038 A[EDGE_INSN: B:20:0x0038->B:15:0x0038 ?: BREAK  , SYNTHETIC] */
    private void changeToNextMode() {
        /*
            r7 = this;
            int r0 = r7.getCurrentModeIndex()
            r1 = 1
            int r0 = r0 + r1
            java.lang.String[] r2 = r7.mValues
            int r2 = r2.length
            r3 = 0
            if (r0 < r2) goto L_0x000d
            goto L_0x0038
        L_0x000d:
            java.lang.String[] r2 = r7.mValues
            r2 = r2[r0]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            int r2 = r2.intValue()
            boolean r4 = r7.mOutdoorModeAvailable
            if (r4 != 0) goto L_0x0020
            r4 = 3
            if (r2 == r4) goto L_0x0031
        L_0x0020:
            int r4 = r7.mDayTemperature
            r5 = 6500(0x1964, float:9.108E-42)
            r6 = 4
            if (r4 != r5) goto L_0x0029
            if (r2 == r6) goto L_0x0031
        L_0x0029:
            boolean r4 = r7.mNightDisplayAvailable
            if (r4 == 0) goto L_0x003a
            if (r2 == r6) goto L_0x0031
            if (r2 != r1) goto L_0x003a
        L_0x0031:
            int r0 = r0 + 1
            java.lang.String[] r2 = r7.mValues
            int r2 = r2.length
            if (r0 < r2) goto L_0x000d
        L_0x0038:
            r0 = r3
            goto L_0x000d
        L_0x003a:
            android.content.Context r7 = r7.mContext
            android.content.ContentResolver r7 = r7.getContentResolver()
            r0 = -2
            java.lang.String r1 = "display_temperature_mode"
            android.provider.Settings.System.putIntForUser(r7, r1, r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.p006qs.tiles.LiveDisplayTile.changeToNextMode():void");
    }

    /* renamed from: com.android.systemui.qs.tiles.LiveDisplayTile$LiveDisplayObserver */
    private class LiveDisplayObserver extends ContentObserver {
        public LiveDisplayObserver(Handler handler) {
            super(handler);
        }

        public void onChange(boolean z) {
            LiveDisplayTile liveDisplayTile = LiveDisplayTile.this;
            int unused = liveDisplayTile.mDayTemperature = liveDisplayTile.mLiveDisplay.getDayColorTemperature();
            LiveDisplayTile liveDisplayTile2 = LiveDisplayTile.this;
            liveDisplayTile2.refreshState(Integer.valueOf(liveDisplayTile2.getCurrentModeIndex()));
        }

        public void startObserving() {
            LiveDisplayTile.this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("display_temperature_mode"), false, this, -1);
            LiveDisplayTile.this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("display_temperature_day"), false, this, -1);
        }

        public void endObserving() {
            LiveDisplayTile.this.mContext.getContentResolver().unregisterContentObserver(this);
        }
    }
}
