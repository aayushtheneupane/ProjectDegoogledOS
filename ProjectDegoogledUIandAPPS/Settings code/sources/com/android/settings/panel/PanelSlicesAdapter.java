package com.android.settings.panel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slice.Slice;
import androidx.slice.SliceItem;
import androidx.slice.widget.EventInfo;
import androidx.slice.widget.SliceView;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.panel.PanelSlicesAdapter;
import com.android.settings.slices.CustomSliceRegistry;
import com.google.android.setupdesign.DividerItemDecoration;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class PanelSlicesAdapter extends RecyclerView.Adapter<SliceRowViewHolder> {
    static final int MAX_NUM_OF_SLICES = 6;
    /* access modifiers changed from: private */
    public final int mMetricsCategory;
    /* access modifiers changed from: private */
    public final PanelFragment mPanelFragment;
    private final List<LiveData<Slice>> mSliceLiveData;

    public PanelSlicesAdapter(PanelFragment panelFragment, List<LiveData<Slice>> list, int i) {
        this.mPanelFragment = panelFragment;
        this.mSliceLiveData = new ArrayList(list);
        this.mMetricsCategory = i;
    }

    public SliceRowViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SliceRowViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C1715R.layout.panel_slice_row, viewGroup, false));
    }

    public void onBindViewHolder(SliceRowViewHolder sliceRowViewHolder, int i) {
        sliceRowViewHolder.onBind(this.mSliceLiveData.get(i));
    }

    public int getItemCount() {
        return Math.min(this.mSliceLiveData.size(), 6);
    }

    /* access modifiers changed from: package-private */
    public List<LiveData<Slice>> getData() {
        return this.mSliceLiveData.subList(0, getItemCount());
    }

    public class SliceRowViewHolder extends RecyclerView.ViewHolder implements DividerItemDecoration.DividedViewHolder {
        private boolean mDividerAllowedAbove = true;
        final SliceView sliceView;

        public boolean isDividerAllowedBelow() {
            return true;
        }

        public SliceRowViewHolder(View view) {
            super(view);
            this.sliceView = (SliceView) view.findViewById(C1715R.C1718id.slice_view);
            this.sliceView.setMode(2);
            this.sliceView.showTitleItems(true);
        }

        public void onBind(LiveData<Slice> liveData) {
            liveData.observe(PanelSlicesAdapter.this.mPanelFragment.getViewLifecycleOwner(), this.sliceView);
            Slice value = liveData.getValue();
            if (value != null && value.getUri().equals(CustomSliceRegistry.MEDIA_OUTPUT_INDICATOR_SLICE_URI)) {
                this.mDividerAllowedAbove = false;
            }
            this.sliceView.setOnSliceActionListener(new SliceView.OnSliceActionListener(liveData) {
                private final /* synthetic */ LiveData f$1;

                {
                    this.f$1 = r2;
                }

                public final void onSliceAction(EventInfo eventInfo, SliceItem sliceItem) {
                    PanelSlicesAdapter.SliceRowViewHolder.this.lambda$onBind$0$PanelSlicesAdapter$SliceRowViewHolder(this.f$1, eventInfo, sliceItem);
                }
            });
        }

        public /* synthetic */ void lambda$onBind$0$PanelSlicesAdapter$SliceRowViewHolder(LiveData liveData, EventInfo eventInfo, SliceItem sliceItem) {
            FeatureFactory.getFactory(this.sliceView.getContext()).getMetricsFeatureProvider().action(0, 1658, PanelSlicesAdapter.this.mMetricsCategory, ((Slice) liveData.getValue()).getUri().getLastPathSegment(), eventInfo.actionType);
        }

        public boolean isDividerAllowedAbove() {
            return this.mDividerAllowedAbove;
        }
    }
}
