package com.android.voicemail.impl.settings;

import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.voicemail.impl.VvmLog;

/* renamed from: com.android.voicemail.impl.settings.-$$Lambda$VisualVoicemailSettingsUtil$JKUh3wC6LnoQDdsRFrwmUhHp2TI */
/* compiled from: lambda */
public final /* synthetic */ class C0781x7d427816 implements DialerExecutor.SuccessListener {
    public static final /* synthetic */ C0781x7d427816 INSTANCE = new C0781x7d427816();

    private /* synthetic */ C0781x7d427816() {
    }

    public final void onSuccess(Object obj) {
        VvmLog.m45i("VisualVoicemailSettingsUtil.setVoicemailTranscriptionEnabled", "voicemail transciptions cleared successfully");
    }
}
