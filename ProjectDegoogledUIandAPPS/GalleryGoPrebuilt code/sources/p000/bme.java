package p000;

import java.util.regex.Pattern;

/* renamed from: bme */
/* compiled from: PG */
public final class bme {

    /* renamed from: a */
    public final hso f3139a;

    public bme(CharSequence charSequence) {
        hsj j = hso.m12048j();
        for (ige ige : ige.f14060f) {
            String str = ige.f14074b;
            if (str == null || Pattern.compile(str).matcher(charSequence).matches()) {
                j.mo7908c(new bmd(ige, Pattern.compile(ige.f14073a)));
            }
        }
        this.f3139a = j.mo7905a();
    }
}
