package p000;

import android.content.Intent;
import android.util.Log;

/* renamed from: ftd */
/* compiled from: PG */
public final class ftd implements eyk {

    /* renamed from: a */
    private final /* synthetic */ ezy f10564a;

    /* renamed from: b */
    private final /* synthetic */ Intent f10565b;

    /* renamed from: c */
    private final /* synthetic */ fad f10566c;

    /* renamed from: d */
    private final /* synthetic */ eym f10567d;

    /* renamed from: e */
    private final /* synthetic */ fte f10568e;

    public ftd(ezy ezy, Intent intent, fad fad, eym eym, fte fte) {
        this.f10564a = ezy;
        this.f10565b = intent;
        this.f10566c = fad;
        this.f10567d = eym;
        this.f10568e = fte;
    }

    /* renamed from: b */
    public final void mo5395b() {
    }

    /* renamed from: a */
    public final void mo5394a() {
        fae fae;
        try {
            ezy ezy = this.f10564a;
            Intent intent = this.f10565b;
            fad fad = this.f10566c;
            if (intent != null) {
                fad.mo5437c(" ");
                fad.mo5435b();
                if (intent.hasExtra("com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.exceptionClass")) {
                    fad.mo5440d(intent.getStringExtra("com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.exceptionClass"));
                }
                if (intent.hasExtra("com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.exceptionMessage")) {
                    fad.mo5441e(intent.getStringExtra("com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.exceptionMessage"));
                }
                if (intent.hasExtra("com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.stackTrace")) {
                    fad.mo5442f(intent.getStringExtra("com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.stackTrace"));
                }
                if (intent.hasExtra("com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.throwingClass")) {
                    fad.mo5443g(intent.getStringExtra("com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.throwingClass"));
                }
                if (intent.hasExtra("com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.throwingFile")) {
                    fad.mo5444h(intent.getStringExtra("com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.throwingFile"));
                }
                if (intent.hasExtra("com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.throwingLine")) {
                    fad.mo5439a(intent.getIntExtra("com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.throwingLine", -1));
                }
                if (intent.hasExtra("com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.throwingMethod")) {
                    fad.mo5445i(intent.getStringExtra("com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.throwingMethod"));
                }
                if (intent.hasExtra("com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.categoryTag")) {
                    fad.mo5436b(intent.getStringExtra("com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.categoryTag"));
                }
                fae = fad.mo5430a();
            } else {
                fae = fad.mo5430a();
            }
            ezy.mo5422a(fae).mo5404a(new ftc(this.f10567d, this.f10568e));
        } catch (UnsupportedOperationException e) {
            Log.e("GcoreCrashReporter", "Could not clean PII, no feedback sent.");
            this.f10568e.mo6176a();
        }
    }
}
