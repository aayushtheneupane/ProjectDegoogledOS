package com.android.settings.network.telephony;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import com.android.settings.network.AirplaneModePreferenceController;
import com.android.settings.network.MobileDataContentObserver;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settings.slices.CustomSliceable;
import com.android.settings.slices.SliceBackgroundWorker;
import com.android.settingslib.Utils;
import com.havoc.config.center.C1715R;
import java.io.IOException;
import java.util.List;

public class MobileDataSlice implements CustomSliceable {
    private final Context mContext;
    private final SubscriptionManager mSubscriptionManager = ((SubscriptionManager) this.mContext.getSystemService(SubscriptionManager.class));
    private final TelephonyManager mTelephonyManager = ((TelephonyManager) this.mContext.getSystemService(TelephonyManager.class));

    public MobileDataSlice(Context context) {
        this.mContext = context;
    }

    public Slice getSlice() {
        IconCompat createWithResource = IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.ic_network_cell);
        String charSequence = this.mContext.getText(C1715R.string.mobile_data_settings_title).toString();
        int colorAccentDefaultColor = Utils.getColorAccentDefaultColor(this.mContext);
        if (isAirplaneModeEnabled() || !isMobileDataAvailable()) {
            return null;
        }
        String charSequence2 = this.mContext.getText(C1715R.string.mobile_data_settings_summary).toString();
        PendingIntent broadcastIntent = getBroadcastIntent(this.mContext);
        SliceAction createDeeplink = SliceAction.createDeeplink(getPrimaryAction(), createWithResource, 0, charSequence);
        SliceAction createToggle = SliceAction.createToggle(broadcastIntent, (CharSequence) null, isMobileDataEnabled());
        ListBuilder listBuilder = new ListBuilder(this.mContext, getUri(), -1);
        listBuilder.setAccentColor(colorAccentDefaultColor);
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitle(charSequence);
        rowBuilder.setSubtitle(charSequence2);
        rowBuilder.addEndItem(createToggle);
        rowBuilder.setPrimaryAction(createDeeplink);
        listBuilder.addRow(rowBuilder);
        return listBuilder.build();
    }

    public Uri getUri() {
        return CustomSliceRegistry.MOBILE_DATA_SLICE_URI;
    }

    public void onNotifyChange(Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra("android.app.slice.extra.TOGGLE_STATE", isMobileDataEnabled());
        int defaultSubscriptionId = getDefaultSubscriptionId(this.mSubscriptionManager);
        if (defaultSubscriptionId != -1) {
            MobileNetworkUtils.setMobileDataEnabled(this.mContext, defaultSubscriptionId, booleanExtra, false);
        }
    }

    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        return intentFilter;
    }

    public Intent getIntent() {
        return new Intent(this.mContext, MobileNetworkActivity.class);
    }

    public Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return MobileDataWorker.class;
    }

    protected static int getDefaultSubscriptionId(SubscriptionManager subscriptionManager) {
        SubscriptionInfo defaultDataSubscriptionInfo = subscriptionManager.getDefaultDataSubscriptionInfo();
        if (defaultDataSubscriptionInfo == null) {
            return -1;
        }
        return defaultDataSubscriptionInfo.getSubscriptionId();
    }

    private PendingIntent getPrimaryAction() {
        return PendingIntent.getActivity(this.mContext, 0, getIntent(), 0);
    }

    private boolean isMobileDataAvailable() {
        List selectableSubscriptionInfoList = this.mSubscriptionManager.getSelectableSubscriptionInfoList();
        return selectableSubscriptionInfoList != null && !selectableSubscriptionInfoList.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public boolean isAirplaneModeEnabled() {
        return new AirplaneModePreferenceController(this.mContext, "key").isChecked();
    }

    /* access modifiers changed from: package-private */
    public boolean isMobileDataEnabled() {
        TelephonyManager telephonyManager = this.mTelephonyManager;
        if (telephonyManager == null) {
            return false;
        }
        return telephonyManager.isDataEnabled();
    }

    public static class MobileDataWorker extends SliceBackgroundWorker<Void> {
        DataContentObserver mMobileDataObserver = new DataContentObserver(new Handler(Looper.getMainLooper()), this);

        public MobileDataWorker(Context context, Uri uri) {
            super(context, uri);
        }

        /* access modifiers changed from: protected */
        public void onSlicePinned() {
            this.mMobileDataObserver.register(getContext(), MobileDataSlice.getDefaultSubscriptionId((SubscriptionManager) getContext().getSystemService(SubscriptionManager.class)));
        }

        /* access modifiers changed from: protected */
        public void onSliceUnpinned() {
            this.mMobileDataObserver.unRegister(getContext());
        }

        public void close() throws IOException {
            this.mMobileDataObserver = null;
        }

        public void updateSlice() {
            notifySliceChange();
        }

        public class DataContentObserver extends ContentObserver {
            private final MobileDataWorker mSliceBackgroundWorker;

            public DataContentObserver(Handler handler, MobileDataWorker mobileDataWorker) {
                super(handler);
                this.mSliceBackgroundWorker = mobileDataWorker;
            }

            public void onChange(boolean z) {
                this.mSliceBackgroundWorker.updateSlice();
            }

            public void register(Context context, int i) {
                context.getContentResolver().registerContentObserver(MobileDataContentObserver.getObservableUri(i), false, this);
            }

            public void unRegister(Context context) {
                context.getContentResolver().unregisterContentObserver(this);
            }
        }
    }
}
