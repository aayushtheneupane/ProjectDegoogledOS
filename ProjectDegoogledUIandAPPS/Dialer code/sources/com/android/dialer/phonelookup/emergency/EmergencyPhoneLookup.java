package com.android.dialer.phonelookup.emergency;

import android.content.Context;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.phonelookup.PhoneLookup;
import com.android.dialer.phonelookup.PhoneLookupInfo;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.concurrent.Callable;

public class EmergencyPhoneLookup implements PhoneLookup<PhoneLookupInfo.EmergencyInfo> {
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutorService;

    EmergencyPhoneLookup(Context context, ListeningExecutorService listeningExecutorService) {
        this.appContext = context;
        this.backgroundExecutorService = listeningExecutorService;
    }

    public ListenableFuture<Void> clearData() {
        return Futures.immediateFuture(null);
    }

    public String getLoggingName() {
        return "EmergencyPhoneLookup";
    }

    public ListenableFuture<ImmutableMap<DialerPhoneNumber, PhoneLookupInfo.EmergencyInfo>> getMostRecentInfo(ImmutableMap<DialerPhoneNumber, PhoneLookupInfo.EmergencyInfo> immutableMap) {
        return Futures.immediateFuture(immutableMap);
    }

    public Object getSubMessage(PhoneLookupInfo phoneLookupInfo) {
        return phoneLookupInfo.getEmergencyInfo();
    }

    public ListenableFuture<Boolean> isDirty(ImmutableSet<DialerPhoneNumber> immutableSet) {
        return Futures.immediateFuture(false);
    }

    public /* synthetic */ PhoneLookupInfo.EmergencyInfo lambda$lookup$0$EmergencyPhoneLookup(DialerPhoneNumber dialerPhoneNumber) throws Exception {
        PhoneLookupInfo.EmergencyInfo.Builder newBuilder = PhoneLookupInfo.EmergencyInfo.newBuilder();
        newBuilder.setIsEmergencyNumber(PhoneNumberHelper.isLocalEmergencyNumber(this.appContext, dialerPhoneNumber.getNormalizedNumber()));
        return (PhoneLookupInfo.EmergencyInfo) newBuilder.build();
    }

    public ListenableFuture<PhoneLookupInfo.EmergencyInfo> lookup(DialerPhoneNumber dialerPhoneNumber) {
        return this.backgroundExecutorService.submit(new Callable(dialerPhoneNumber) {
            private final /* synthetic */ DialerPhoneNumber f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return EmergencyPhoneLookup.this.lambda$lookup$0$EmergencyPhoneLookup(this.f$1);
            }
        });
    }

    public ListenableFuture<Void> onSuccessfulBulkUpdate() {
        return Futures.immediateFuture(null);
    }

    public void registerContentObservers() {
    }

    public void setSubMessage(PhoneLookupInfo.Builder builder, Object obj) {
        builder.setEmergencyInfo((PhoneLookupInfo.EmergencyInfo) obj);
    }

    public void unregisterContentObservers() {
    }
}
