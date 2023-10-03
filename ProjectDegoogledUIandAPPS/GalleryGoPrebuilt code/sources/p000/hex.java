package p000;

import android.accounts.Account;
import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: hex */
/* compiled from: PG */
public final /* synthetic */ class hex implements Callable {

    /* renamed from: a */
    private final hey f12622a;

    /* renamed from: b */
    private final ieh f12623b;

    /* renamed from: c */
    private final fba f12624c;

    /* renamed from: d */
    private final ieh f12625d;

    public hex(hey hey, ieh ieh, fba fba, ieh ieh2) {
        this.f12622a = hey;
        this.f12623b = ieh;
        this.f12624c = fba;
        this.f12625d = ieh2;
    }

    public final Object call() {
        hey hey = this.f12622a;
        ieh ieh = this.f12623b;
        fba fba = this.f12624c;
        ieh ieh2 = this.f12625d;
        hpy hpy = (hpy) ife.m12871b((Future) ieh);
        if (hpy.mo7646a()) {
            fba.mo5454a(new Account((String) hpy.mo7647b(), "com.google"));
        }
        try {
            fba.mo5456a((fae) ife.m12871b((Future) ieh2), new File(hey.f12627b.getCacheDir(), "feedback"));
        } catch (ExecutionException e) {
            ((hvv) ((hvv) ((hvv) hey.f12626a.mo8178a()).mo8202a((Throwable) e)).mo8201a("com/google/apps/tiktok/monitoring/feedback/HelpService", "lambda$getHelpAndFeedbackIntent$0", 70, "HelpService.java")).mo8203a("Failed to get FeedbackOptions");
        }
        return fba.mo5452a();
    }
}
