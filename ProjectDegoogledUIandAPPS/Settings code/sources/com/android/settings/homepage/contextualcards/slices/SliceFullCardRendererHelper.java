package com.android.settings.homepage.contextualcards.slices;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slice.Slice;
import androidx.slice.SliceItem;
import androidx.slice.widget.EventInfo;
import androidx.slice.widget.SliceView;
import com.android.settings.homepage.contextualcards.ContextualCard;
import com.android.settings.homepage.contextualcards.logging.ContextualCardLogUtils;
import com.android.settings.homepage.contextualcards.slices.SliceFullCardRendererHelper;
import com.android.settings.overlay.FeatureFactory;
import com.havoc.config.center.C1715R;

class SliceFullCardRendererHelper {
    private final Context mContext;

    SliceFullCardRendererHelper(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: package-private */
    public RecyclerView.ViewHolder createViewHolder(View view) {
        return new SliceViewHolder(view);
    }

    /* access modifiers changed from: package-private */
    public void bindView(RecyclerView.ViewHolder viewHolder, ContextualCard contextualCard, Slice slice) {
        SliceViewHolder sliceViewHolder = (SliceViewHolder) viewHolder;
        sliceViewHolder.sliceView.setScrollable(false);
        sliceViewHolder.sliceView.setTag(contextualCard.getSliceUri());
        sliceViewHolder.sliceView.setMode(2);
        sliceViewHolder.sliceView.setSlice(slice);
        sliceViewHolder.sliceView.setOnSliceActionListener(new SliceView.OnSliceActionListener(contextualCard, sliceViewHolder, slice) {
            private final /* synthetic */ ContextualCard f$1;
            private final /* synthetic */ SliceFullCardRendererHelper.SliceViewHolder f$2;
            private final /* synthetic */ Slice f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onSliceAction(EventInfo eventInfo, SliceItem sliceItem) {
                SliceFullCardRendererHelper.this.lambda$bindView$0$SliceFullCardRendererHelper(this.f$1, this.f$2, this.f$3, eventInfo, sliceItem);
            }
        });
        sliceViewHolder.sliceView.setShowTitleItems(true);
        if (contextualCard.isLargeCard()) {
            sliceViewHolder.sliceView.setShowHeaderDivider(true);
            sliceViewHolder.sliceView.setShowActionDividers(true);
        }
    }

    public /* synthetic */ void lambda$bindView$0$SliceFullCardRendererHelper(ContextualCard contextualCard, SliceViewHolder sliceViewHolder, Slice slice, EventInfo eventInfo, SliceItem sliceItem) {
        FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider().action(this.mContext, 1666, ContextualCardLogUtils.buildCardClickLog(contextualCard, eventInfo.rowIndex, eventInfo.actionType, sliceViewHolder.getAdapterPosition()));
        FeatureFactory.getFactory(this.mContext).getContextualCardFeatureProvider(this.mContext).logNotificationPackage(slice);
    }

    static class SliceViewHolder extends RecyclerView.ViewHolder {
        public final SliceView sliceView;

        public SliceViewHolder(View view) {
            super(view);
            this.sliceView = (SliceView) view.findViewById(C1715R.C1718id.slice_view);
        }
    }
}
