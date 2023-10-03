package com.android.settings.homepage.contextualcards.conditional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settings.homepage.contextualcards.ContextualCard;
import com.android.settings.homepage.contextualcards.ContextualCardRenderer;
import com.android.settings.homepage.contextualcards.ControllerRendererPool;
import com.android.settings.homepage.contextualcards.conditional.ConditionHeaderContextualCardRenderer;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.havoc.config.center.C1715R;
import java.util.function.Consumer;

public class ConditionHeaderContextualCardRenderer implements ContextualCardRenderer {
    private final Context mContext;
    private final ControllerRendererPool mControllerRendererPool;

    public ConditionHeaderContextualCardRenderer(Context context, ControllerRendererPool controllerRendererPool) {
        this.mContext = context;
        this.mControllerRendererPool = controllerRendererPool;
    }

    public RecyclerView.ViewHolder createViewHolder(View view, int i) {
        return new ConditionHeaderCardHolder(view);
    }

    public void bindView(RecyclerView.ViewHolder viewHolder, ContextualCard contextualCard) {
        ConditionHeaderCardHolder conditionHeaderCardHolder = (ConditionHeaderCardHolder) viewHolder;
        MetricsFeatureProvider metricsFeatureProvider = FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider();
        conditionHeaderCardHolder.icons.removeAllViews();
        ((ConditionHeaderContextualCard) contextualCard).getConditionalCards().stream().forEach(new Consumer(conditionHeaderCardHolder) {
            private final /* synthetic */ ConditionHeaderContextualCardRenderer.ConditionHeaderCardHolder f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                ConditionHeaderContextualCardRenderer.this.lambda$bindView$0$ConditionHeaderContextualCardRenderer(this.f$1, (ContextualCard) obj);
            }
        });
        conditionHeaderCardHolder.itemView.setOnClickListener(new View.OnClickListener(metricsFeatureProvider) {
            private final /* synthetic */ MetricsFeatureProvider f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                ConditionHeaderContextualCardRenderer.this.lambda$bindView$1$ConditionHeaderContextualCardRenderer(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$bindView$0$ConditionHeaderContextualCardRenderer(ConditionHeaderCardHolder conditionHeaderCardHolder, ContextualCard contextualCard) {
        ImageView imageView = (ImageView) LayoutInflater.from(this.mContext).inflate(C1715R.layout.conditional_card_header_icon, conditionHeaderCardHolder.icons, false);
        imageView.setImageDrawable(contextualCard.getIconDrawable());
        conditionHeaderCardHolder.icons.addView(imageView);
    }

    public /* synthetic */ void lambda$bindView$1$ConditionHeaderContextualCardRenderer(MetricsFeatureProvider metricsFeatureProvider, View view) {
        metricsFeatureProvider.action(0, 373, 1502, (String) null, 1);
        ConditionContextualCardController conditionContextualCardController = (ConditionContextualCardController) this.mControllerRendererPool.getController(this.mContext, 4);
        conditionContextualCardController.setIsExpanded(true);
        conditionContextualCardController.onConditionsChanged();
    }

    public static class ConditionHeaderCardHolder extends RecyclerView.ViewHolder {
        public final ImageView expandIndicator;
        public final LinearLayout icons;

        public ConditionHeaderCardHolder(View view) {
            super(view);
            this.icons = (LinearLayout) view.findViewById(C1715R.C1718id.header_icons_container);
            this.expandIndicator = (ImageView) view.findViewById(C1715R.C1718id.expand_indicator);
        }
    }
}
