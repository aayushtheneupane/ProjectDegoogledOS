package com.android.dialer.phonelookup.spam;

import android.content.SharedPreferences;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.common.Assert;
import com.android.dialer.phonelookup.PhoneLookup;
import com.android.dialer.phonelookup.PhoneLookupInfo;
import com.android.dialer.spam.Spam;
import com.android.dialer.spam.status.SpamStatus;
import com.android.dialer.spam.stub.SpamStub;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;

public final class SpamPhoneLookup implements PhoneLookup<PhoneLookupInfo.SpamInfo> {
    static final String PREF_LAST_TIMESTAMP_PROCESSED = "spamPhoneLookupLastTimestampProcessed";
    private final ListeningExecutorService backgroundExecutorService;
    private Long currentLastTimestampProcessed;
    private final ListeningExecutorService lightweightExecutorService;
    private final SharedPreferences sharedPreferences;
    private final Spam spam;

    SpamPhoneLookup(ListeningExecutorService listeningExecutorService, ListeningExecutorService listeningExecutorService2, SharedPreferences sharedPreferences2, Spam spam2) {
        this.backgroundExecutorService = listeningExecutorService;
        this.lightweightExecutorService = listeningExecutorService2;
        this.sharedPreferences = sharedPreferences2;
        this.spam = spam2;
    }

    static /* synthetic */ PhoneLookupInfo.SpamInfo lambda$lookup$0(DialerPhoneNumber dialerPhoneNumber, ImmutableMap immutableMap) {
        PhoneLookupInfo.SpamInfo.Builder newBuilder = PhoneLookupInfo.SpamInfo.newBuilder();
        SpamStatus spamStatus = (SpamStatus) immutableMap.get(dialerPhoneNumber);
        Assert.isNotNull(spamStatus);
        newBuilder.setIsSpam(spamStatus.isSpam());
        return (PhoneLookupInfo.SpamInfo) newBuilder.build();
    }

    public ListenableFuture<Void> clearData() {
        return this.backgroundExecutorService.submit(new Callable() {
            public final Object call() {
                return SpamPhoneLookup.this.lambda$clearData$4$SpamPhoneLookup();
            }
        });
    }

    public String getLoggingName() {
        return "SpamPhoneLookup";
    }

    public ListenableFuture<ImmutableMap<DialerPhoneNumber, PhoneLookupInfo.SpamInfo>> getMostRecentInfo(ImmutableMap<DialerPhoneNumber, PhoneLookupInfo.SpamInfo> immutableMap) {
        this.currentLastTimestampProcessed = null;
        return Futures.transform(((SpamStub) this.spam).batchCheckSpamStatus(immutableMap.keySet()), new Function() {
            public final Object apply(Object obj) {
                return SpamPhoneLookup.this.lambda$getMostRecentInfo$2$SpamPhoneLookup((ImmutableMap) obj);
            }
        }, this.lightweightExecutorService);
    }

    public Object getSubMessage(PhoneLookupInfo phoneLookupInfo) {
        return phoneLookupInfo.getSpamInfo();
    }

    public ListenableFuture<Boolean> isDirty(ImmutableSet<DialerPhoneNumber> immutableSet) {
        ListenableFuture submit = this.backgroundExecutorService.submit(new Callable() {
            public final Object call() {
                return SpamPhoneLookup.this.lambda$isDirty$1$SpamPhoneLookup();
            }
        });
        Spam spam2 = this.spam;
        Objects.requireNonNull(spam2);
        return Futures.transformAsync(submit, new AsyncFunction() {
            public final ListenableFuture apply(Object obj) {
                return ((SpamStub) Spam.this).dataUpdatedSince(((Long) obj).longValue());
            }
        }, this.lightweightExecutorService);
    }

    public /* synthetic */ Void lambda$clearData$4$SpamPhoneLookup() throws Exception {
        this.sharedPreferences.edit().remove(PREF_LAST_TIMESTAMP_PROCESSED).apply();
        return null;
    }

    public /* synthetic */ ImmutableMap lambda$getMostRecentInfo$2$SpamPhoneLookup(ImmutableMap immutableMap) {
        long j;
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        UnmodifiableIterator it = immutableMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            SpamStatus spamStatus = (SpamStatus) entry.getValue();
            PhoneLookupInfo.SpamInfo.Builder newBuilder = PhoneLookupInfo.SpamInfo.newBuilder();
            newBuilder.setIsSpam(spamStatus.isSpam());
            builder.put((DialerPhoneNumber) entry.getKey(), (PhoneLookupInfo.SpamInfo) newBuilder.build());
            Optional<Long> timestampMillis = spamStatus.getTimestampMillis();
            if (timestampMillis.isPresent()) {
                if (this.currentLastTimestampProcessed == null) {
                    j = timestampMillis.get().longValue();
                } else {
                    j = Math.max(timestampMillis.get().longValue(), this.currentLastTimestampProcessed.longValue());
                }
                this.currentLastTimestampProcessed = Long.valueOf(j);
            }
        }
        if (this.currentLastTimestampProcessed == null) {
            this.currentLastTimestampProcessed = Long.valueOf(System.currentTimeMillis());
        }
        return builder.build();
    }

    public /* synthetic */ Long lambda$isDirty$1$SpamPhoneLookup() throws Exception {
        return Long.valueOf(this.sharedPreferences.getLong(PREF_LAST_TIMESTAMP_PROCESSED, 0));
    }

    public /* synthetic */ Void lambda$onSuccessfulBulkUpdate$3$SpamPhoneLookup() throws Exception {
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        Long l = this.currentLastTimestampProcessed;
        Assert.isNotNull(l);
        edit.putLong(PREF_LAST_TIMESTAMP_PROCESSED, l.longValue()).apply();
        return null;
    }

    public ListenableFuture<PhoneLookupInfo.SpamInfo> lookup(DialerPhoneNumber dialerPhoneNumber) {
        return Futures.transform(((SpamStub) this.spam).batchCheckSpamStatus(ImmutableSet.m87of(dialerPhoneNumber)), new Function() {
            public final Object apply(Object obj) {
                return SpamPhoneLookup.lambda$lookup$0(DialerPhoneNumber.this, (ImmutableMap) obj);
            }
        }, this.lightweightExecutorService);
    }

    public ListenableFuture<Void> onSuccessfulBulkUpdate() {
        return this.backgroundExecutorService.submit(new Callable() {
            public final Object call() {
                return SpamPhoneLookup.this.lambda$onSuccessfulBulkUpdate$3$SpamPhoneLookup();
            }
        });
    }

    public void registerContentObservers() {
    }

    public void setSubMessage(PhoneLookupInfo.Builder builder, Object obj) {
        builder.setSpamInfo((PhoneLookupInfo.SpamInfo) obj);
    }

    public void unregisterContentObservers() {
    }
}
