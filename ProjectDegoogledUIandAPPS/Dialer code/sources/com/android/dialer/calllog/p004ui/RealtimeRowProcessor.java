package com.android.dialer.calllog.p004ui;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.support.p002v7.appcompat.R$style;
import android.util.ArrayMap;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.NumberAttributes;
import com.android.dialer.calllog.model.CoalescedRow;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.phonelookup.PhoneLookupInfo;
import com.android.dialer.phonelookup.composite.CompositePhoneLookup;
import com.android.dialer.phonelookup.database.contract.PhoneLookupHistoryContract;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* renamed from: com.android.dialer.calllog.ui.RealtimeRowProcessor */
public final class RealtimeRowProcessor {
    static final long BATCH_WAIT_MILLIS = TimeUnit.SECONDS.toMillis(3);
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutor;
    private final Map<DialerPhoneNumber, PhoneLookupInfo> cache = new ArrayMap();
    private final CompositePhoneLookup compositePhoneLookup;
    private final Map<DialerPhoneNumber, PhoneLookupInfo> queuedPhoneLookupHistoryWrites = new LinkedHashMap();
    private final ListeningExecutorService uiExecutor;
    private final Runnable writePhoneLookupHistoryRunnable = new Runnable() {
        public final void run() {
            RealtimeRowProcessor.this.writePhoneLookupHistory();
        }
    };

    RealtimeRowProcessor(Context context, ListeningExecutorService listeningExecutorService, ListeningExecutorService listeningExecutorService2, CompositePhoneLookup compositePhoneLookup2) {
        this.appContext = context;
        this.uiExecutor = listeningExecutorService;
        this.backgroundExecutor = listeningExecutorService2;
        this.compositePhoneLookup = compositePhoneLookup2;
    }

    private CoalescedRow applyPhoneLookupInfoToRow(PhoneLookupInfo phoneLookupInfo, CoalescedRow coalescedRow) {
        CoalescedRow.Builder builder = (CoalescedRow.Builder) coalescedRow.toBuilder();
        NumberAttributes.Builder fromPhoneLookupInfo = R$style.fromPhoneLookupInfo(phoneLookupInfo);
        fromPhoneLookupInfo.setIsCp2InfoIncomplete(coalescedRow.getNumberAttributes().getIsCp2InfoIncomplete());
        builder.setNumberAttributes((NumberAttributes) fromPhoneLookupInfo.build());
        return (CoalescedRow) builder.build();
    }

    /* access modifiers changed from: private */
    public void writePhoneLookupHistory() {
        Assert.isMainThread();
        ImmutableMap<DialerPhoneNumber, PhoneLookupInfo> copyOf = ImmutableMap.copyOf(this.queuedPhoneLookupHistoryWrites);
        this.queuedPhoneLookupHistoryWrites.clear();
        Futures.addCallback(this.backgroundExecutor.submit(new Callable(copyOf) {
            private final /* synthetic */ ImmutableMap f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return RealtimeRowProcessor.this.lambda$writePhoneLookupHistory$1$RealtimeRowProcessor(this.f$1);
            }
        }), new FutureCallback<Integer>(this) {
            public void onFailure(Throwable th) {
                throw new RuntimeException(th);
            }

            public void onSuccess(Object obj) {
                LogUtil.m9i("RealtimeRowProcessor.onSuccess", "wrote %d rows to PhoneLookupHistory", (Integer) obj);
            }
        }, this.uiExecutor);
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<CoalescedRow> applyRealtimeProcessing(CoalescedRow coalescedRow) {
        if (!coalescedRow.getNumberAttributes().getIsCp2InfoIncomplete()) {
            return Futures.immediateFuture(coalescedRow);
        }
        PhoneLookupInfo phoneLookupInfo = this.cache.get(coalescedRow.getNumber());
        if (phoneLookupInfo != null) {
            return Futures.immediateFuture(applyPhoneLookupInfoToRow(phoneLookupInfo, coalescedRow));
        }
        return Futures.transform(this.compositePhoneLookup.lookup(coalescedRow.getNumber()), new Function(coalescedRow) {
            private final /* synthetic */ CoalescedRow f$1;

            {
                this.f$1 = r2;
            }

            public final Object apply(Object obj) {
                return RealtimeRowProcessor.this.lambda$applyRealtimeProcessing$0$RealtimeRowProcessor(this.f$1, (PhoneLookupInfo) obj);
            }
        }, this.uiExecutor);
    }

    public void clearCache() {
        Assert.isMainThread();
        this.cache.clear();
    }

    public /* synthetic */ CoalescedRow lambda$applyRealtimeProcessing$0$RealtimeRowProcessor(CoalescedRow coalescedRow, PhoneLookupInfo phoneLookupInfo) {
        DialerPhoneNumber number = coalescedRow.getNumber();
        Assert.isMainThread();
        this.queuedPhoneLookupHistoryWrites.put(number, phoneLookupInfo);
        DialerExecutorModule.getUiThreadHandler().removeCallbacks(this.writePhoneLookupHistoryRunnable);
        DialerExecutorModule.getUiThreadHandler().postDelayed(this.writePhoneLookupHistoryRunnable, BATCH_WAIT_MILLIS);
        this.cache.put(coalescedRow.getNumber(), phoneLookupInfo);
        return applyPhoneLookupInfoToRow(phoneLookupInfo, coalescedRow);
    }

    public /* synthetic */ Integer lambda$writePhoneLookupHistory$1$RealtimeRowProcessor(ImmutableMap immutableMap) throws Exception {
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        UnmodifiableIterator it = immutableMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String normalizedNumber = ((DialerPhoneNumber) entry.getKey()).getNormalizedNumber();
            ContentValues contentValues = new ContentValues();
            contentValues.put("phone_lookup_info", ((PhoneLookupInfo) entry.getValue()).toByteArray());
            contentValues.put("last_modified", Long.valueOf(currentTimeMillis));
            arrayList.add(ContentProviderOperation.newUpdate(PhoneLookupHistoryContract.PhoneLookupHistory.contentUriForNumber(normalizedNumber)).withValues(contentValues).build());
        }
        ContentProviderResult[] applyBatch = this.appContext.getContentResolver().applyBatch(PhoneLookupHistoryContract.AUTHORITY, arrayList);
        Assert.isNotNull(applyBatch);
        return Integer.valueOf(applyBatch.length);
    }
}
