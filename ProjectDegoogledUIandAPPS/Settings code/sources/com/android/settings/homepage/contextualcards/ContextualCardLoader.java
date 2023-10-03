package com.android.settings.homepage.contextualcards;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.android.settings.homepage.contextualcards.logging.ContextualCardLogUtils;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settingslib.utils.AsyncLoaderCompat;
import com.android.settingslib.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ContextualCardLoader extends AsyncLoaderCompat<List<ContextualCard>> {
    static final int DEFAULT_CARD_COUNT = 2;
    private final Context mContext;
    Uri mNotifyUri;
    private final ContentObserver mObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
        public void onChange(boolean z, Uri uri) {
            if (ContextualCardLoader.this.isStarted()) {
                ContextualCardLoader contextualCardLoader = ContextualCardLoader.this;
                contextualCardLoader.mNotifyUri = uri;
                contextualCardLoader.forceLoad();
            }
        }
    };

    public interface CardContentLoaderListener {
        void onFinishCardLoading(List<ContextualCard> list);
    }

    /* access modifiers changed from: protected */
    public void onDiscardResult(List<ContextualCard> list) {
    }

    ContextualCardLoader(Context context) {
        super(context);
        this.mContext = context.getApplicationContext();
    }

    /* access modifiers changed from: protected */
    public void onStartLoading() {
        super.onStartLoading();
        this.mNotifyUri = null;
        this.mContext.getContentResolver().registerContentObserver(CardContentProvider.REFRESH_CARD_URI, false, this.mObserver);
        this.mContext.getContentResolver().registerContentObserver(CardContentProvider.DELETE_CARD_URI, false, this.mObserver);
    }

    /* access modifiers changed from: protected */
    public void onStopLoading() {
        super.onStopLoading();
        this.mContext.getContentResolver().unregisterContentObserver(this.mObserver);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0064, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0065, code lost:
        if (r1 != null) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006c, code lost:
        r4.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006f, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.android.settings.homepage.contextualcards.ContextualCard> loadInBackground() {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.content.Context r1 = r4.mContext
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131034187(0x7f05004b, float:1.7678884E38)
            boolean r1 = r1.getBoolean(r2)
            if (r1 == 0) goto L_0x001c
            java.lang.String r4 = "ContextualCardLoader"
            java.lang.String r1 = "Skipping - in legacy suggestion mode"
            android.util.Log.d(r4, r1)
            return r0
        L_0x001c:
            android.database.Cursor r1 = r4.getContextualCardsFromProvider()
            int r2 = r1.getCount()     // Catch:{ all -> 0x0062 }
            if (r2 <= 0) goto L_0x0058
            r1.moveToFirst()     // Catch:{ all -> 0x0062 }
        L_0x0029:
            boolean r2 = r1.isAfterLast()     // Catch:{ all -> 0x0062 }
            if (r2 != 0) goto L_0x0058
            com.android.settings.homepage.contextualcards.ContextualCard r2 = new com.android.settings.homepage.contextualcards.ContextualCard     // Catch:{ all -> 0x0062 }
            r2.<init>((android.database.Cursor) r1)     // Catch:{ all -> 0x0062 }
            boolean r3 = r2.isCustomCard()     // Catch:{ all -> 0x0062 }
            if (r3 == 0) goto L_0x003b
            goto L_0x0054
        L_0x003b:
            boolean r3 = r4.isLargeCard(r2)     // Catch:{ all -> 0x0062 }
            if (r3 == 0) goto L_0x0051
            com.android.settings.homepage.contextualcards.ContextualCard$Builder r2 = r2.mutate()     // Catch:{ all -> 0x0062 }
            r3 = 1
            r2.setIsLargeCard(r3)     // Catch:{ all -> 0x0062 }
            com.android.settings.homepage.contextualcards.ContextualCard r2 = r2.build()     // Catch:{ all -> 0x0062 }
            r0.add(r2)     // Catch:{ all -> 0x0062 }
            goto L_0x0054
        L_0x0051:
            r0.add(r2)     // Catch:{ all -> 0x0062 }
        L_0x0054:
            r1.moveToNext()     // Catch:{ all -> 0x0062 }
            goto L_0x0029
        L_0x0058:
            if (r1 == 0) goto L_0x005d
            r1.close()
        L_0x005d:
            java.util.List r4 = r4.getDisplayableCards(r0)
            return r4
        L_0x0062:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0064 }
        L_0x0064:
            r0 = move-exception
            if (r1 == 0) goto L_0x006f
            r1.close()     // Catch:{ all -> 0x006b }
            goto L_0x006f
        L_0x006b:
            r1 = move-exception
            r4.addSuppressed(r1)
        L_0x006f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.homepage.contextualcards.ContextualCardLoader.loadInBackground():java.util.List");
    }

    /* access modifiers changed from: package-private */
    public List<ContextualCard> getDisplayableCards(List<ContextualCard> list) {
        List<ContextualCard> filterEligibleCards = filterEligibleCards(list);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int size = filterEligibleCards.size();
        for (int i = 0; i < size; i++) {
            if (i < 2) {
                arrayList.add(filterEligibleCards.get(i));
            } else {
                arrayList2.add(filterEligibleCards.get(i));
            }
        }
        if (!CardContentProvider.DELETE_CARD_URI.equals(this.mNotifyUri)) {
            FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider().action(this.mContext, 1664, ContextualCardLogUtils.buildCardListLog(arrayList2));
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public Cursor getContextualCardsFromProvider() {
        return CardDatabaseHelper.getInstance(this.mContext).getContextualCards();
    }

    /* access modifiers changed from: package-private */
    public List<ContextualCard> filterEligibleCards(List<ContextualCard> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList<Future> arrayList2 = new ArrayList<>();
        for (ContextualCard eligibleCardChecker : list) {
            arrayList2.add(ThreadUtils.postOnBackgroundThread((Callable) new EligibleCardChecker(this.mContext, eligibleCardChecker)));
        }
        for (Future future : arrayList2) {
            try {
                ContextualCard contextualCard = (ContextualCard) future.get(250, TimeUnit.MILLISECONDS);
                if (contextualCard != null) {
                    arrayList.add(contextualCard);
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                Log.w("ContextualCardLoader", "Failed to get eligible state for card, likely timeout. Skipping", e);
            }
        }
        return arrayList;
    }

    private boolean isLargeCard(ContextualCard contextualCard) {
        return contextualCard.getSliceUri().equals(CustomSliceRegistry.CONTEXTUAL_WIFI_SLICE_URI) || contextualCard.getSliceUri().equals(CustomSliceRegistry.BLUETOOTH_DEVICES_SLICE_URI) || contextualCard.getSliceUri().equals(CustomSliceRegistry.CONTEXTUAL_NOTIFICATION_CHANNEL_SLICE_URI);
    }
}
