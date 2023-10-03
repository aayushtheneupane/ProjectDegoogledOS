package com.android.settings.homepage.contextualcards.conditional;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkPolicyManager;
import com.android.settings.Settings;
import com.android.settings.homepage.contextualcards.ContextualCard;
import com.android.settings.homepage.contextualcards.conditional.ConditionalContextualCard;
import com.havoc.config.center.C1715R;
import java.util.Objects;

public class BackgroundDataConditionController implements ConditionalCardController {

    /* renamed from: ID */
    static final int f39ID = Objects.hash(new Object[]{"BackgroundDataConditionController"});
    private final Context mAppContext;
    private final ConditionManager mConditionManager;
    private final NetworkPolicyManager mNetworkPolicyManager;

    public void startMonitoringStateChange() {
    }

    public void stopMonitoringStateChange() {
    }

    public BackgroundDataConditionController(Context context, ConditionManager conditionManager) {
        this.mAppContext = context;
        this.mConditionManager = conditionManager;
        this.mNetworkPolicyManager = (NetworkPolicyManager) context.getSystemService("netpolicy");
    }

    public long getId() {
        return (long) f39ID;
    }

    public boolean isDisplayable() {
        return this.mNetworkPolicyManager.getRestrictBackground();
    }

    public void onPrimaryClick(Context context) {
        context.startActivity(new Intent(context, Settings.DataUsageSummaryActivity.class));
    }

    public void onActionClick() {
        this.mNetworkPolicyManager.setRestrictBackground(false);
        this.mConditionManager.onConditionChanged();
    }

    public ContextualCard buildContextualCard() {
        ConditionalContextualCard.Builder builder = new ConditionalContextualCard.Builder();
        builder.setConditionId((long) f39ID);
        builder.setMetricsConstant(378);
        builder.setActionText(this.mAppContext.getText(C1715R.string.condition_turn_off));
        builder.setName(this.mAppContext.getPackageName() + "/" + this.mAppContext.getText(C1715R.string.condition_bg_data_title));
        builder.setTitleText(this.mAppContext.getText(C1715R.string.condition_bg_data_title).toString());
        builder.setSummaryText(this.mAppContext.getText(C1715R.string.condition_bg_data_summary).toString());
        builder.setIconDrawable(this.mAppContext.getDrawable(C1715R.C1717drawable.ic_data_saver));
        builder.setViewType(C1715R.layout.conditional_card_half_tile);
        return builder.build();
    }
}
