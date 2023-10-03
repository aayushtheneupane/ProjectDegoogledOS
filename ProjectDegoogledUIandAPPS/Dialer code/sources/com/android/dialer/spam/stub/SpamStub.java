package com.android.dialer.spam.stub;

import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.logging.ContactLookupResult$Type;
import com.android.dialer.logging.ReportingLocation$Type;
import com.android.dialer.spam.Spam;
import com.android.dialer.spam.status.SimpleSpamStatus;
import com.android.dialer.spam.status.SpamStatus;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.concurrent.Callable;

public class SpamStub implements Spam {
    private final ListeningExecutorService backgroundExecutorService;

    public SpamStub(ListeningExecutorService listeningExecutorService) {
        this.backgroundExecutorService = listeningExecutorService;
    }

    static /* synthetic */ ImmutableMap lambda$batchCheckSpamStatus$0(ImmutableSet immutableSet) throws Exception {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        UnmodifiableIterator it = immutableSet.iterator();
        while (it.hasNext()) {
            builder.put((DialerPhoneNumber) it.next(), SimpleSpamStatus.notSpam());
        }
        return builder.build();
    }

    public ListenableFuture<ImmutableMap<DialerPhoneNumber, SpamStatus>> batchCheckSpamStatus(ImmutableSet<DialerPhoneNumber> immutableSet) {
        return this.backgroundExecutorService.submit(new Callable() {
            public final Object call() {
                return SpamStub.lambda$batchCheckSpamStatus$0(ImmutableSet.this);
            }
        });
    }

    public boolean checkSpamStatusSynchronous(String str, String str2) {
        return false;
    }

    public ListenableFuture<Boolean> dataUpdatedSince(long j) {
        return Futures.immediateFuture(false);
    }

    public void reportNotSpamFromAfterCallNotification(String str, String str2, int i, ReportingLocation$Type reportingLocation$Type, ContactLookupResult$Type contactLookupResult$Type) {
    }

    public void reportSpamFromAfterCallNotification(String str, String str2, int i, ReportingLocation$Type reportingLocation$Type, ContactLookupResult$Type contactLookupResult$Type) {
    }
}
