package com.android.settings.homepage.contextualcards.conditional;

import android.content.Context;
import com.android.settings.homepage.contextualcards.ContextualCard;
import com.android.settings.homepage.contextualcards.conditional.ConditionalContextualCard;
import com.havoc.config.center.C1715R;
import java.util.Objects;

public class RingerMutedConditionController extends AbnormalRingerConditionController {

    /* renamed from: ID */
    static final int f46ID = Objects.hash(new Object[]{"RingerMutedConditionController"});
    private final Context mAppContext;

    public RingerMutedConditionController(Context context, ConditionManager conditionManager) {
        super(context, conditionManager);
        this.mAppContext = context;
    }

    public long getId() {
        return (long) f46ID;
    }

    public boolean isDisplayable() {
        return this.mAudioManager.getRingerModeInternal() == 0;
    }

    public ContextualCard buildContextualCard() {
        ConditionalContextualCard.Builder builder = new ConditionalContextualCard.Builder();
        builder.setConditionId((long) f46ID);
        builder.setMetricsConstant(1368);
        builder.setActionText(this.mAppContext.getText(C1715R.string.condition_device_muted_action_turn_on_sound));
        builder.setName(this.mAppContext.getPackageName() + "/" + this.mAppContext.getText(C1715R.string.condition_device_muted_title));
        builder.setTitleText(this.mAppContext.getText(C1715R.string.condition_device_muted_title).toString());
        builder.setSummaryText(this.mAppContext.getText(C1715R.string.condition_device_muted_summary).toString());
        builder.setIconDrawable(this.mAppContext.getDrawable(C1715R.C1717drawable.ic_notifications_off_24dp));
        builder.setViewType(C1715R.layout.conditional_card_half_tile);
        return builder.build();
    }
}
