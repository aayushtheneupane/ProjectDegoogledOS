package p000;

import com.google.android.libraries.vision.visionkit.recognition.classifier.NativeClassifier;
import java.io.File;

/* renamed from: gce */
/* compiled from: PG */
public final class gce {

    /* renamed from: a */
    public long f10919a = 0;

    public gce(gch gch) {
        gcg gcg;
        if (gch.f10932b == 6) {
            gcf gcf = ((gcg) gch.f10933c).f10928c;
            m10009a(gcf == null ? gcf.f10920c : gcf);
            if (gch.f10932b == 6) {
                gcg = (gcg) gch.f10933c;
            } else {
                gcg = gcg.f10924d;
            }
            gcf gcf2 = gcg.f10927b;
            m10009a(gcf2 == null ? gcf.f10920c : gcf2);
        }
        try {
            this.f10919a = NativeClassifier.initialize(gch.mo8512ag());
        } catch (UnsatisfiedLinkError e) {
            throw new IllegalStateException("Call initialize() or load native library manually before calling constructor.");
        }
    }

    /* renamed from: a */
    private static void m10009a(gcf gcf) {
        if (gcf.f10922a == 1 && !new File((String) gcf.f10923b).exists()) {
            String valueOf = String.valueOf(gcf.f10922a == 1 ? (String) gcf.f10923b : "");
            throw new IllegalArgumentException(valueOf.length() == 0 ? new String("External resource file not found: ") : "External resource file not found: ".concat(valueOf));
        } else if (gcf.f10922a == 2 && ((ihw) gcf.f10923b).mo8623i()) {
            throw new IllegalArgumentException("External resource content is empty. Did you mean to add some?");
        }
    }
}
