package com.android.dialer.phonelookup.composite;

import android.content.Context;
import android.telecom.Call;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.calllog.CallLogState;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.metrics.FutureTimer;
import com.android.dialer.phonelookup.PhoneLookup;
import com.android.dialer.phonelookup.PhoneLookupInfo;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps$EntryTransformer;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.List;

public final class CompositePhoneLookup {
    private final Context appContext;
    private final CallLogState callLogState;
    private final FutureTimer futureTimer;
    private final ListeningExecutorService lightweightExecutorService;
    private final ImmutableList<PhoneLookup> phoneLookups;

    public CompositePhoneLookup(Context context, ImmutableList<PhoneLookup> immutableList, FutureTimer futureTimer2, CallLogState callLogState2, ListeningExecutorService listeningExecutorService) {
        this.appContext = context;
        this.phoneLookups = immutableList;
        this.futureTimer = futureTimer2;
        this.callLogState = callLogState2;
        this.lightweightExecutorService = listeningExecutorService;
    }

    private static String getMostRecentInfoEventName(String str, boolean z) {
        return String.format(!z ? "%s.Initial.GetMostRecentInfo" : "%s.GetMostRecentInfo", new Object[]{str});
    }

    private static String onSuccessfulBulkUpdatedEventName(String str, boolean z) {
        return String.format(!z ? "%s.Initial.OnSuccessfulBulkUpdate" : "%s.OnSuccessfulBulkUpdate", new Object[]{str});
    }

