package com.android.systemui.p006qs.tiles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.SystemProperties;
import android.telephony.PhoneStateListener;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.SysUIToast;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;
import java.util.List;

/* renamed from: com.android.systemui.qs.tiles.DataSwitchTile */
public class DataSwitchTile extends QSTileImpl<QSTile.BooleanState> {
    /* access modifiers changed from: private */
    public boolean mCanSwitch = true;
    private MyCallStateListener mPhoneStateListener;
    private boolean mRegistered = false;
    private int mSimCount = 0;
    BroadcastReceiver mSimReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Log.d(DataSwitchTile.this.TAG, "mSimReceiver:onReceive");
            DataSwitchTile.this.refreshState();
        }
    };
    private SubscriptionManager mSubscriptionManager;
    /* access modifiers changed from: private */
    public TelephonyManager mTelephonyManager;

    public int getMetricsCategory() {
        return 1999;
    }

    /* renamed from: com.android.systemui.qs.tiles.DataSwitchTile$MyCallStateListener */
    class MyCallStateListener extends PhoneStateListener {
        MyCallStateListener() {
        }

        public void onCallStateChanged(int i, String str) {
            DataSwitchTile dataSwitchTile = DataSwitchTile.this;
            boolean unused = dataSwitchTile.mCanSwitch = dataSwitchTile.mTelephonyManager.getCallState() == 0;
            DataSwitchTile.this.refreshState();
        }
    }

    public DataSwitchTile(QSHost qSHost) {
        super(qSHost);
        this.mSubscriptionManager = SubscriptionManager.from(qSHost.getContext());
        this.mTelephonyManager = TelephonyManager.from(qSHost.getContext());
        this.mPhoneStateListener = new MyCallStateListener();
    }

    public boolean isAvailable() {
        int phoneCount = TelephonyManager.getDefault().getPhoneCount();
        String str = this.TAG;
        Log.d(str, "phoneCount: " + phoneCount);
        return phoneCount >= 2;
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    public void handleSetListening(boolean z) {
        if (z) {
            if (!this.mRegistered) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
                this.mContext.registerReceiver(this.mSimReceiver, intentFilter);
                this.mTelephonyManager.listen(this.mPhoneStateListener, 32);
                this.mRegistered = true;
            }
            refreshState();
        } else if (this.mRegistered) {
            this.mContext.unregisterReceiver(this.mSimReceiver);
            this.mTelephonyManager.listen(this.mPhoneStateListener, 0);
            this.mRegistered = false;
        }
    }

    private void updateSimCount() {
        String str = SystemProperties.get("gsm.sim.state");
        Log.d(this.TAG, "DataSwitchTile:updateSimCount:simState=" + str);
        this.mSimCount = 0;
        try {
            String[] split = TextUtils.split(str, ",");
            for (int i = 0; i < split.length; i++) {
                if (!split[i].isEmpty() && !split[i].equalsIgnoreCase("ABSENT") && !split[i].equalsIgnoreCase("NOT_READY")) {
                    this.mSimCount++;
                }
            }
        } catch (Exception unused) {
            Log.e(this.TAG, "Error to parse sim state");
        }
        Log.d(this.TAG, "DataSwitchTile:updateSimCount:mSimCount=" + this.mSimCount);
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        if (!this.mCanSwitch) {
            String str = this.TAG;
            Log.d(str, "Call state=" + this.mTelephonyManager.getCallState());
            return;
        }
        int i = this.mSimCount;
        if (i == 0) {
            Log.d(this.TAG, "handleClick:no sim card");
            Context context = this.mContext;
            SysUIToast.makeText(context, (CharSequence) context.getString(C1784R$string.qs_data_switch_toast_0), 1).show();
        } else if (i == 1) {
            Log.d(this.TAG, "handleClick:only one sim card");
            Context context2 = this.mContext;
            SysUIToast.makeText(context2, (CharSequence) context2.getString(C1784R$string.qs_data_switch_toast_1), 1).show();
        } else {
            this.mHost.collapsePanels();
            AsyncTask.execute(new Runnable() {
                public final void run() {
                    DataSwitchTile.this.toggleMobileDataEnabled();
                    DataSwitchTile.this.refreshState();
                }
            });
        }
    }

    public Intent getLongClickIntent() {
        return new Intent("android.settings.panel.action.MOBILE_DATA");
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.qs_data_switch_label);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        boolean z;
        int i;
        int i2;
        int i3;
        int i4 = 1;
        if (obj == null) {
            int defaultDataPhoneId = this.mSubscriptionManager.getDefaultDataPhoneId();
            Log.d(this.TAG, "default data phone id=" + defaultDataPhoneId);
            z = defaultDataPhoneId == 0;
        } else {
            z = ((Boolean) obj).booleanValue();
        }
        updateSimCount();
        int i5 = this.mSimCount;
        if (i5 == 1) {
            if (z) {
                i2 = C1776R$drawable.ic_qs_data_switch_1;
            } else {
                i2 = C1776R$drawable.ic_qs_data_switch_2;
            }
            booleanState.icon = QSTileImpl.ResourceIcon.get(i2);
            booleanState.value = false;
        } else if (i5 != 2) {
            booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_data_switch_1);
            booleanState.value = false;
        } else {
            if (z) {
                i3 = C1776R$drawable.ic_qs_data_switch_1;
            } else {
                i3 = C1776R$drawable.ic_qs_data_switch_2;
            }
            booleanState.icon = QSTileImpl.ResourceIcon.get(i3);
            booleanState.value = true;
        }
        if (this.mSimCount < 2) {
            booleanState.state = 0;
        } else if (!this.mCanSwitch) {
            booleanState.state = 0;
            Log.d(this.TAG, "call state isn't idle, set to unavailable.");
        } else {
            if (booleanState.value) {
                i4 = 2;
            }
            booleanState.state = i4;
        }
        Context context = this.mContext;
        if (z) {
            i = C1784R$string.qs_data_switch_changed_1;
        } else {
            i = C1784R$string.qs_data_switch_changed_2;
        }
        booleanState.contentDescription = context.getString(i);
        booleanState.label = this.mContext.getString(C1784R$string.qs_data_switch_label);
    }

    /* access modifiers changed from: protected */
    public String composeChangeAnnouncement() {
        int i;
        Context context = this.mContext;
        if (((QSTile.BooleanState) this.mState).value) {
            i = C1784R$string.qs_data_switch_changed_1;
        } else {
            i = C1784R$string.qs_data_switch_changed_2;
        }
        return context.getString(i);
    }

    /* access modifiers changed from: private */
    public void toggleMobileDataEnabled() {
        int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId() ^ 3;
        this.mTelephonyManager.createForSubscriptionId(defaultDataSubscriptionId).setDataEnabled(true);
        this.mSubscriptionManager.setDefaultDataSubId(defaultDataSubscriptionId);
        String str = this.TAG;
        Log.d(str, "Enabled subID: " + defaultDataSubscriptionId);
        List<SubscriptionInfo> activeSubscriptionInfoList = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList != null) {
            for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
                if (subscriptionInfo.getSubscriptionId() != defaultDataSubscriptionId && !subscriptionInfo.isOpportunistic()) {
                    this.mTelephonyManager.createForSubscriptionId(subscriptionInfo.getSubscriptionId()).setDataEnabled(false);
                    String str2 = this.TAG;
                    Log.d(str2, "Disabled subID: " + subscriptionInfo.getSubscriptionId());
                }
            }
        }
    }
}
