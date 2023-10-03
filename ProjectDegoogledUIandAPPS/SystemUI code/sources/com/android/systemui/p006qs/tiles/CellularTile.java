package com.android.systemui.p006qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.Settings;
import android.telephony.SubscriptionManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import androidx.appcompat.R$styleable;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.net.DataUsageController;
import com.android.settingslib.net.DataUsageUtils;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.SignalTileView;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.p005qs.DetailAdapter;
import com.android.systemui.plugins.p005qs.QSIconView;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import com.android.systemui.statusbar.policy.NetworkController;

/* renamed from: com.android.systemui.qs.tiles.CellularTile */
public class CellularTile extends QSTileImpl<QSTile.SignalState> {
    private final ActivityStarter mActivityStarter;
    /* access modifiers changed from: private */
    public final NetworkController mController;
    /* access modifiers changed from: private */
    public final DataUsageController mDataController;
    /* access modifiers changed from: private */
    public final CellularDetailAdapter mDetailAdapter;
    private final KeyguardMonitor mKeyguard;
    private final KeyguardCallback mKeyguardCallback = new KeyguardCallback();
    /* access modifiers changed from: private */
    public final CellSignalCallback mSignalCallback = new CellSignalCallback();

    public int getMetricsCategory() {
        return R$styleable.AppCompatTheme_windowActionBar;
    }

    public boolean isDualTarget() {
        return true;
    }

    public CellularTile(QSHost qSHost, NetworkController networkController, ActivityStarter activityStarter) {
        super(qSHost);
        this.mController = networkController;
        this.mActivityStarter = activityStarter;
        this.mDataController = this.mController.getMobileDataController();
        this.mDetailAdapter = new CellularDetailAdapter();
        this.mKeyguard = (KeyguardMonitor) Dependency.get(KeyguardMonitor.class);
        this.mController.observe(getLifecycle(), this.mSignalCallback);
    }

    public QSTile.SignalState newTileState() {
        return new QSTile.SignalState();
    }

    public QSIconView createTileView(Context context) {
        return new SignalTileView(context);
    }

    public DetailAdapter getDetailAdapter() {
        return this.mDetailAdapter;
    }

    public void handleSetListening(boolean z) {
        if (z) {
            this.mKeyguard.addCallback(this.mKeyguardCallback);
        } else {
            this.mKeyguard.removeCallback(this.mKeyguardCallback);
        }
    }

    public Intent getLongClickIntent() {
        return getCellularSettingIntent();
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        if (((QSTile.SignalState) getState()).state != 0) {
            if (!this.mKeyguard.isSecure() || !this.mKeyguard.isShowing()) {
                DataUsageController dataUsageController = this.mDataController;
                dataUsageController.setMobileDataEnabled(!dataUsageController.isMobileDataEnabled());
                return;
            }
            ((ActivityStarter) Dependency.get(ActivityStarter.class)).postQSRunnableDismissingKeyguard(new Runnable() {
                public final void run() {
                    CellularTile.this.lambda$handleClick$0$CellularTile();
                }
            });
        }
    }

    public /* synthetic */ void lambda$handleClick$0$CellularTile() {
        this.mHost.openPanels();
        DataUsageController dataUsageController = this.mDataController;
        dataUsageController.setMobileDataEnabled(!dataUsageController.isMobileDataEnabled());
    }

