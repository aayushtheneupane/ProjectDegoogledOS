package com.android.dialer.historyitemactions;

import android.content.Context;
import android.content.Intent;
import com.android.dialer.R;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindings;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.precall.PreCall;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.DialerUtils;
import com.google.common.collect.ImmutableList;
import java.util.Objects;
import java.util.function.Consumer;

public class IntentModule implements HistoryItemActionModule {
    private final Context context;
    private final int image;
    private final ImmutableList<DialerImpression$Type> impressions;
    private final Intent intent;
    private final int text;

    @Deprecated
    public IntentModule(Context context2, Intent intent2, int i, int i2) {
        ImmutableList<DialerImpression$Type> of = ImmutableList.m74of();
        this.context = context2;
        this.intent = intent2;
        this.text = i;
        this.image = i2;
        this.impressions = of;
    }

    @Deprecated
    public static IntentModule newCallModule(Context context2, CallIntentBuilder callIntentBuilder) {
        return newCallModule(context2, callIntentBuilder, ImmutableList.m74of());
    }

    @Deprecated
    public static IntentModule newModuleForSendingTextMessage(Context context2, String str) {
        return new IntentModule(context2, CallUtil.getSendSmsIntent(str), R.string.send_a_message, R.drawable.quantum_ic_message_vd_theme_24, ImmutableList.m74of());
    }

    public int getDrawableId() {
        return this.image;
    }

    public int getStringId() {
        return this.text;
    }

    public boolean onClick() {
        DialerUtils.startActivityWithErrorToast(this.context, this.intent, R.string.activity_not_available);
        ImmutableList<DialerImpression$Type> immutableList = this.impressions;
        LoggingBindings loggingBindings = Logger.get(this.context);
        Objects.requireNonNull(loggingBindings);
        immutableList.forEach(new Consumer() {
            public final void accept(Object obj) {
                ((LoggingBindingsStub) LoggingBindings.this).logImpression((DialerImpression$Type) obj);
            }
        });
        return true;
    }

    static IntentModule newCallModule(Context context2, CallIntentBuilder callIntentBuilder, ImmutableList<DialerImpression$Type> immutableList) {
        int i;
        int i2;
        if (callIntentBuilder.isVideoCall()) {
            i2 = R.string.video_call;
            i = R.drawable.quantum_ic_videocam_vd_white_24;
        } else {
            i2 = R.string.voice_call;
            i = R.drawable.quantum_ic_call_white_24;
        }
        return new IntentModule(context2, PreCall.getIntent(context2, callIntentBuilder), i2, i, immutableList);
    }

    IntentModule(Context context2, Intent intent2, int i, int i2, ImmutableList<DialerImpression$Type> immutableList) {
        this.context = context2;
        this.intent = intent2;
        this.text = i;
        this.image = i2;
        this.impressions = immutableList;
    }
}
