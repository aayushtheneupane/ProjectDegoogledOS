package com.android.settings.homepage.contextualcards.conditional;

import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import android.hardware.display.NightDisplayListener;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.display.NightDisplaySettings;
import com.android.settings.homepage.contextualcards.ContextualCard;
import com.android.settings.homepage.contextualcards.conditional.ConditionalContextualCard;
import com.havoc.config.center.C1715R;
import java.util.Objects;

public class NightDisplayConditionController implements ConditionalCardController, NightDisplayListener.Callback {

    /* renamed from: ID */
    static final int f45ID = Objects.hash(new Object[]{"NightDisplayConditionController"});
    private final Context mAppContext;
    private final ColorDisplayManager mColorDisplayManager;
    private final ConditionManager mConditionManager;
    private final NightDisplayListener mNightDisplayListener;

    public NightDisplayConditionController(Context context, ConditionManager conditionManager) {
        this.mAppContext = context;
        this.mConditionManager = conditionManager;
        this.mColorDisplayManager = (ColorDisplayManager) context.getSystemService(ColorDisplayManager.class);
        this.mNightDisplayListener = new NightDisplayListener(context);
    }

    public long getId() {
        return (long) f45ID;
    }

    public boolean isDisplayable() {
        return this.mColorDisplayManager.isNightDisplayActivated();
    }

    public void onPrimaryClick(Context context) {
        new SubSettingLauncher(context).setDestination(NightDisplaySettings.class.getName()).setSourceMetricsCategory(1502).setTitleRes(C1715R.string.night_display_title).launch();
    }

    public void onActionClick() {
        this.mColorDisplayManager.setNightDisplayActivated(false);
    }

    public ContextualCard buildContextualCard() {
        ConditionalContextualCard.Builder builder = new ConditionalContextualCard.Builder();
        builder.setConditionId((long) f45ID);
        builder.setMetricsConstant(492);
        builder.setActionText(this.mAppContext.getText(C1715R.string.condition_turn_off));
        builder.setName(this.mAppContext.getPackageName() + "/" + this.mAppContext.getText(C1715R.string.condition_night_display_title));
        builder.setTitleText(this.mAppContext.getText(C1715R.string.condition_night_display_title).toString());
        builder.setSummaryText(this.mAppContext.getText(C1715R.string.condition_night_display_summary).toString());
        builder.setIconDrawable(this.mAppContext.getDrawable(C1715R.C1717drawable.ic_settings_night_display));
        builder.setViewType(C1715R.layout.conditional_card_half_tile);
        return builder.build();
    }

    public void startMonitoringStateChange() {
        this.mNightDisplayListener.setCallback(this);
    }

    public void stopMonitoringStateChange() {
        this.mNightDisplayListener.setCallback((NightDisplayListener.Callback) null);
    }

    public void onActivated(boolean z) {
        this.mConditionManager.onConditionChanged();
    }
}
