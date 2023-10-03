package com.android.settings.homepage.contextualcards.slices;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slice.Slice;
import androidx.slice.widget.SliceLiveData;
import com.android.settings.homepage.contextualcards.CardContentProvider;
import com.android.settings.homepage.contextualcards.ContextualCard;
import com.android.settings.homepage.contextualcards.ContextualCardRenderer;
import com.android.settings.homepage.contextualcards.ControllerRendererPool;
import com.android.settings.homepage.contextualcards.slices.SliceFullCardRendererHelper;
import com.android.settings.homepage.contextualcards.slices.SliceHalfCardRendererHelper;
import com.havoc.config.center.C1715R;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class SliceContextualCardRenderer implements ContextualCardRenderer, LifecycleObserver {
    private final Context mContext;
    private final ControllerRendererPool mControllerRendererPool;
    private final SliceDeferredSetupCardRendererHelper mDeferredSetupCardHelper;
    final Set<RecyclerView.ViewHolder> mFlippedCardSet;
    private final SliceFullCardRendererHelper mFullCardHelper;
    private final SliceHalfCardRendererHelper mHalfCardHelper;
    private final LifecycleOwner mLifecycleOwner;
    final Map<Uri, LiveData<Slice>> mSliceLiveDataMap = new ArrayMap();

    public SliceContextualCardRenderer(Context context, LifecycleOwner lifecycleOwner, ControllerRendererPool controllerRendererPool) {
        this.mContext = context;
        this.mLifecycleOwner = lifecycleOwner;
        this.mControllerRendererPool = controllerRendererPool;
        this.mFlippedCardSet = new ArraySet();
        this.mLifecycleOwner.getLifecycle().addObserver(this);
        this.mFullCardHelper = new SliceFullCardRendererHelper(context);
        this.mHalfCardHelper = new SliceHalfCardRendererHelper(context);
        this.mDeferredSetupCardHelper = new SliceDeferredSetupCardRendererHelper(context);
    }

    public RecyclerView.ViewHolder createViewHolder(View view, int i) {
        if (i == C1715R.layout.contextual_slice_deferred_setup) {
            return this.mDeferredSetupCardHelper.createViewHolder(view);
        }
        if (i != C1715R.layout.contextual_slice_half_tile) {
            return this.mFullCardHelper.createViewHolder(view);
        }
        return this.mHalfCardHelper.createViewHolder(view);
    }

    public void bindView(RecyclerView.ViewHolder viewHolder, ContextualCard contextualCard) {
        Uri sliceUri = contextualCard.getSliceUri();
        if (!"content".equals(sliceUri.getScheme())) {
            Log.w("SliceCardRenderer", "Invalid uri, skipping slice: " + sliceUri);
            return;
        }
        LiveData<Slice> liveData = this.mSliceLiveDataMap.get(sliceUri);
        if (liveData == null) {
            liveData = SliceLiveData.fromUri(this.mContext, sliceUri);
            this.mSliceLiveDataMap.put(sliceUri, liveData);
        }
        View findViewById = viewHolder.itemView.findViewById(C1715R.C1718id.dismissal_swipe_background);
        liveData.removeObservers(this.mLifecycleOwner);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        liveData.observe(this.mLifecycleOwner, new Observer(viewHolder, contextualCard, findViewById) {
            private final /* synthetic */ RecyclerView.ViewHolder f$1;
            private final /* synthetic */ ContextualCard f$2;
            private final /* synthetic */ View f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onChanged(Object obj) {
                SliceContextualCardRenderer.this.lambda$bindView$0$SliceContextualCardRenderer(this.f$1, this.f$2, this.f$3, (Slice) obj);
            }
        });
        int itemViewType = viewHolder.getItemViewType();
        if (itemViewType != C1715R.layout.contextual_slice_deferred_setup) {
            if (itemViewType != C1715R.layout.contextual_slice_half_tile) {
                initDismissalActions(viewHolder, contextualCard);
            } else {
                initDismissalActions(viewHolder, contextualCard);
            }
        }
        if (contextualCard.isPendingDismiss()) {
            showDismissalView(viewHolder);
            this.mFlippedCardSet.add(viewHolder);
        }
    }

    public /* synthetic */ void lambda$bindView$0$SliceContextualCardRenderer(RecyclerView.ViewHolder viewHolder, ContextualCard contextualCard, View view, Slice slice) {
        if (slice == null) {
            Log.w("SliceCardRenderer", "Slice is null");
            this.mContext.getContentResolver().notifyChange(CardContentProvider.REFRESH_CARD_URI, (ContentObserver) null);
        } else if (slice.hasHint("error")) {
            Log.w("SliceCardRenderer", "Slice has HINT_ERROR, skipping rendering. uri=" + slice.getUri());
            this.mSliceLiveDataMap.get(slice.getUri()).removeObservers(this.mLifecycleOwner);
            this.mContext.getContentResolver().notifyChange(CardContentProvider.REFRESH_CARD_URI, (ContentObserver) null);
        } else {
            int itemViewType = viewHolder.getItemViewType();
            if (itemViewType == C1715R.layout.contextual_slice_deferred_setup) {
                this.mDeferredSetupCardHelper.bindView(viewHolder, contextualCard, slice);
            } else if (itemViewType != C1715R.layout.contextual_slice_half_tile) {
                this.mFullCardHelper.bindView(viewHolder, contextualCard, slice);
            } else {
                this.mHalfCardHelper.bindView(viewHolder, contextualCard, slice);
            }
            if (view != null) {
                view.setVisibility(0);
            }
        }
    }

    private void initDismissalActions(RecyclerView.ViewHolder viewHolder, ContextualCard contextualCard) {
        ((Button) viewHolder.itemView.findViewById(C1715R.C1718id.keep)).setOnClickListener(new View.OnClickListener(viewHolder) {
            private final /* synthetic */ RecyclerView.ViewHolder f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                SliceContextualCardRenderer.this.lambda$initDismissalActions$1$SliceContextualCardRenderer(this.f$1, view);
            }
        });
        ((Button) viewHolder.itemView.findViewById(C1715R.C1718id.remove)).setOnClickListener(new View.OnClickListener(contextualCard, viewHolder) {
            private final /* synthetic */ ContextualCard f$1;
            private final /* synthetic */ RecyclerView.ViewHolder f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                SliceContextualCardRenderer.this.lambda$initDismissalActions$2$SliceContextualCardRenderer(this.f$1, this.f$2, view);
            }
        });
    }

    public /* synthetic */ void lambda$initDismissalActions$1$SliceContextualCardRenderer(RecyclerView.ViewHolder viewHolder, View view) {
        this.mFlippedCardSet.remove(viewHolder);
        lambda$onStop$3$SliceContextualCardRenderer(viewHolder);
    }

    public /* synthetic */ void lambda$initDismissalActions$2$SliceContextualCardRenderer(ContextualCard contextualCard, RecyclerView.ViewHolder viewHolder, View view) {
        this.mControllerRendererPool.getController(this.mContext, contextualCard.getCardType()).onDismissed(contextualCard);
        this.mFlippedCardSet.remove(viewHolder);
        lambda$onStop$3$SliceContextualCardRenderer(viewHolder);
        this.mSliceLiveDataMap.get(contextualCard.getSliceUri()).removeObservers(this.mLifecycleOwner);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        this.mFlippedCardSet.stream().forEach(new Consumer() {
            public final void accept(Object obj) {
                SliceContextualCardRenderer.this.lambda$onStop$3$SliceContextualCardRenderer((RecyclerView.ViewHolder) obj);
            }
        });
        this.mFlippedCardSet.clear();
    }

    /* access modifiers changed from: private */
    /* renamed from: resetCardView */
    public void lambda$onStop$3$SliceContextualCardRenderer(RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.findViewById(C1715R.C1718id.dismissal_view).setVisibility(8);
        getInitialView(viewHolder).setVisibility(0);
    }

    private void showDismissalView(RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.findViewById(C1715R.C1718id.dismissal_view).setVisibility(0);
        getInitialView(viewHolder).setVisibility(4);
    }

    private View getInitialView(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.getItemViewType() == C1715R.layout.contextual_slice_half_tile) {
            return ((SliceHalfCardRendererHelper.HalfCardViewHolder) viewHolder).content;
        }
        return ((SliceFullCardRendererHelper.SliceViewHolder) viewHolder).sliceView;
    }
}