    /* access modifiers changed from: protected */
    public void handleSecondaryClick() {
        if (((QSTile.SignalState) getState()).state != 0) {
            if (!this.mDataController.isMobileDataSupported()) {
                this.mActivityStarter.postStartActivityDismissingKeyguard(getCellularSettingIntent(), 0);
            } else if (!this.mKeyguard.isSecure() || !this.mKeyguard.isShowing()) {
                showDetail(true);
            } else {
                ((ActivityStarter) Dependency.get(ActivityStarter.class)).postQSRunnableDismissingKeyguard(new Runnable() {
                    public final void run() {
                        CellularTile.this.lambda$handleSecondaryClick$1$CellularTile();
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$handleSecondaryClick$1$CellularTile() {
        this.mHost.openPanels();
        showDetail(true);
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_cellular_detail_title);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.SignalState signalState, Object obj) {
        Object obj2;
        CallbackInfo callbackInfo = (CallbackInfo) obj;
        if (callbackInfo == null) {
            callbackInfo = this.mSignalCallback.mInfo;
        }
        signalState.dualTarget = true;
        Resources resources = this.mContext.getResources();
        signalState.label = resources.getString(C1784R$string.mobile_data);
        boolean z = this.mDataController.isMobileDataSupported() && this.mDataController.isMobileDataEnabled();
        signalState.value = z;
        signalState.activityIn = z && callbackInfo.activityIn;
        signalState.activityOut = z && callbackInfo.activityOut;
        signalState.expandedAccessibilityClassName = Switch.class.getName();
        if (callbackInfo.noSim) {
            signalState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_no_sim);
        } else {
            signalState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_swap_vert);
        }
        if (callbackInfo.noSim) {
            signalState.state = 0;
            signalState.secondaryLabel = resources.getString(C1784R$string.keyguard_missing_sim_message_short);
        } else if (callbackInfo.airplaneModeEnabled) {
            signalState.state = 0;
            signalState.secondaryLabel = resources.getString(C1784R$string.status_bar_airplane);
        } else if (z) {
            signalState.state = 2;
            signalState.secondaryLabel = appendMobileDataType(callbackInfo.multipleSubs ? callbackInfo.dataSubscriptionName : "", getMobileDataContentName(callbackInfo));
        } else {
            signalState.state = 1;
            signalState.secondaryLabel = resources.getString(C1784R$string.cell_data_off);
        }
        if (signalState.state == 1) {
            obj2 = resources.getString(C1784R$string.cell_data_off_content_description);
        } else {
            obj2 = signalState.secondaryLabel;
        }
        signalState.contentDescription = signalState.label + ", " + obj2;
    }

    private CharSequence appendMobileDataType(CharSequence charSequence, CharSequence charSequence2) {
        if (TextUtils.isEmpty(charSequence2)) {
            return Html.fromHtml(charSequence.toString(), 0);
        }
        if (TextUtils.isEmpty(charSequence)) {
            return Html.fromHtml(charSequence2.toString(), 0);
        }
        return Html.fromHtml(this.mContext.getString(C1784R$string.mobile_carrier_text_format, new Object[]{charSequence, charSequence2}), 0);
    }

    private CharSequence getMobileDataContentName(CallbackInfo callbackInfo) {
        if (callbackInfo.roaming && !TextUtils.isEmpty(callbackInfo.dataContentDescription)) {
            String string = this.mContext.getString(C1784R$string.data_connection_roaming);
            String charSequence = callbackInfo.dataContentDescription.toString();
            return this.mContext.getString(C1784R$string.mobile_data_text_format, new Object[]{string, charSequence});
        } else if (callbackInfo.roaming) {
            return this.mContext.getString(C1784R$string.data_connection_roaming);
        } else {
            return callbackInfo.dataContentDescription;
        }
    }

    public boolean isAvailable() {
        return this.mController.hasMobileDataFeature();
    }

    /* renamed from: com.android.systemui.qs.tiles.CellularTile$CallbackInfo */
    private static final class CallbackInfo {
        boolean activityIn;
        boolean activityOut;
        boolean airplaneModeEnabled;
        CharSequence dataContentDescription;
        CharSequence dataSubscriptionName;
        boolean multipleSubs;
        boolean noSim;
        boolean roaming;

        private CallbackInfo() {
        }
    }

    /* renamed from: com.android.systemui.qs.tiles.CellularTile$CellSignalCallback */
    private final class CellSignalCallback implements NetworkController.SignalCallback {
        /* access modifiers changed from: private */
        public final CallbackInfo mInfo;

        private CellSignalCallback() {
            this.mInfo = new CallbackInfo();
        }

        public void setMobileDataIndicators(NetworkController.IconState iconState, NetworkController.IconState iconState2, int i, int i2, boolean z, boolean z2, int i3, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, boolean z3, int i4, boolean z4) {
            if (iconState2 != null) {
                this.mInfo.dataSubscriptionName = CellularTile.this.mController.getMobileDataNetworkName();
                CallbackInfo callbackInfo = this.mInfo;
                if (charSequence3 == null) {
                    charSequence2 = null;
                }
                callbackInfo.dataContentDescription = charSequence2;
                CallbackInfo callbackInfo2 = this.mInfo;
                callbackInfo2.activityIn = z;
                callbackInfo2.activityOut = z2;
                callbackInfo2.roaming = z4;
                boolean z5 = true;
                if (CellularTile.this.mController.getNumberSubscriptions() <= 1) {
                    z5 = false;
                }
                callbackInfo2.multipleSubs = z5;
                CellularTile.this.refreshState(this.mInfo);
            }
        }

        public void setNoSims(boolean z, boolean z2) {
            CallbackInfo callbackInfo = this.mInfo;
            callbackInfo.noSim = z;
            CellularTile.this.refreshState(callbackInfo);
        }

        public void setIsAirplaneMode(NetworkController.IconState iconState) {
            CallbackInfo callbackInfo = this.mInfo;
            callbackInfo.airplaneModeEnabled = iconState.visible;
            CellularTile.this.refreshState(callbackInfo);
        }

        public void setMobileDataEnabled(boolean z) {
            CellularTile.this.mDetailAdapter.setMobileDataEnabled(z);
        }
    }

    static Intent getCellularSettingIntent() {
        return new Intent("android.settings.panel.action.MOBILE_DATA");
    }

    /* renamed from: com.android.systemui.qs.tiles.CellularTile$CellularDetailAdapter */
    private final class CellularDetailAdapter implements DetailAdapter {
        public int getMetricsCategory() {
            return R$styleable.AppCompatTheme_windowActionModeOverlay;
        }

        private CellularDetailAdapter() {
        }

        public CharSequence getTitle() {
            return CellularTile.this.mContext.getString(C1784R$string.quick_settings_cellular_detail_title);
        }

        public Boolean getToggleState() {
            if (CellularTile.this.mDataController.isMobileDataSupported()) {
                return Boolean.valueOf(CellularTile.this.mDataController.isMobileDataEnabled());
            }
            return null;
        }

        public Intent getSettingsIntent() {
            return CellularTile.getCellularSettingIntent();
        }

        public void setToggleState(boolean z) {
            MetricsLogger.action(CellularTile.this.mContext, 155, z);
            CellularTile.this.mDataController.setMobileDataEnabled(z);
        }

        public View createDetailView(Context context, View view, ViewGroup viewGroup) {
            DataUsageController.DataUsageInfo dataUsageInfo;
            int i = 0;
            if (view == null) {
                view = LayoutInflater.from(CellularTile.this.mContext).inflate(C1779R$layout.data_usage, viewGroup, false);
            }
            DataUsageDetailView dataUsageDetailView = (DataUsageDetailView) view;
            int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
            boolean z = true;
            if (Settings.System.getInt(CellularTile.this.mContext.getContentResolver(), "data_usage_period", 1) != 0) {
                z = false;
            }
            if (defaultDataSubscriptionId == -1) {
                dataUsageInfo = CellularTile.this.mDataController.getDataUsageInfo();
            } else if (z) {
                dataUsageInfo = CellularTile.this.mDataController.getDailyDataUsageInfo(DataUsageUtils.getMobileTemplate(CellularTile.this.mContext, defaultDataSubscriptionId));
            } else {
                dataUsageInfo = CellularTile.this.mDataController.getDataUsageInfo(DataUsageUtils.getMobileTemplate(CellularTile.this.mContext, defaultDataSubscriptionId));
            }
            if (dataUsageInfo == null) {
                return dataUsageDetailView;
            }
            dataUsageDetailView.bind(dataUsageInfo);
            View findViewById = dataUsageDetailView.findViewById(C1777R$id.roaming_text);
            if (!CellularTile.this.mSignalCallback.mInfo.roaming) {
                i = 4;
            }
            findViewById.setVisibility(i);
            return dataUsageDetailView;
        }

        public void setMobileDataEnabled(boolean z) {
            CellularTile.this.fireToggleStateChanged(z);
        }
    }

    /* renamed from: com.android.systemui.qs.tiles.CellularTile$KeyguardCallback */
    private final class KeyguardCallback implements KeyguardMonitor.Callback {
        private KeyguardCallback() {
        }

        public void onKeyguardShowingChanged() {
            CellularTile.this.refreshState();
        }
    }
}