    public ListenableFuture<Void> clearData() {
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator<PhoneLookup> it = this.phoneLookups.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().clearData());
        }
        return Futures.transform(Futures.allAsList(arrayList), $$Lambda$CompositePhoneLookup$knleE2OmDB6uczjANdSSGSsuod4.INSTANCE, this.lightweightExecutorService);
    }

    public ListenableFuture<ImmutableMap<DialerPhoneNumber, PhoneLookupInfo>> getMostRecentInfo(ImmutableMap<DialerPhoneNumber, PhoneLookupInfo> immutableMap) {
        return Futures.transformAsync(this.callLogState.isBuilt(), new AsyncFunction(immutableMap) {
            private final /* synthetic */ ImmutableMap f$1;

            {
                this.f$1 = r2;
            }

            public final ListenableFuture apply(Object obj) {
                return CompositePhoneLookup.this.lambda$getMostRecentInfo$2$CompositePhoneLookup(this.f$1, (Boolean) obj);
            }
        }, MoreExecutors.directExecutor());
    }

    public ListenableFuture<Boolean> isDirty(ImmutableSet<DialerPhoneNumber> immutableSet) {
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator<PhoneLookup> it = this.phoneLookups.iterator();
        while (it.hasNext()) {
            PhoneLookup next = it.next();
            ListenableFuture<Boolean> isDirty = next.isDirty(immutableSet);
            arrayList.add(isDirty);
            this.futureTimer.applyTiming(isDirty, String.format("%s.IsDirty", new Object[]{next.getLoggingName()}), 2);
        }
        ListenableFuture<Boolean> firstMatching = DialerExecutorModule.firstMatching(arrayList, $$Lambda$VbxFCRFe5CwNTJmvYo_2CX5U7Sg.INSTANCE, false);
        this.futureTimer.applyTiming(firstMatching, String.format("%s.IsDirty", new Object[]{"CompositePhoneLookup"}), 2);
        return firstMatching;
    }

    public /* synthetic */ PhoneLookupInfo lambda$combineSubMessageFutures$0$CompositePhoneLookup(List list) {
        if (list != null) {
            PhoneLookupInfo.Builder newBuilder = PhoneLookupInfo.newBuilder();
            for (int i = 0; i < list.size(); i++) {
                this.phoneLookups.get(i).setSubMessage(newBuilder, list.get(i));
            }
            return (PhoneLookupInfo) newBuilder.build();
        }
        throw new NullPointerException();
    }

    public /* synthetic */ ImmutableMap lambda$getMostRecentInfo$1$CompositePhoneLookup(ImmutableMap immutableMap, List list) {
        if (list != null) {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            UnmodifiableIterator it = immutableMap.keySet().iterator();
            while (it.hasNext()) {
                DialerPhoneNumber dialerPhoneNumber = (DialerPhoneNumber) it.next();
                PhoneLookupInfo.Builder newBuilder = PhoneLookupInfo.newBuilder();
                int i = 0;
                while (i < list.size()) {
                    Object obj = ((ImmutableMap) list.get(i)).get(dialerPhoneNumber);
                    if (obj != null) {
                        this.phoneLookups.get(i).setSubMessage(newBuilder, obj);
                        i++;
                    } else {
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13("A sublookup didn't return an info for number: ");
                        outline13.append(LogUtil.sanitizePhoneNumber(dialerPhoneNumber.getNormalizedNumber()));
                        throw new IllegalStateException(outline13.toString());
                    }
                }
                builder.put(dialerPhoneNumber, (PhoneLookupInfo) newBuilder.build());
            }
            return builder.build();
        }
        throw new NullPointerException();
    }

    public /* synthetic */ ListenableFuture lambda$getMostRecentInfo$2$CompositePhoneLookup(ImmutableMap immutableMap, Boolean bool) throws Exception {
        if (bool != null) {
            ArrayList arrayList = new ArrayList();
            UnmodifiableIterator<PhoneLookup> it = this.phoneLookups.iterator();
            while (it.hasNext()) {
                PhoneLookup next = it.next();
                boolean booleanValue = bool.booleanValue();
                ListenableFuture mostRecentInfo = next.getMostRecentInfo(ImmutableMap.copyOf(Collections2.transformEntries(immutableMap, new Maps$EntryTransformer(immutableMap) {
                    private final /* synthetic */ ImmutableMap f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final Object transformEntry(Object obj, Object obj2) {
                        return PhoneLookup.this.getSubMessage((PhoneLookupInfo) this.f$1.get((DialerPhoneNumber) obj));
                    }
                })));
                this.futureTimer.applyTiming(mostRecentInfo, getMostRecentInfoEventName(next.getLoggingName(), booleanValue));
                arrayList.add(mostRecentInfo);
            }
            ListenableFuture transform = Futures.transform(Futures.allAsList(arrayList), new Function(immutableMap) {
                private final /* synthetic */ ImmutableMap f$1;

                {
                    this.f$1 = r2;
                }

                public final Object apply(Object obj) {
                    return CompositePhoneLookup.this.lambda$getMostRecentInfo$1$CompositePhoneLookup(this.f$1, (List) obj);
                }
            }, this.lightweightExecutorService);
            this.futureTimer.applyTiming(transform, getMostRecentInfoEventName("CompositePhoneLookup", bool.booleanValue()));
            return transform;
        }
        throw new NullPointerException();
    }

    public /* synthetic */ ListenableFuture lambda$onSuccessfulBulkUpdate$5$CompositePhoneLookup(Boolean bool) throws Exception {
        if (bool != null) {
            ArrayList arrayList = new ArrayList();
            UnmodifiableIterator<PhoneLookup> it = this.phoneLookups.iterator();
            while (it.hasNext()) {
                PhoneLookup next = it.next();
                ListenableFuture<Void> onSuccessfulBulkUpdate = next.onSuccessfulBulkUpdate();
                arrayList.add(onSuccessfulBulkUpdate);
                this.futureTimer.applyTiming(onSuccessfulBulkUpdate, onSuccessfulBulkUpdatedEventName(next.getLoggingName(), bool.booleanValue()));
            }
            ListenableFuture transform = Futures.transform(Futures.allAsList(arrayList), $$Lambda$CompositePhoneLookup$c_WPZaOkaBhWfCKyIwwghygwqOw.INSTANCE, this.lightweightExecutorService);
            this.futureTimer.applyTiming(transform, onSuccessfulBulkUpdatedEventName("CompositePhoneLookup", bool.booleanValue()));
            return transform;
        }
        throw new NullPointerException();
    }

    public ListenableFuture<PhoneLookupInfo> lookup(Call call) {
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator<PhoneLookup> it = this.phoneLookups.iterator();
        while (it.hasNext()) {
            PhoneLookup next = it.next();
            ListenableFuture lookup = next.lookup(this.appContext, call);
            this.futureTimer.applyTiming(lookup, String.format("%s.LookupForCall", new Object[]{next.getLoggingName()}));
            arrayList.add(lookup);
        }
        ListenableFuture<PhoneLookupInfo> transform = Futures.transform(Futures.allAsList(arrayList), new Function() {
            public final Object apply(Object obj) {
                return CompositePhoneLookup.this.lambda$combineSubMessageFutures$0$CompositePhoneLookup((List) obj);
            }
        }, this.lightweightExecutorService);
        this.futureTimer.applyTiming(transform, String.format("%s.LookupForCall", new Object[]{"CompositePhoneLookup"}));
        return transform;
    }

    public ListenableFuture<Void> onSuccessfulBulkUpdate() {
        return Futures.transformAsync(this.callLogState.isBuilt(), new AsyncFunction() {
            public final ListenableFuture apply(Object obj) {
                return CompositePhoneLookup.this.lambda$onSuccessfulBulkUpdate$5$CompositePhoneLookup((Boolean) obj);
            }
        }, MoreExecutors.directExecutor());
    }

    public void registerContentObservers() {
        UnmodifiableIterator<PhoneLookup> it = this.phoneLookups.iterator();
        while (it.hasNext()) {
            it.next().registerContentObservers();
        }
    }

    public void unregisterContentObservers() {
        UnmodifiableIterator<PhoneLookup> it = this.phoneLookups.iterator();
        while (it.hasNext()) {
            it.next().unregisterContentObservers();
        }
    }

    public ListenableFuture<PhoneLookupInfo> lookup(DialerPhoneNumber dialerPhoneNumber) {
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator<PhoneLookup> it = this.phoneLookups.iterator();
        while (it.hasNext()) {
            PhoneLookup next = it.next();
            ListenableFuture lookup = next.lookup(dialerPhoneNumber);
            this.futureTimer.applyTiming(lookup, String.format("%s.LookupForNumber", new Object[]{next.getLoggingName()}));
            arrayList.add(lookup);
        }
        ListenableFuture<PhoneLookupInfo> transform = Futures.transform(Futures.allAsList(arrayList), new Function() {
            public final Object apply(Object obj) {
                return CompositePhoneLookup.this.lambda$combineSubMessageFutures$0$CompositePhoneLookup((List) obj);
            }
        }, this.lightweightExecutorService);
        this.futureTimer.applyTiming(transform, String.format("%s.LookupForNumber", new Object[]{"CompositePhoneLookup"}));
        return transform;
    }
}
