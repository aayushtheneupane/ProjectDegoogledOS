package com.android.settings.homepage.contextualcards.slices;

import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slice.Slice;
import androidx.slice.SliceMetadata;
import androidx.slice.core.SliceAction;
import com.android.settings.homepage.contextualcards.ContextualCard;
import com.android.settings.homepage.contextualcards.logging.ContextualCardLogUtils;
import com.android.settings.homepage.contextualcards.slices.SliceHalfCardRendererHelper;
import com.android.settings.overlay.FeatureFactory;
import com.havoc.config.center.C1715R;

class SliceHalfCardRendererHelper {
    private final Context mContext;

    SliceHalfCardRendererHelper(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: package-private */
    public RecyclerView.ViewHolder createViewHolder(View view) {
        return new HalfCardViewHolder(view);
    }

    /* access modifiers changed from: package-private */
    public void bindView(RecyclerView.ViewHolder viewHolder, ContextualCard contextualCard, Slice slice) {
        HalfCardViewHolder halfCardViewHolder = (HalfCardViewHolder) viewHolder;
        SliceAction primaryAction = SliceMetadata.from(this.mContext, slice).getPrimaryAction();
        if (this.mContext != null) {
            halfCardViewHolder.icon.setImageDrawable(primaryAction.getIcon().loadDrawable(this.mContext));
            halfCardViewHolder.title.setText(primaryAction.getTitle());
            halfCardViewHolder.content.setOnClickListener(new View.OnClickListener(primaryAction, contextualCard, halfCardViewHolder) {
                private final /* synthetic */ SliceAction f$1;
                private final /* synthetic */ ContextualCard f$2;
                private final /* synthetic */ SliceHalfCardRendererHelper.HalfCardViewHolder f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void onClick(View view) {
                    SliceHalfCardRendererHelper.this.lambda$bindView$0$SliceHalfCardRendererHelper(this.f$1, this.f$2, this.f$3, view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$bindView$0$SliceHalfCardRendererHelper(SliceAction sliceAction, ContextualCard contextualCard, HalfCardViewHolder halfCardViewHolder, View view) {
        try {
            sliceAction.getAction().send();
        } catch (PendingIntent.CanceledException unused) {
            Log.w("SliceHCRendererHelper", "Failed to start intent " + sliceAction.getTitle());
        }
        FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider().action(this.mContext, 1666, ContextualCardLogUtils.buildCardClickLog(contextualCard, 0, 3, halfCardViewHolder.getAdapterPosition()));
    }

    static class HalfCardViewHolder extends RecyclerView.ViewHolder {
        public final LinearLayout content;
        public final ImageView icon;
        public final TextView title;

        public HalfCardViewHolder(View view) {
            super(view);
            this.content = (LinearLayout) view.findViewById(C1715R.C1718id.content);
            this.icon = (ImageView) view.findViewById(16908294);
            this.title = (TextView) view.findViewById(16908310);
        }
    }
}
