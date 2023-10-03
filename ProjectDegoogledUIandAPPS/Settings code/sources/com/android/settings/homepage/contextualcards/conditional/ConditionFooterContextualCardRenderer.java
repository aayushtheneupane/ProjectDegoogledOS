package com.android.settings.homepage.contextualcards.conditional;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settings.homepage.contextualcards.ContextualCard;
import com.android.settings.homepage.contextualcards.ContextualCardRenderer;
import com.android.settings.homepage.contextualcards.ControllerRendererPool;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;

public class ConditionFooterContextualCardRenderer implements ContextualCardRenderer {
    private final Context mContext;
    private final ControllerRendererPool mControllerRendererPool;

    public ConditionFooterContextualCardRenderer(Context context, ControllerRendererPool controllerRendererPool) {
        this.mContext = context;
        this.mControllerRendererPool = controllerRendererPool;
    }

    public RecyclerView.ViewHolder createViewHolder(View view, int i) {
        return new ConditionFooterCardHolder(view);
    }

    public void bindView(RecyclerView.ViewHolder viewHolder, ContextualCard contextualCard) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener(FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider()) {
            private final /* synthetic */ MetricsFeatureProvider f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                ConditionFooterContextualCardRenderer.this.lambda$bindView$0$ConditionFooterContextualCardRenderer(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$bindView$0$ConditionFooterContextualCardRenderer(MetricsFeatureProvider metricsFeatureProvider, View view) {
        metricsFeatureProvider.action(0, 373, 1502, (String) null, 0);
        ConditionContextualCardController conditionContextualCardController = (ConditionContextualCardController) this.mControllerRendererPool.getController(this.mContext, 5);
        conditionContextualCardController.setIsExpanded(false);
        conditionContextualCardController.onConditionsChanged();
    }

    public static class ConditionFooterCardHolder extends RecyclerView.ViewHolder {
        public ConditionFooterCardHolder(View view) {
            super(view);
        }
    }
}
