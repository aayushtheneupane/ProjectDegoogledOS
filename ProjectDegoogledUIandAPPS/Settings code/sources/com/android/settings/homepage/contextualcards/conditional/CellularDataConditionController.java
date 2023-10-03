package com.android.settings.homepage.contextualcards.conditional;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import com.android.settings.Settings;
import com.android.settings.homepage.contextualcards.ContextualCard;
import com.android.settings.homepage.contextualcards.conditional.ConditionalContextualCard;
import com.havoc.config.center.C1715R;
import java.util.Objects;

public class CellularDataConditionController implements ConditionalCardController {
    private static final IntentFilter DATA_CONNECTION_FILTER = new IntentFilter("android.intent.action.ANY_DATA_STATE");

    /* renamed from: ID */
    static final int f41ID = Objects.hash(new Object[]{"CellularDataConditionController"});
    private final Context mAppContext;
    /* access modifiers changed from: private */
    public final ConditionManager mConditionManager;
    private final ConnectivityManager mConnectivityManager;
    private final Receiver mReceiver = new Receiver();
    private final TelephonyManager mTelephonyManager;

    public CellularDataConditionController(Context context, ConditionManager conditionManager) {
        this.mAppContext = context;
        this.mConditionManager = conditionManager;
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
    }

    public long getId() {
        return (long) f41ID;
    }

    public boolean isDisplayable() {
        if (!this.mConnectivityManager.isNetworkSupported(0) || this.mTelephonyManager.getSimState() != 5) {
            return false;
        }
        return !this.mTelephonyManager.isDataEnabled();
    }

    public void onPrimaryClick(Context context) {
        context.startActivity(new Intent(context, Settings.DataUsageSummaryActivity.class));
    }

    public void onActionClick() {
        this.mTelephonyManager.setDataEnabled(true);
    }

    public ContextualCard buildContextualCard() {
        ConditionalContextualCard.Builder builder = new ConditionalContextualCard.Builder();
        builder.setConditionId((long) f41ID);
        builder.setMetricsConstant(380);
        builder.setActionText(this.mAppContext.getText(C1715R.string.condition_turn_on));
        builder.setName(this.mAppContext.getPackageName() + "/" + this.mAppContext.getText(C1715R.string.condition_cellular_title));
        builder.setTitleText(this.mAppContext.getText(C1715R.string.condition_cellular_title).toString());
        builder.setSummaryText(this.mAppContext.getText(C1715R.string.condition_cellular_summary).toString());
        builder.setIconDrawable(this.mAppContext.getDrawable(C1715R.C1717drawable.ic_cellular_off));
        builder.setViewType(C1715R.layout.conditional_card_half_tile);
        return builder.build();
    }

    public void startMonitoringStateChange() {
        this.mAppContext.registerReceiver(this.mReceiver, DATA_CONNECTION_FILTER);
    }

    public void stopMonitoringStateChange() {
        this.mAppContext.unregisterReceiver(this.mReceiver);
    }

    public class Receiver extends BroadcastReceiver {
        public Receiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.ANY_DATA_STATE".equals(intent.getAction())) {
                CellularDataConditionController.this.mConditionManager.onConditionChanged();
            }
        }
    }
}
