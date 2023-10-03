package p000;

import java.io.File;
import java.util.concurrent.Executor;

/* renamed from: ckb */
/* compiled from: PG */
public final /* synthetic */ class ckb implements icf {

    /* renamed from: a */
    private final ckk f4537a;

    /* renamed from: b */
    private final String f4538b;

    /* renamed from: c */
    private final String f4539c;

    public ckb(ckk ckk, String str, String str2) {
        this.f4537a = ckk;
        this.f4538b = str;
        this.f4539c = str2;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        ckk ckk = this.f4537a;
        String str = this.f4538b;
        String str2 = this.f4539c;
        SecurityException securityException = (SecurityException) obj;
        if (ckk.f4568i.mo3175a()) {
            return gte.m10771a(ckk.f4565f.mo4787a(new File(str), false), (icf) new cjz(str2), (Executor) ckk.f4566g);
        }
        return gte.m10771a(ckk.f4565f.mo4786a(new File(str)), (icf) new cka(str2), (Executor) ckk.f4566g);
    }
}
