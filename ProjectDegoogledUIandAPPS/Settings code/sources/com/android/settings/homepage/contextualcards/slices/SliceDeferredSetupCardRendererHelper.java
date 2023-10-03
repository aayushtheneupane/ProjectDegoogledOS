package com.android.settings.homepage.contextualcards.slices;

import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slice.Slice;
import androidx.slice.SliceMetadata;
import androidx.slice.core.SliceAction;
import com.android.settings.homepage.contextualcards.ContextualCard;
import com.android.settings.homepage.contextualcards.logging.ContextualCardLogUtils;
import com.android.settings.homepage.contextualcards.slices.SliceDeferredSetupCardRendererHelper;
import com.android.settings.overlay.FeatureFactory;
import com.havoc.config.center.C1715R;

class SliceDeferredSetupCardRendererHelper {
    private final Context mContext;

    SliceDeferredSetupCardRendererHelper(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: package-private */
    public RecyclerView.ViewHolder createViewHolder(View view) {
        return new DeferredSetupCardViewHolder(view);
    }

    /* access modifiers changed from: package-private */
    public void bindView(RecyclerView.ViewHolder viewHolder, ContextualCard contextualCard, Slice slice) {
        DeferredSetupCardViewHolder deferredSetupCardViewHolder = (DeferredSetupCardViewHolder) viewHolder;
        SliceMetadata from = SliceMetadata.from(this.mContext, slice);
        SliceAction primaryAction = from.getPrimaryAction();
        deferredSetupCardViewHolder.icon.setImageDrawable(primaryAction.getIcon().loadDrawable(this.mContext));
        deferredSetupCardViewHolder.title.setText(primaryAction.getTitle());
        deferredSetupCardViewHolder.summary.setText(from.getSubtitle());
        deferredSetupCardViewHolder.button.setOnClickListener(new View.OnClickListener(primaryAction, contextualCard, deferredSetupCardViewHolder) {
            private final /* synthetic */ SliceAction f$1;
            private final /* synthetic */ ContextualCard f$2;
            private final /* synthetic */ SliceDeferredSetupCardRendererHelper.DeferredSetupCardViewHolder f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onClick(View view) {
                SliceDeferredSetupCardRendererHelper.this.lambda$bindView$0$SliceDeferredSetupCardRendererHelper(this.f$1, this.f$2, this.f$3, view);
            }
        });
    }

    public /* synthetic */ void lambda$bindView$0$SliceDeferredSetupCardRendererHelper(SliceAction sliceAction, ContextualCard contextualCard, DeferredSetupCardViewHolder deferredSetupCardViewHolder, View view) {
        try {
            sliceAction.getAction().send();
        } catch (PendingIntent.CanceledException unused) {
            Log.w("SliceDSCRendererHelper", "Failed to start intent " + sliceAction.getTitle());
        }
        FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider().action(this.mContext, 1666, ContextualCardLogUtils.buildCardClickLog(contextualCard, 0, 3, deferredSetupCardViewHolder.getAdapterPosition()));
    }

    static class DeferredSetupCardViewHolder extends RecyclerView.ViewHolder {
        public final Button button;
        public final LinearLayout content;
        public final ImageView icon;
        public final TextView summary;
        public final TextView title;

        public DeferredSetupCardViewHolder(View view) {
            super(view);
            this.content = (LinearLayout) view.findViewById(C1715R.C1718id.content);
            this.icon = (ImageView) view.findViewById(16908294);
            this.title = (TextView) view.findViewById(16908310);
            this.summary = (TextView) view.findViewById(16908304);
            this.button = (Button) view.findViewById(C1715R.C1718id.finish_setup);
        }
    }
}
