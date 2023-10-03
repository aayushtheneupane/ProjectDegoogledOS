package com.android.dialer.precall;

import android.content.Context;
import android.content.Intent;
import com.android.dialer.R;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.inject.HasRootComponent;
import com.android.dialer.precall.impl.PreCallImpl;
import com.android.dialer.util.DialerUtils;

public interface PreCall {
    static Intent getIntent(Context context, CallIntentBuilder callIntentBuilder) {
        return ((PreCallImpl) ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).preCallActionsComponent().getPreCall()).buildIntent(context, callIntentBuilder);
    }

    static void start(Context context, CallIntentBuilder callIntentBuilder) {
        DialerUtils.startActivityWithErrorToast(context, getIntent(context, callIntentBuilder), R.string.activity_not_available);
    }
}
