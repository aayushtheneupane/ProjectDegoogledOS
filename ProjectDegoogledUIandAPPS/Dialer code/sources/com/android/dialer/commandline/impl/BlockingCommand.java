package com.android.dialer.commandline.impl;

import android.content.Context;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.commandline.Arguments;
import com.android.dialer.commandline.Command;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.inject.HasRootComponent;
import com.android.dialer.phonelookup.PhoneLookupInfo;
import com.android.dialer.phonelookup.consolidator.PhoneLookupInfoConsolidator;
import com.android.dialer.phonenumberproto.DialerPhoneNumberUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableCollection;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;

public class BlockingCommand implements Command {
    private final Context appContext;
    private final ListeningExecutorService executorService;

    BlockingCommand(Context context, ListeningExecutorService listeningExecutorService) {
        this.appContext = context;
        this.executorService = listeningExecutorService;
    }

    static /* synthetic */ String lambda$run$4(PhoneLookupInfo phoneLookupInfo) {
        return new PhoneLookupInfoConsolidator(phoneLookupInfo).isBlocked() ? "true" : "false";
    }

    public String getShortDescription() {
        return "block or unblock numbers";
    }

    public String getUsage() {
        return "blocking block|unblock|isblocked number\n\nnumber should be e.164 formatted";
    }

    public /* synthetic */ ListenableFuture lambda$run$3$BlockingCommand(DialerPhoneNumber dialerPhoneNumber) throws Exception {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) this.appContext.getApplicationContext()).component()).phoneLookupComponent().compositePhoneLookup().lookup(dialerPhoneNumber);
    }

    public ListenableFuture<String> run(Arguments arguments) throws Command.IllegalCommandLineArgumentException {
        if (arguments.getPositionals().isEmpty()) {
            return Futures.immediateFuture("blocking block|unblock|isblocked number\n\nnumber should be e.164 formatted");
        }
        String str = arguments.getPositionals().get(0);
        if ("block".equals(str)) {
            String str2 = arguments.getPositionals().get(1);
            Context context = this.appContext;
            return Futures.transform(DialerExecutorComponent.get(context).backgroundExecutor().submit(new Callable((String) null, context) {
                private final /* synthetic */ String f$1;
                private final /* synthetic */ Context f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final Object call() {
                    Blocking.lambda$block$0(ImmutableCollection.this, this.f$1, this.f$2);
                    return null;
                }
            }), new Function(str2) {
                private final /* synthetic */ String f$0;

                {
                    this.f$0 = r1;
                }

                public final Object apply(Object obj) {
                    return GeneratedOutlineSupport.outline8("blocked ", this.f$0);
                }
            }, MoreExecutors.directExecutor());
        } else if ("unblock".equals(str)) {
            String str3 = arguments.getPositionals().get(1);
            Context context2 = this.appContext;
            return Futures.transform(DialerExecutorComponent.get(context2).backgroundExecutor().submit(new Callable((String) null, context2) {
                private final /* synthetic */ String f$1;
                private final /* synthetic */ Context f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final Object call() {
                    Blocking.lambda$unblock$1(ImmutableCollection.this, this.f$1, this.f$2);
                    return null;
                }
            }), new Function(str3) {
                private final /* synthetic */ String f$0;

                {
                    this.f$0 = r1;
                }

                public final Object apply(Object obj) {
                    return GeneratedOutlineSupport.outline8("unblocked ", this.f$0);
                }
            }, MoreExecutors.directExecutor());
        } else if ("isblocked".equals(str)) {
            return Futures.transform(Futures.transformAsync(this.executorService.submit(new Callable(arguments.getPositionals().get(1)) {
                private final /* synthetic */ String f$0;

                {
                    this.f$0 = r1;
                }

                public final Object call() {
                    return new DialerPhoneNumberUtil().parse(this.f$0, (String) null);
                }
            }), new AsyncFunction() {
                public final ListenableFuture apply(Object obj) {
                    return BlockingCommand.this.lambda$run$3$BlockingCommand((DialerPhoneNumber) obj);
                }
            }, this.executorService), $$Lambda$BlockingCommand$eaOMFat0nWhwfEY2ete1ZEUBogM.INSTANCE, MoreExecutors.directExecutor());
        } else {
            return Futures.immediateFuture("blocking block|unblock|isblocked number\n\nnumber should be e.164 formatted");
        }
    }
}
