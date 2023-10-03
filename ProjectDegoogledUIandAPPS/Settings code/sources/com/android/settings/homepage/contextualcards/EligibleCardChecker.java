package com.android.settings.homepage.contextualcards;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import androidx.slice.Slice;
import androidx.slice.SliceMetadata;
import androidx.slice.SliceViewManager;
import com.android.settings.homepage.contextualcards.ContextualCard;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class EligibleCardChecker implements Callable<ContextualCard> {
    ContextualCard mCard;
    private final Context mContext;

    EligibleCardChecker(Context context, ContextualCard contextualCard) {
        this.mContext = context;
        this.mCard = contextualCard;
    }

    public ContextualCard call() throws Exception {
        ContextualCard contextualCard;
        long currentTimeMillis = System.currentTimeMillis();
        MetricsFeatureProvider metricsFeatureProvider = FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider();
        if (isCardEligibleToDisplay(this.mCard)) {
            metricsFeatureProvider.action(0, 1686, 1502, this.mCard.getTextSliceUri(), 1);
            contextualCard = this.mCard;
        } else {
            metricsFeatureProvider.action(0, 1686, 1502, this.mCard.getTextSliceUri(), 0);
            contextualCard = null;
        }
        ContextualCard contextualCard2 = contextualCard;
        metricsFeatureProvider.action(0, 1684, 1502, this.mCard.getTextSliceUri(), (int) (System.currentTimeMillis() - currentTimeMillis));
        return contextualCard2;
    }

    /* access modifiers changed from: package-private */
    public boolean isCardEligibleToDisplay(ContextualCard contextualCard) {
        if (contextualCard.getRankingScore() < 0.0d) {
            return false;
        }
        if (contextualCard.isCustomCard()) {
            return true;
        }
        Uri sliceUri = contextualCard.getSliceUri();
        if (!"content".equals(sliceUri.getScheme())) {
            return false;
        }
        Slice bindSlice = bindSlice(sliceUri);
        if (isSliceToggleable(bindSlice)) {
            ContextualCard.Builder mutate = contextualCard.mutate();
            mutate.setHasInlineAction(true);
            this.mCard = mutate.build();
        }
        if (bindSlice != null && !bindSlice.hasHint("error")) {
            return true;
        }
        Log.w("EligibleCardChecker", "Failed to bind slice, not eligible for display " + sliceUri);
        return false;
    }

    /* access modifiers changed from: package-private */
    public Slice bindSlice(Uri uri) {
        SliceViewManager instance = SliceViewManager.getInstance(this.mContext);
        Slice[] sliceArr = new Slice[1];
        CountDownLatch countDownLatch = new CountDownLatch(1);
        final Slice[] sliceArr2 = sliceArr;
        final CountDownLatch countDownLatch2 = countDownLatch;
        final Uri uri2 = uri;
        final SliceViewManager sliceViewManager = instance;
        C08751 r1 = new SliceViewManager.SliceCallback() {
            public void onSliceUpdated(Slice slice) {
                try {
                    sliceArr2[0] = slice;
                    countDownLatch2.countDown();
                } catch (Exception e) {
                    Log.w("EligibleCardChecker", uri2 + " cannot be indexed", e);
                } catch (Throwable th) {
                    sliceViewManager.unregisterSliceCallback(uri2, this);
                    throw th;
                }
                sliceViewManager.unregisterSliceCallback(uri2, this);
            }
        };
        instance.registerSliceCallback(uri, r1);
        r1.onSliceUpdated(instance.bindSlice(uri));
        try {
            countDownLatch.await(200, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Log.w("EligibleCardChecker", "Error waiting for slice binding for uri" + uri, e);
            instance.unregisterSliceCallback(uri, r1);
        }
        return sliceArr[0];
    }

    /* access modifiers changed from: package-private */
    public boolean isSliceToggleable(Slice slice) {
        return !SliceMetadata.from(this.mContext, slice).getToggles().isEmpty();
    }
}
