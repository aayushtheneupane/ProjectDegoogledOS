package com.android.settings.homepage.contextualcards.legacysuggestion;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settings.homepage.contextualcards.ContextualCard;
import com.android.settings.homepage.contextualcards.ContextualCardRenderer;
import com.android.settings.homepage.contextualcards.ControllerRendererPool;

public class LegacySuggestionContextualCardRenderer implements ContextualCardRenderer {
    private final Context mContext;
    private final ControllerRendererPool mControllerRendererPool;

    public LegacySuggestionContextualCardRenderer(Context context, ControllerRendererPool controllerRendererPool) {
        this.mContext = context;
        this.mControllerRendererPool = controllerRendererPool;
    }

    public RecyclerView.ViewHolder createViewHolder(View view, int i) {
        return new LegacySuggestionViewHolder(view);
    }

    public void bindView(RecyclerView.ViewHolder viewHolder, ContextualCard contextualCard) {
        LegacySuggestionViewHolder legacySuggestionViewHolder = (LegacySuggestionViewHolder) viewHolder;
        legacySuggestionViewHolder.icon.setImageDrawable(contextualCard.getIconDrawable());
        legacySuggestionViewHolder.title.setText(contextualCard.getTitleText());
        legacySuggestionViewHolder.summary.setText(contextualCard.getSummaryText());
        legacySuggestionViewHolder.itemView.setOnClickListener(new View.OnClickListener(contextualCard) {
            private final /* synthetic */ ContextualCard f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                LegacySuggestionContextualCardRenderer.this.lambda$bindView$0$LegacySuggestionContextualCardRenderer(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$bindView$0$LegacySuggestionContextualCardRenderer(ContextualCard contextualCard, View view) {
        this.mControllerRendererPool.getController(this.mContext, contextualCard.getCardType()).onPrimaryClick(contextualCard);
    }

    private static class LegacySuggestionViewHolder extends RecyclerView.ViewHolder {
        public final ImageView icon;
        public final TextView summary;
        public final TextView title;

        public LegacySuggestionViewHolder(View view) {
            super(view);
            this.icon = (ImageView) view.findViewById(16908294);
            this.title = (TextView) view.findViewById(16908310);
            this.summary = (TextView) view.findViewById(16908304);
        }
    }
}
