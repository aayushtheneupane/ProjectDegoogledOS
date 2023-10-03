package com.android.settings.homepage.contextualcards.conditional;

import android.content.Context;
import android.os.PowerManager;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.fuelgauge.BatterySaverReceiver;
import com.android.settings.fuelgauge.batterysaver.BatterySaverSettings;
import com.android.settings.homepage.contextualcards.ContextualCard;
import com.android.settings.homepage.contextualcards.conditional.ConditionalContextualCard;
import com.android.settingslib.fuelgauge.BatterySaverUtils;
import com.havoc.config.center.C1715R;
import java.util.Objects;

public class BatterySaverConditionController implements ConditionalCardController, BatterySaverReceiver.BatterySaverListener {

    /* renamed from: ID */
    static final int f40ID = Objects.hash(new Object[]{"BatterySaverConditionController"});
    private final Context mAppContext;
    private final ConditionManager mConditionManager;
    private final PowerManager mPowerManager;
    private final BatterySaverReceiver mReceiver;

    public void onBatteryChanged(boolean z) {
    }

    public BatterySaverConditionController(Context context, ConditionManager conditionManager) {
        this.mAppContext = context;
        this.mConditionManager = conditionManager;
        this.mPowerManager = (PowerManager) context.getSystemService(PowerManager.class);
        this.mReceiver = new BatterySaverReceiver(context);
        this.mReceiver.setBatterySaverListener(this);
    }

    public long getId() {
        return (long) f40ID;
    }

    public boolean isDisplayable() {
        return this.mPowerManager.isPowerSaveMode();
    }

    public void onPrimaryClick(Context context) {
        new SubSettingLauncher(context).setDestination(BatterySaverSettings.class.getName()).setSourceMetricsCategory(35).setTitleRes(C1715R.string.battery_saver).launch();
    }

    public void onActionClick() {
        BatterySaverUtils.setPowerSaveMode(this.mAppContext, false, false);
    }

    public ContextualCard buildContextualCard() {
        ConditionalContextualCard.Builder builder = new ConditionalContextualCard.Builder();
        builder.setConditionId((long) f40ID);
        builder.setMetricsConstant(379);
        builder.setActionText(this.mAppContext.getText(C1715R.string.condition_turn_off));
        builder.setName(this.mAppContext.getPackageName() + "/" + this.mAppContext.getText(C1715R.string.condition_battery_title));
        builder.setTitleText(this.mAppContext.getText(C1715R.string.condition_battery_title).toString());
        builder.setSummaryText(this.mAppContext.getText(C1715R.string.condition_battery_summary).toString());
        builder.setIconDrawable(this.mAppContext.getDrawable(C1715R.C1717drawable.ic_battery_saver_accent_24dp));
        builder.setViewType(C1715R.layout.conditional_card_half_tile);
        return builder.build();
    }

    public void startMonitoringStateChange() {
        this.mReceiver.setListening(true);
    }

    public void stopMonitoringStateChange() {
        this.mReceiver.setListening(false);
    }

    public void onPowerSaveModeChanged() {
        this.mConditionManager.onConditionChanged();
    }
}
