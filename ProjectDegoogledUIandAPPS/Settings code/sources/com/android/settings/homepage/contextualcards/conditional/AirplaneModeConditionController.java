package com.android.settings.homepage.contextualcards.conditional;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import com.android.settings.homepage.contextualcards.ContextualCard;
import com.android.settings.homepage.contextualcards.conditional.ConditionalContextualCard;
import com.android.settingslib.WirelessUtils;
import com.havoc.config.center.C1715R;
import java.util.Objects;

public class AirplaneModeConditionController implements ConditionalCardController {
    private static final IntentFilter AIRPLANE_MODE_FILTER = new IntentFilter("android.intent.action.AIRPLANE_MODE");

    /* renamed from: ID */
    static final int f38ID = Objects.hash(new Object[]{"AirplaneModeConditionController"});
    private final Context mAppContext;
    /* access modifiers changed from: private */
    public final ConditionManager mConditionManager;
    private final Receiver mReceiver = new Receiver();

    public AirplaneModeConditionController(Context context, ConditionManager conditionManager) {
        this.mAppContext = context;
        this.mConditionManager = conditionManager;
    }

    public long getId() {
        return (long) f38ID;
    }

    public boolean isDisplayable() {
        return WirelessUtils.isAirplaneModeOn(this.mAppContext);
    }

    public void onPrimaryClick(Context context) {
        context.startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
    }

    public void onActionClick() {
        ConnectivityManager.from(this.mAppContext).setAirplaneMode(false);
    }

    public ContextualCard buildContextualCard() {
        ConditionalContextualCard.Builder builder = new ConditionalContextualCard.Builder();
        builder.setConditionId((long) f38ID);
        builder.setMetricsConstant(377);
        builder.setActionText(this.mAppContext.getText(C1715R.string.condition_turn_off));
        builder.setName(this.mAppContext.getPackageName() + "/" + this.mAppContext.getText(C1715R.string.condition_airplane_title));
        builder.setTitleText(this.mAppContext.getText(C1715R.string.condition_airplane_title).toString());
        builder.setSummaryText(this.mAppContext.getText(C1715R.string.condition_airplane_summary).toString());
        builder.setIconDrawable(this.mAppContext.getDrawable(C1715R.C1717drawable.ic_airplanemode_active));
        builder.setViewType(C1715R.layout.conditional_card_half_tile);
        return builder.build();
    }

    public void startMonitoringStateChange() {
        this.mAppContext.registerReceiver(this.mReceiver, AIRPLANE_MODE_FILTER);
    }

    public void stopMonitoringStateChange() {
        this.mAppContext.unregisterReceiver(this.mReceiver);
    }

    public class Receiver extends BroadcastReceiver {
        public Receiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.AIRPLANE_MODE".equals(intent.getAction())) {
                AirplaneModeConditionController.this.mConditionManager.onConditionChanged();
            }
        }
    }
}
