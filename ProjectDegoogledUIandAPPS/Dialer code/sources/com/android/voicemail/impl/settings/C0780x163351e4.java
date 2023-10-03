package com.android.voicemail.impl.settings;

import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.voicemail.impl.VvmLog;

/* renamed from: com.android.voicemail.impl.settings.-$$Lambda$VisualVoicemailSettingsUtil$3n3reETwum_BQdi6xaapU9T2qm8 */
/* compiled from: lambda */
public final /* synthetic */ class C0780x163351e4 implements DialerExecutor.FailureListener {
    public static final /* synthetic */ C0780x163351e4 INSTANCE = new C0780x163351e4();

    private /* synthetic */ C0780x163351e4() {
    }

    public final void onFailure(Throwable th) {
        VvmLog.m44e("VisualVoicemailSettingsUtil.setVoicemailTranscriptionEnabled", "unable to clear Google transcribed voicemails", th);
    }
}
