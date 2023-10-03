package com.android.dialer.precall;

import android.content.Context;
import com.android.dialer.callintent.CallIntentBuilder;

public interface PreCallAction {
    void onDiscard();

    boolean requiresUi(Context context, CallIntentBuilder callIntentBuilder);

    void runWithUi(PreCallCoordinator preCallCoordinator);

    void runWithoutUi(Context context, CallIntentBuilder callIntentBuilder);
}
