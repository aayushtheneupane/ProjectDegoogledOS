package com.android.dialer.precall.impl;

import android.content.Context;
import android.content.Intent;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.precall.PreCall;
import com.android.dialer.precall.PreCallAction;
import com.android.dialer.precall.PreCallCoordinator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

public class PreCallImpl implements PreCall {
    private final ImmutableList<PreCallAction> actions;

    PreCallImpl(ImmutableList<PreCallAction> immutableList) {
        this.actions = immutableList;
    }

    public Intent buildIntent(Context context, CallIntentBuilder callIntentBuilder) {
        boolean z;
        ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.PRECALL_INITIATED);
        UnmodifiableIterator<PreCallAction> it = this.actions.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            PreCallAction next = it.next();
            if (next.requiresUi(context, callIntentBuilder)) {
                LogUtil.m9i("PreCallImpl.requiresUi", next + " requested UI", new Object[0]);
                z = true;
                break;
            }
        }
        if (!z) {
            LogUtil.m9i("PreCallImpl.buildIntent", "No UI requested, running pre-call directly", new Object[0]);
            UnmodifiableIterator<PreCallAction> it2 = this.actions.iterator();
            while (it2.hasNext()) {
                it2.next().runWithoutUi(context, callIntentBuilder);
            }
            return callIntentBuilder.build();
        }
        LogUtil.m9i("PreCallImpl.buildIntent", "building intent to start activity", new Object[0]);
        Intent intent = new Intent(context, PreCallActivity.class);
        intent.putExtra(PreCallCoordinator.EXTRA_CALL_INTENT_BUILDER, callIntentBuilder);
        return intent;
    }
}
