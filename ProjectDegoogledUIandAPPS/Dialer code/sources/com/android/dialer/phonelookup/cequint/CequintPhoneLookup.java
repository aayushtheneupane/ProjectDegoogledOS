package com.android.dialer.phonelookup.cequint;

import android.content.Context;
import android.support.p002v7.appcompat.R$style;
import android.telecom.Call;
import android.text.TextUtils;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.common.Assert;
import com.android.dialer.oem.CequintCallerIdManager;
import com.android.dialer.phonelookup.PhoneLookup;
import com.android.dialer.phonelookup.PhoneLookupInfo;
import com.android.dialer.phonenumberproto.DialerPhoneNumberUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.concurrent.Callable;

public class CequintPhoneLookup implements PhoneLookup<PhoneLookupInfo.CequintInfo> {
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutorService;
    private final ListeningExecutorService lightweightExecutorService;

    CequintPhoneLookup(Context context, ListeningExecutorService listeningExecutorService, ListeningExecutorService listeningExecutorService2) {
        this.appContext = context;
        this.backgroundExecutorService = listeningExecutorService;
        this.lightweightExecutorService = listeningExecutorService2;
    }

    private static PhoneLookupInfo.CequintInfo buildCequintInfo(CequintCallerIdManager.CequintCallerIdContact cequintCallerIdContact) {
        PhoneLookupInfo.CequintInfo.Builder newBuilder = PhoneLookupInfo.CequintInfo.newBuilder();
        if (!TextUtils.isEmpty(cequintCallerIdContact.name())) {
            newBuilder.setName(cequintCallerIdContact.name());
        }
        if (!TextUtils.isEmpty(cequintCallerIdContact.geolocation())) {
            newBuilder.setGeolocation(cequintCallerIdContact.geolocation());
        }
        if (!TextUtils.isEmpty(cequintCallerIdContact.photoUri())) {
            newBuilder.setPhotoUri(cequintCallerIdContact.photoUri());
        }
        return (PhoneLookupInfo.CequintInfo) newBuilder.build();
    }

    public ListenableFuture<Void> clearData() {
        return Futures.immediateFuture(null);
    }

    public String getLoggingName() {
        return "CequintPhoneLookup";
    }

    public ListenableFuture<ImmutableMap<DialerPhoneNumber, PhoneLookupInfo.CequintInfo>> getMostRecentInfo(ImmutableMap<DialerPhoneNumber, PhoneLookupInfo.CequintInfo> immutableMap) {
        return Futures.immediateFuture(immutableMap);
    }

    public Object getSubMessage(PhoneLookupInfo phoneLookupInfo) {
        return phoneLookupInfo.getCequintInfo();
    }

    public ListenableFuture<Boolean> isDirty(ImmutableSet<DialerPhoneNumber> immutableSet) {
        return Futures.immediateFuture(false);
    }

    public /* synthetic */ ListenableFuture lambda$lookup$2$CequintPhoneLookup(Context context, String str, boolean z, DialerPhoneNumber dialerPhoneNumber) throws Exception {
        return this.backgroundExecutorService.submit(new Callable(context, dialerPhoneNumber, str, z) {
            private final /* synthetic */ Context f$0;
            private final /* synthetic */ DialerPhoneNumber f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ boolean f$3;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final Object call() {
                return Assert.isNotNull(this.f$1);
            }
        });
    }

    public /* synthetic */ PhoneLookupInfo.CequintInfo lambda$lookup$3$CequintPhoneLookup(DialerPhoneNumber dialerPhoneNumber) throws Exception {
        return buildCequintInfo(CequintCallerIdManager.getCequintCallerIdContactForNumber(this.appContext, dialerPhoneNumber.getNormalizedNumber()));
    }

    public ListenableFuture<PhoneLookupInfo.CequintInfo> lookup(Context context, Call call) {
        if (!CequintCallerIdManager.isCequintCallerIdEnabled(context)) {
            return Futures.immediateFuture(PhoneLookupInfo.CequintInfo.getDefaultInstance());
        }
        return Futures.transformAsync(this.backgroundExecutorService.submit(new Callable(call, context) {
            private final /* synthetic */ Call f$0;
            private final /* synthetic */ Context f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final Object call() {
                return new DialerPhoneNumberUtil().parse(R$style.getNumber(this.f$0), R$style.getCurrentCountryIso(this.f$1));
            }
        }), new AsyncFunction(context, call.getDetails().getCallerDisplayName(), call.getState() == 2) {
            private final /* synthetic */ Context f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ boolean f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final ListenableFuture apply(Object obj) {
                return CequintPhoneLookup.this.lambda$lookup$2$CequintPhoneLookup(this.f$1, this.f$2, this.f$3, (DialerPhoneNumber) obj);
            }
        }, this.lightweightExecutorService);
    }

    public ListenableFuture<Void> onSuccessfulBulkUpdate() {
        return Futures.immediateFuture(null);
    }

    public void registerContentObservers() {
    }

    public void setSubMessage(PhoneLookupInfo.Builder builder, Object obj) {
        builder.setCequintInfo((PhoneLookupInfo.CequintInfo) obj);
    }

    public void unregisterContentObservers() {
    }

    public ListenableFuture<PhoneLookupInfo.CequintInfo> lookup(DialerPhoneNumber dialerPhoneNumber) {
        if (!CequintCallerIdManager.isCequintCallerIdEnabled(this.appContext)) {
            return Futures.immediateFuture(PhoneLookupInfo.CequintInfo.getDefaultInstance());
        }
        return this.backgroundExecutorService.submit(new Callable(dialerPhoneNumber) {
            private final /* synthetic */ DialerPhoneNumber f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return CequintPhoneLookup.this.lambda$lookup$3$CequintPhoneLookup(this.f$1);
            }
        });
    }
}
