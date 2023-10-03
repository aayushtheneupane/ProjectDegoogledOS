package com.android.dialer.phonelookup;

import android.content.Context;
import android.support.p002v7.appcompat.R$style;
import android.telecom.Call;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.phonelookup.PhoneLookupInfo;
import com.android.dialer.phonenumberproto.DialerPhoneNumberUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;

public interface PhoneLookup<T> {
    ListenableFuture<Void> clearData();

    String getLoggingName();

    ListenableFuture<ImmutableMap<DialerPhoneNumber, T>> getMostRecentInfo(ImmutableMap<DialerPhoneNumber, T> immutableMap);

    T getSubMessage(PhoneLookupInfo phoneLookupInfo);

    ListenableFuture<Boolean> isDirty(ImmutableSet<DialerPhoneNumber> immutableSet);

    ListenableFuture<T> lookup(Context context, Call call) {
        return Futures.transformAsync(DialerExecutorComponent.get(context).backgroundExecutor().submit(new Callable(call, context) {
            private final /* synthetic */ Call f$0;
            private final /* synthetic */ Context f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final Object call() {
                return new DialerPhoneNumberUtil().parse(R$style.getNumber(this.f$0), R$style.getCurrentCountryIso(this.f$1));
            }
        }), new AsyncFunction() {
            public final ListenableFuture apply(Object obj) {
                return PhoneLookup.this.lookup((DialerPhoneNumber) obj);
            }
        }, MoreExecutors.directExecutor());
    }

    ListenableFuture<T> lookup(DialerPhoneNumber dialerPhoneNumber);

    ListenableFuture<Void> onSuccessfulBulkUpdate();

    void registerContentObservers();

    void setSubMessage(PhoneLookupInfo.Builder builder, T t);

    void unregisterContentObservers();
}
