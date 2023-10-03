package p000;

import com.google.android.gms.googlehelp.GoogleHelp;

/* renamed from: euc */
/* compiled from: PG */
public final class euc {

    /* renamed from: a */
    public final GoogleHelp f9024a;

    public euc(GoogleHelp googleHelp) {
        this.f9024a = googleHelp;
    }

    /* renamed from: a */
    public static final void m8176a(Runnable runnable) {
        Thread thread = new Thread(runnable, "PsdCollector");
        thread.setPriority(4);
        thread.start();
    }
}
